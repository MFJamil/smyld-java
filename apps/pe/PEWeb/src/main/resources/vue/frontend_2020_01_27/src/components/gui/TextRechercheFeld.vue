<template>
    <v-content>
        <v-text-field
            :ref="reference"
            clear-icon="mdi-close-circle"
            :append-icon="value ? 'mdi-magnify' : 'mdi-timer-sand-empty'"
            counter="20"
            :label="label"
            :value="value"
            required
            clearable
            outlined
            :disabled="disabled"
            @click:append="doSearch"
            @keyup.enter="doSearch" 
            @change="handleChange"

            @input="handleInput"
        ></v-text-field>
        <v-dialog v-model="showRechercheResult" persistent max-width="600">
            <RechercheResult 
            ref = "rechercheList" 
            :search="value"
            :headers="headers"
            :rechercheRS="rechercheResult"
            @itemSelected="handleItemSelection"/>
        </v-dialog>
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

    </v-content>
</template>
<script lang="ts">
    import { Component, Vue, Prop, Model } from 'vue-property-decorator';
    import RechercheResult from './RechercheResult.vue';
    import KfwStore from '../../KfwStore';
    import axios from 'axios';
    @Component(
        {
        components:{
            RechercheResult,

        }
    })
    export default class TextRechercheFeld extends Vue {
        $refs!:{
            rechercheList:RechercheResult;
        }

        @Prop()
        @Model('input')
        public value:string;
        @Prop()
        public label! :string;
        @Prop()
        public reference! :string;
        @Prop()
        public disabled! :boolean;
        @Prop()
        public headers:any[];


        public rechercheResult: any={};

        public loadingProgress:boolean = false;
        public showRechercheResult:boolean = false;

        
        doSearch():void{
            let targetURL : string = KfwStore.fetchURL('KfwRest');
            console.log(' ******************  ENV :: ' + process.env.NODE_ENV + " **** Searching for : " + this.value);
            if (this.value)
                targetURL += '?SC_RECHERCHE_TYPE=HauptGeschaeftspartner&SC_HGPNAME_VALUE=' + this.value;
            console.log("Fetching from URL " + targetURL);
            this.loadingProgress = true;
            axios.get(targetURL).then(res => {
            if ((res.data.items)&&(res.data.items.length>0)){
                console.log(res.data.items);
                this.rechercheResult = res.data.items;
                this.showRechercheResult = true;
                if (this.$refs.rechercheList)
                    this.$refs.rechercheList.sendToJSP();
            }else{
                console.error("No Serach results found !!");
                KfwStore.showNoDataError();
            }
            }).catch(error =>{
                KfwStore.showError(error);
            }).finally(() => {
            this.loadingProgress = false;
            });
        }
        
        handleItemSelection(item:any):void{
            this.$emit('itemSelected',item);
            this.showRechercheResult = false;
        }
        handleInput(event:any):void{
            this.$emit('input',event);
        }

        handleChange(event:any):void{
            this.$emit('change',event);
        }
}
</script>


          