<template>
   <v-expansion-panels
      accordion   focusable  :dark="themeDark">
      <v-expansion-panel  :class="themeMiddle">
        <v-expansion-panel-header  :dark="themeDark"   expand-icon="mdi-archive-arrow-down-outline">
          <strong >Ratinghistorie ....</strong>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-divider class="mx-4" inset vertical></v-divider>
          <div>

        <v-data-table
          :headers="headers"
          :items="ratingsResult"
          item-key="Name"
          :search="resultSearch"
           
           >
            <template v-slot:header.HGP_ID>
                <span class="tableHeader">Verfahen</span>
            </template>
            <template v-slot:header.HGP>
                <span class="tableHeader">LC-Rating</span>
            </template>
            <template v-slot:header.GPID>
                <span class="tableHeader">RC-Rating</span>
            </template>
            <template v-slot:header.NAME>
                <span class="tableHeader">Bilanzdaten vom</span>
            </template>
    
        </v-data-table>
    </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
        
        
</template>
<script lang="ts">

    import { Component, Vue, Ref } from 'vue-property-decorator';
    import {RatingsTableHeaders} from '../model/RatingSettings';
    import KfwStore from '../../../KfwStore';
    import {InfoListener} from '../../../KfwStore';


    @Component({
    components: {

    }, 
    })

    export default class RatingHistory extends Vue implements InfoListener {
        public headers = RatingsTableHeaders;
        public ratingsResult: any[]=[];
        public resultSearch:string='';
        public themeMiddle: string = 'app_grad_blue_middle';
        public theme: string = 'app_toolbar_blue';
        public themeDark:boolean = true;


        mounted():void{
            console.log("RatingHistory Mounteed is called .....");
            KfwStore.setProperty(KfwStore.PROPERTY_ACTIVATE_NEXT,false);
            KfwStore.setProperty(KfwStore.PROPERTY_ACTIVATE_BACK,true);
            KfwStore.addListener(this);
            this.ratingsResult = KfwStore.getProperty(KfwStore.PROPERTY_RATINGS_LISTE); 
            this.theme = KfwStore.getProperty(KfwStore.PROPERTY_THEME_CLASS);
            this.themeMiddle = KfwStore.getProperty(KfwStore.PROPERTY_THEME_MIDDLE);
            this.themeDark = KfwStore.getProperty(KfwStore.PROPERTY_THEME_DARK); 


        }

        public stateChanged(property:string,newValue:any):void{
            if (property==KfwStore.PROPERTY_THEME_DARK)
                this.themeDark = newValue;
            if (property==KfwStore.PROPERTY_THEME_CLASS)
                this.theme = newValue;
            if (property==KfwStore.PROPERTY_THEME_MIDDLE)
                this.themeMiddle = newValue;
            if (property==KfwStore.PROPERTY_RATINGS_LISTE)
                this.ratingsResult = newValue;

        }

    }
</script>