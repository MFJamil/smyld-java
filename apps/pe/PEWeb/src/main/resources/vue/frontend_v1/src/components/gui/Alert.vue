<template>
    <v-snackbar
          :value="alertOnError"
          bottom
          right
          color="red"
          @input="closeSnackbar"
          :timeout="4000"
          
          
        >
          {{errorMessage}}
          <v-btn
            dark
            text
            @click="closeSnackbar"
          >
            Close
          </v-btn>
    </v-snackbar>
</template>
<script lang="ts">
    import { Component, Vue, Prop, Model } from 'vue-property-decorator';
    @Component
    export default class Alert extends Vue {
        @Prop()
        public errorMessage! :string;
      
        @Prop({default:false})
        @Model('click')
        public alertOnError! :boolean;

        closeSnackbar(event):void{
          this.$emit('update:alertOnError', false);
        }

    }
</script>