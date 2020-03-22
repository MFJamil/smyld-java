<template>
   <v-expansion-panels 
      accordion   focusable  :dark="themeDark">
      <v-expansion-panel  :class="themeMiddle">
        <v-expansion-panel-header  :dark="themeDark"   expand-icon="mdi-archive-arrow-down-outline">
          <strong >Recherche ....</strong>
        </v-expansion-panel-header>
        <v-expansion-panel-content
          
        >
          <v-divider class="mx-4" inset vertical></v-divider>
          <div>

            <v-data-table
              :headers="headers"
              :items="rechercheRS"
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



              <!-- Templates around the table -->
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
      <!-- JSP Recherche Section -->
      <v-expansion-panel v-model="oldRechercheVisible"  :class="themeMiddle">
          <v-expansion-panel-header  expand-icon="mdi-archive-arrow-down-outline">
            <strong >JSP Recherche ....</strong>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="text-center" text-center>
            <div  v-html="serverResp" style="margin:auto;width:800px;height:800px;"></div>
          </v-expansion-panel-content>
      </v-expansion-panel>

    </v-expansion-panels>
        
</template>
<script lang="ts">

  declare global{
    interface Window{
       decorateFrameFunction:any; 
       selectionFromJsp:any;
       rechercheResult:RechercheResult;

    }
  }
  window.decorateFrameFunction = function(fr:any){
    if (fr.contentWindow.internalSelectDone) {
      
      console.log("\t\t if (frami.contentWindow.internalSelectDone)");
      // Here we need to replace the information below with a dynamic array to be generic
      const rm = `
          window.selectionFromJsp(HGP_ID,HGP,GPID,HGPNAME);
          `;
      const old = String(fr.contentWindow.internalSelectDone);
      const oldSig = old.slice(old.indexOf('(') + 1, old.indexOf(')'));
      let oldBody = old.slice(old.indexOf('{') + 1, old.lastIndexOf('}'));
      oldBody = oldBody.slice(oldBody.indexOf('{') + 1, oldBody.lastIndexOf('}'));
      let newBody = oldBody.replace('internalSelectDoneVar = true;', '');
      
      newBody = newBody.replace('parent.window.close();', rm);
      fr.contentWindow.internalSelectDone = new Function(oldSig, newBody);
      console.log(fr.contentWindow.internalSelectDone);
    }
    // prevent contextmenu
    fr.contentWindow.addEventListener('contextmenu', function (event:any) {
      event.preventDefault();
    });

 }
 window.selectionFromJsp = function(HGP_ID:string, HGP:string, GPID:string, HGPNAME:string){
   console.log("Got values:\n \t HGP_ID : " + HGP_ID + "\n\t HGP : " + HGP + "\n\t GPID : " + GPID+ "\n\t HGPNAME : " + HGPNAME);
   let item = {GPID:GPID,NAME:HGPNAME,HGP_ID:HGP_ID};
   this.rechercheResult.itemSelected(item);
}


    import { Component, Vue, Ref, Watch, Prop } from 'vue-property-decorator';
    import KfwStore from '../../KfwStore';
    import {InfoListener} from '../../KfwStore';
    @Component({
    components: {

    }, 
    })

    export default class RechercheResult extends Vue implements InfoListener{
        
        @Prop()
        public headers:any[];
        @Prop()
        public rechercheRS!: any;
        @Prop()
        public search!: any;

        public resultSearch:string='';
        public themeMiddle: string = 'app_grad_blue_middle';
        public theme: string = 'app_toolbar_blue';
        public themeDark:boolean = true;


        public serverResp:string = 'TT';
        public alertOnError:boolean = false;
        public oldRechercheVisible :boolean = false;
        public newRechercheVisible :boolean = false;



        mounted():void{
            console.log("Recherche Result Mounteed is called .....");
            window.rechercheResult = this;
            KfwStore.addListener(this);
            this.sendToJSP();
            this.oldRechercheVisible = true;
            this.theme = KfwStore.getProperty(KfwStore.PROPERTY_THEME_CLASS);
            this.themeMiddle = KfwStore.getProperty(KfwStore.PROPERTY_THEME_MIDDLE);
            this.themeDark = KfwStore.getProperty(KfwStore.PROPERTY_THEME_DARK); 
            
        }
        @Watch('oldRechercheVisible')
        handleJSPPage(newValue: any):void{
          console.log("Watch function is called ..... ");  
        }
        public sendToJSP():void {
            console.log('About fetching the JSP Recherche Page');
            let jspURL = `'http://localhost:9080/Recherche/KfwFormularServer?APPLICATIONOBJEKT=Recherche&CLIENT_ACTION=InitRecherche&SC_RECHERCHE_TYPE=HauptGeschaeftspartner&SC_HGPNAME_VALUE=${this.search}&SC_RETURN_MODE=ReturnFromModalDialog'`
            this.serverResp = `<iframe style="width:100%;height:100%"   onload='decorateFrameFunction(this);' src=${jspURL} />`;
        }

        itemSelected(item:any):void {
            console.info("Item is selected " + item.GPID);
            this.oldRechercheVisible = false;
            this.newRechercheVisible = false;
            this.$emit('itemSelected',item);
        }

        public stateChanged(property:string,newValue:any):void{
            if (property==KfwStore.PROPERTY_THEME_DARK)
                this.themeDark = newValue;
            if (property==KfwStore.PROPERTY_THEME_CLASS)
                this.theme = newValue;
            if (property==KfwStore.PROPERTY_THEME_MIDDLE)
                this.themeMiddle = newValue;
        }


    }
</script>