<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex xs12>
        <v-img  :src="require('../../assets/kfw_logo_120.png')"  class="my-3" contain height="45"></v-img>
      </v-flex>
      <v-flex mb-4>
        <h1 class="display-1 font-weight-bold mb-3">BANKENGRUPPE</h1>
      </v-flex>
      <v-flex xs12  class="justify-center">
        <h2 class="headline font-weight-bold">Ratinganzeige für Hauptgeschäftspartner</h2>
            <v-form>
               <v-container>
                <v-row class="justify-center">
                  <v-col  cols="12"  md="4">
                    <TextRechercheFeld 
                    v-model="search" 
                    :headers="RechercheTableHeaders"
                    :disabled="searchDisabled"  
                    :reference="'HGP_ID'"  
                    :label="'Hauptgeschäftspartner'" 
                     @itemSelected="itemSelected"  
                     @change="resetFieldsByEmptySearch" />
                  </v-col>
                </v-row>
                <v-row class="justify-center">
                  <v-col  cols="12" md="4">
                    <v-text-field ref="GPID"   v-model="partnerId"  label="Geschäftspartner-ID (Hauptadresse)" required  disabled></v-text-field>
                  </v-col>
                </v-row>
                <v-row class="justify-center">
                  <v-col cols="12" md="4">
                    <v-text-field  ref="NAME"  v-model="partnerName"  label="Geschäftspartnername"  required  disabled></v-text-field>
                  </v-col>
                </v-row>
               </v-container>
            </v-form>       
      </v-flex>
      <v-flex xs12  class="justify-center">
        <transition name="scale-transition" origin="center center">
          <router-view ref="ratingsRouter" :key="$route.path" @itemSelected="itemSelected" />
        </transition>
      </v-flex>
    </v-layout>

    <v-dialog
      v-model="loadingProgress"
      hide-overlay
      persistent
      width="300"
    >
      <v-card
        color="primary"
        dark
      >
        <v-card-text>
          Daten werden geladen .....
          <v-progress-linear
            indeterminate
            color="white"
            class="mb-0"
          ></v-progress-linear>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-container>
  
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator';

import axios from 'axios';
import RechercheService from './service/rechercheService'
import RechercheItem from './model/RechercheItem';
import KfwStore, {InfoListener} from '../../KfwStore';

import TextRechercheFeld from '../../components/gui/TextRechercheFeld.vue';
import Alert from '../../components/gui/Alert.vue';
import {RechercheTableHeaders} from './model/RatingSettings';




@Component(
  {
    components:{
      TextRechercheFeld,
    }
  }

)
export default class KfwApp extends Vue implements InfoListener{
    public themeMiddle: string = 'app_grad_blue_middle';
    public theme: string = 'app_toolbar_blue';
    public themeDark:boolean = true;

    public search: string = '';
    public connectToDB: boolean = true;
    public partnerName: string = '';
    public partnerId: string = '';

    public resultSearch:string='';
    public serverResp:string = 'TT';
    public loadingProgress:boolean = false;
    public searchDisabled:boolean = false;
    public RechercheTableHeaders = RechercheTableHeaders;


    
    constructor() {
        super();
        KfwStore.addListener(this);
    }




    public resetFieldsByEmptySearch():void{
      KfwStore.setProperty(KfwStore.PROPERTY_SERACH_TEXT,this.search);
      if (KfwStore.isEmpty(this.search)){
          this.resetFields();
      }
    }

    public resetFields():void{
        this.search = '';
        this.partnerName = '';
        this.partnerId = '';
        KfwStore.setProperty(KfwStore.PROPERTY_RECHERCHE_LISTE,[]);
        KfwStore.setProperty(KfwStore.PROPERTY_ACTIVATE_NEXT,false);
        KfwStore.setProperty(KfwStore.PROPERTY_ACTIVATE_BACK,false);
      
    }


  
    mounted():void{
      console.log('Setting the kfw application instance !!');
      // For testing
      //KfwStore.showError("Testing the alert....");
      console.log(this.$route);
    }

    
    queryRatings():void{
      let targetURL : string = KfwStore.fetchURL('RatingAnzeigeRest');
      console.log(' ******************  ENV :: ' + process.env.NODE_ENV);
      if (this.search)
        targetURL += '?QR_VORGANGSOBJEKT=' + this.search;
      console.log("Fetching from URL " + targetURL);
      this.loadingProgress = true;
      axios.get(targetURL).then(res => {
        console.log(res);
        if ((res.data.items)&&(res.data.items.length>0)){
          KfwStore.setProperty(KfwStore.PROPERTY_RATINGS_LISTE,res.data.items);
          this.$router.replace({
            name: 'history',
            query:{
              search : this.search,
            }
          } );
        }else{
          console.error("No Ratings results found !!");
          KfwStore.showNoDataError();
        }
      }).catch(error =>{
        KfwStore.showError(error);
      }).finally(() => {
        this.loadingProgress = false;
      });
      
    }    

    itemSelected(item:any):void {
      console.info("Item is selected " + item.GPID);
      this.partnerId = item.GPID;
      this.partnerName = item.NAME;
      this.search = item.HGP_ID;
      KfwStore.setProperty(KfwStore.PROPERTY_ACTIVATE_NEXT,true);
    }

    public stateChanged(property:string,newValue:any):void{
      if (property==KfwStore.PROPERTY_THEME_DARK)
        this.themeDark = newValue;
      if (property==KfwStore.PROPERTY_THEME_CLASS)
        this.theme = newValue;
      if (property==KfwStore.PROPERTY_THEME_MIDDLE)
        this.themeMiddle = newValue;
      if (property==KfwStore.PROPERTY_ACTION_NEXT)
        this.handleNextAction(newValue);
      if (property==KfwStore.PROPERTY_ACTION_BACK)
        this.handleBackAction(newValue);

  }

    handleNextAction(value:boolean):void{
        if (!value) return;
        this.searchDisabled = true;
        this.queryRatings();
        this.$router.replace('/history');
    }

    handleBackAction(value:boolean):void{
        if (!value) return;
        this.searchDisabled = false;
        this.$router.replace('/');
        this.resetFields();

    }


}



</script>
<style scoped>
  .tableHeader{
    color:var(--v-primary-base);
    font-size: 15px;
    font-style: italic;
    
  }

</style>
