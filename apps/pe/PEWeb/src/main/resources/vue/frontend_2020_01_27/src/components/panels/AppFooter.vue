<template>
    <v-footer
      height="40px"
      color="secondary"
      :class="theme"
      :dark="themeDark"
      padless
      >
      <span style="font-size:11px;padding-left:15px;">{{new Date().toUTCString()}} | {{user.id}} ({{user.name}}) | {{user.mandat}}</span>
      <v-spacer></v-spacer>
      <v-btn  text fab small
       :disabled="!btnBackActive"
       ref="backBtn"
      @click="doHandleBack"
      ><v-icon small>mdi-chevron-double-left</v-icon></v-btn>
      <v-btn  text fab small 
      :disabled="!btnNextActive"
      ref="nextBtn"
      @click="doHandleNext"
      ><v-icon small>mdi-chevron-double-right</v-icon></v-btn>
    </v-footer>

</template>
<script lang="ts">
    import { Component, Vue, Prop } from 'vue-property-decorator';
  
    import KfwUser from '../../model/kfwUser';
    import KfwStore from '../../KfwStore';
    import {InfoListener} from '../../KfwStore';

    @Component
    export default class AppFooter extends Vue implements InfoListener{
        @Prop()
        public theme! :string;
        @Prop({default:true})
        public themeDark! :boolean;
        @Prop({default:new KfwUser(), required:true})
        public user!:KfwUser;

        public btnBackActive:boolean = false;
        public btnNextActive:boolean = false;

        mounted():void{
            KfwStore.addListener(this);

        }

        doHandleNext():void{
            this.$emit('next');

        }

        doHandleBack():void{
            this.$emit('back');
        }
        

        public stateChanged(property:string,newValue:any):void{
            if (property==KfwStore.PROPERTY_ACTIVATE_NEXT)
                this.btnNextActive = newValue;
            if (property==KfwStore.PROPERTY_ACTIVATE_BACK)
                this.btnBackActive = newValue;

        }

    }


</script>