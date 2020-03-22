<template>
  <v-container>
    <v-layout
      text-center
      wrap
    >
      <v-flex xs12>
        <v-img
          :src="require('../assets/kfw_logo_120.png')"
          class="my-3"
          contain
          height="45"
        ></v-img>
      </v-flex>

      <v-flex mb-4>
        <h1 class="display-1 font-weight-bold mb-3">
          BANKENGRUPPE
        </h1>
      </v-flex>

      <v-flex
        xs12
        
        class="justify-center"
        
      >
        <h2 class="headline font-weight-bold">Ratinganzeige für Hauptgeschäftspartner</h2>
        

            <v-form>
               <v-container  >
                <v-row class="justify-center">
                  <v-col
                    cols="12"
                    md="4"
                  >
                    <v-text-field
                      ref="HGP_ID"
                      v-model="search"
                      clear-icon="mdi-close-circle"
                      :append-icon="search ? 'mdi-magnify' : 'mdi-timer-sand-empty'"
                      :counter="20"
                      label="Hauptgeschäftspartner"
                      required
                      clearable
                      outlined
                      @click:append="sendMessage"
                      @keyup.enter="sendMessage" 
                      @change="resetFields"
                    ></v-text-field>
          
                  </v-col>
                </v-row>
                <v-row class="justify-center">
                  <v-col
                    cols="12"
                    md="4"
                  >
                    <v-text-field
                      ref="GPID"
                      v-model="partnerId"
                      label="Geschäftspartner-ID (Hauptadresse)"
                      required
                      disabled
                    ></v-text-field>
                  </v-col>
                </v-row>
                <v-row class="justify-center">
                  <v-col
                    cols="12"
                    md="4"
                  >

                    <v-text-field
                      ref="NAME"
                      v-model="partnerName"
                      label="Geschäftspartnername"
                      required
                      disabled
                    ></v-text-field>
                  </v-col>
                </v-row>
               </v-container>
            </v-form>       

      </v-flex>

    </v-layout>
  
  <!--v-label>{{msg}}</v-label-->
   <v-expansion-panels
      v-show ="(this.rechercheResult.length>0) && (this.search!=null)&&(this.search.trim().length>0)"
      accordion
      focusable
    >
      <v-expansion-panel>
        <v-expansion-panel-header expand-icon="mdi-archive-arrow-down-outline">
          <strong >Recherche ....</strong>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-expansion-panels
              accordion
              focusable
            >

              <v-expansion-panel>
                <v-expansion-panel-header>Konfigurieren ...</v-expansion-panel-header>
                <v-expansion-panel-content>
                  TODO Configuration panel should be populated here
                </v-expansion-panel-content>
              </v-expansion-panel>

          </v-expansion-panels>  
          <v-divider class="mx-4" inset vertical></v-divider>
          <div>

            <v-data-table
              
              :headers="headers"
              :items="rechercheResult"
              item-key="Name"
              
              :search="resultSearch"
              @click="itemSelected"
            >
              <template v-slot:header.HGP_ID>
                <span class="tableHeader">HGP_ID</span>
              </template>
              <template v-slot:header.HGP>
                <span class="tableHeader">HGP</span>
              </template>
              <template v-slot:header.GPID>
                <span class="tableHeader">GPID</span>
              </template>
              <template v-slot:header.NAME>
                <span class="tableHeader">Name</span>
              </template>



              <template v-slot:top>
                <v-text-field v-model="resultSearch" label="Suchen" class="mx-4"></v-text-field>
              </template>
              <template v-slot:body.append>
                <tr>
                  <td></td>
                  <td>
                    <v-text-field v-model="resultSearch" type="number" label="Less than"></v-text-field>
                  </td>
                  <td colspan="4"></td>
                </tr>
              </template>
              <template v-slot:item.action="{ item }">
                <v-icon  small class="mr-2" @click="itemSelected(item)">mdi-cursor-default-click</v-icon>
              </template>
    
            </v-data-table>
          </div>
        </v-expansion-panel-content>
      </v-expansion-panel>
      <v-expansion-panel v-model="oldRechercheVisible">
          <v-expansion-panel-header expand-icon="mdi-archive-arrow-down-outline">
            <strong >JSP Recherche ....</strong>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="text-center">
            <div v-show="oldRechercheVisible"  v-html="serverResp" style="width:800px;height:800px;"></div>
          </v-expansion-panel-content>
      </v-expansion-panel>




    </v-expansion-panels>
  


   <v-snackbar
      v-model="alertOnError"
      bottom
      right
      color="red"
      :timeout="4000"
    >
      {{errorMessage}}
      <v-btn
        dark
        text
        @click="alertOnError = false"
      >
        Close
      </v-btn>
    </v-snackbar>
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
  declare global{
    interface Window{
       decorateFrameFunction:any; 
       selectionFromJsp:any;

    }
  }
  window.decorateFrameFunction = function(fr:any){
    console.log("I am called ....!!!");
    if (fr.contentWindow.internalSelectDone) {
      
      console.log("\t\t if (frami.contentWindow.internalSelectDone)");
      const rm = `
          window.selectionFromJsp(HGP_ID,HGP);
          `;
      const old = String(fr.contentWindow.internalSelectDone);
      const oldSig = old.slice(old.indexOf('(') + 1, old.indexOf(')'));
      let oldBody = old.slice(old.indexOf('{') + 1, old.lastIndexOf('}'));
      oldBody = oldBody.slice(oldBody.indexOf('{') + 1, oldBody.lastIndexOf('}'));
      let newBody = oldBody.replace('internalSelectDoneVar = true;', '');
      
      newBody = newBody.replace('parent.window.close();', rm);
      fr.contentWindow.internalSelectDone = new Function(oldSig, newBody);
      console.log("\t\t frami.contentWindow.internalSelectDone = new Function(oldSig, newBody);");
      console.log(fr.contentWindow.internalSelectDone);
      //log.debug(`---> ${frami.contentWindow.internalSelectDone}`);
    }
    // prevent contextmenu
    fr.contentWindow.addEventListener('contextmenu', function (event:any) {
      event.preventDefault();
    });

 }
 window.selectionFromJsp = function(HGP_ID:string, HGP:string){
   console.log("Got values " + HGP_ID);
 }

import {Component, Vue} from 'vue-property-decorator';

import {Message} from './Message';
import axios from 'axios';
import RechercheService from '../services/rechercheService'
import RechercheItem from './RechercheItem';
import {Props} from '../App.vue';


@Component
export default class KfwApp extends Vue {
    public msg: string = 'Welcome to KFW NOF project';
    public search: string = '';
    //public connectToDB: boolean = true;
    public partnerName: string = '';
    public partnerId: string = '';
    public rechercheResult: any[]=[];
    public resultSearch:string='';
    public serverResp:string = 'TT';
    public alertOnError:boolean = false;
    public loadingProgress:boolean = false;
    public errorMessage:string = '';
    public error_No_Results:string = 'Ihre suche ergab leider keine Treffer';
    public headers = [
          {
            text: 'HGP_ID',
            align: 'left',
            sortable: true,
            value: 'HGP_ID',
            class: 'tableHeader',
            /*
            filter: val  => {
              if (!this.rechercheResult) return true

              return value < parseInt(this.calories)
            },*/
          },

          {
            text: 'HGP',
            align: 'left',
            sortable: true,
            value: 'HGP',
            class: 'tableHeader',
            /*
            filter: val  => {
              if (!this.rechercheResult) return true

              return value < parseInt(this.calories)
            },*/
          },
          {
            text: 'GPID',
            align: 'left',
            sortable: true,
            value: 'GPID',
            class: 'tableHeader',
            /*
            filter: val  => {
              if (!this.rechercheResult) return true

              return value < parseInt(this.calories)
            },*/
          },
          {
            text: 'Name',
            align: 'left',
            sortable: true,
            value: 'NAME',
            class: 'tableHeader',
            /*
            filter: val  => {
              if (!this.rechercheResult) return true

              return value < parseInt(this.calories)
            },*/
          },
           { text: '', value: 'action', sortable: false },

        ];
    public oldRechercheVisible :boolean = false;


    public showOldRecherche():void{
      /*
      if (!this.oldRechercheVisible){
        this.testSend();
      }
      this.oldRechercheVisible = !this.oldRechercheVisible;
      */
    }
    
    
    constructor() {
        super();
        //this.loadFromServer();
    }

    private loadFromServer() {
        console.log('About fetching the Spring server message');
        axios.get<Message>('/api').then((res) => {
            console.log(res.data.text);
            this.msg = res.data.text;

        });
    }

    private sendToJSP() {
        console.log('About fetching the Spring server message');
        axios.get<any>('/rech').then((res) => {
            let jspURL = `'http://localhost:9080/Recherche/KfwFormularServer?APPLICATIONOBJEKT=Recherche&CLIENT_ACTION=InitRecherche&SC_RECHERCHE_TYPE=HauptGeschaeftspartner&SC_HGPNAME_VALUE=${this.search}&SC_RETURN_MODE=ReturnFromModalDialog'`
            this.serverResp = `<iframe style="width:100%;height:100%"   onload='decorateFrameFunction(this);' src=${jspURL} />`;
            //console.log(res.data);
        });
    }




/* Below can not be called from v-show attribute! */
    public showResults():boolean{
      return false;
      //return (this.rechercheResult.length>0);
      //return ((this.rechercheResult.length>0) && (this.search.trim().length>0));

    }

    public resetFields():void{
      if ((this.search==undefined)
      ||(this.search==null)
      ||(this.search.trim().length==0)
      ){
        this.partnerName = '';
        this.partnerId = '';
        this.rechercheResult = [];
      }

    }

    sendMessage():void{
      if (Props.connectToDB){
        this.sendRealMessage();
      }else{
        this.sendFakeMessage();

      }
    }
    
    sendRealMessage():void{
      //console.info("TODO : We do have to send a request for featching the data - " + this.search);
      //let targetURL : string = "/VueServlet";
      let targetURL : string = "/KFW/RechercheRest";
      
      //let targetURL : string = "/KfwRest";
      if (this.search)
        targetURL += '?SC_RECHERCHE_TYPE=HauptGeschaeftspartner&SC_HGPNAME_VALUE=' + this.search;
      console.log("Fetching from URL " + targetURL);
      this.loadingProgress = true;
      //axios.defaults.headers.common
      axios.get(targetURL).then(res => {
        if ((res.data.items)&&(res.data.items.length>0)){
            this.rechercheResult = res.data.items;
            this.sendToJSP();
            this.oldRechercheVisible = true;
        }else if (res.data){
            this.rechercheResult = res.data;
            //this.sendToJSP();
            //this.oldRechercheVisible = true;

        }else{
          console.error("No Serach results found !!");
          this.errorMessage = this.error_No_Results;
          this.alertOnError = true;
        }
        
      }).catch(error =>{
          this.errorMessage  = error;
          this.alertOnError = true;
      }).finally(() => {
        this.loadingProgress = false;
      });
      
    }    

    sendFakeMessage():void{
      console.info("TODO : We do have to send a request for featching the data - " + this.search);
      //this.msg = this.search;
      //this.loadFromServer();
      const service:RechercheService = new RechercheService();
      this.rechercheResult = service.fetchResult(this.search);
      console.log(this.rechercheResult);
      
    }    


    itemSelected(item:any):void {
      console.info("Item is selected " + item.GPID);
      this.partnerId = item.GPID;
      this.partnerName = item.NAME;
      this.search = item.HGP_ID;
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
