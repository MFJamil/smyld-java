<template>
     
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                
                flat
              >
                <v-toolbar-title>Login</v-toolbar-title>
                
                <v-spacer />
                <v-icon>mdi-login</v-icon>

              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    label="Login"
                    name="login"
                    prepend-icon="mdi-account"
                    type="text"
                    v-model="user"
                  />

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-model="pwd"
                  />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn color="primary" @click="handleLogin">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    
</template>
<script lang="ts">
    import {Vue,Component, Inject} from 'vue-property-decorator';
    import AuthenticateRequest from './AuthenticateRequest';
    import {Props} from '../App.vue';
    import {InfoListener} from '../KfwStore';
    import KfwStore from '../KfwStore';
    @Component
    export default class KfwLogin extends Vue implements InfoListener{
        public pwd:string = '';
        public user:string = '';
        
        constructor(){
          super();
          KfwStore.addListener(this);

        }
        public clearFields():void{
          this.pwd = '';
          this.user = '';
        }
        
        public handleLogin(){
            let authReq = new AuthenticateRequest();
            authReq.user = this.user;
            authReq.pwd = this.pwd;
            console.log("Login requset attempted with the info :  " +  JSON.stringify(authReq));
            this.$emit('loginRequest', authReq);
        }

        public stateChanged(property:string,newValue:boolean):void{
          if (property==KfwStore.PROPERTY_CLEAR_LOGIN)
            this.clearFields();

        }


    }
</script>