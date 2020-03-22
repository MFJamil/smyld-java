<template>
  <v-app style="background:white;">
    <v-app-bar
      collapse-on-scroll
      app
      min-width="200px"
      color="secondary"
      :class="theme"
      :dark="themeDark"
      :light="!themeDark"
      >
      <v-app-bar-nav-icon> </v-app-bar-nav-icon>
      <v-toolbar-title>{{appTitle}}</v-toolbar-title>
      <v-spacer></v-spacer>
      <div class="d-flex align-center">
        <v-img
          alt="KFW Logo"
          class="shrink mr-2"
          contain
          src="${app_logo}"
          transition="scale-transition"
          width="100"
        />

      </div>
     
      <template v-slot:extension >
            <v-btn  text fab small><v-icon small>mdi-close-circle-outline</v-icon></v-btn>
            <v-btn  text fab small><v-icon small>mdi-undo-variant</v-icon></v-btn>
            <v-btn  text fab small><v-icon small>mdi-pause</v-icon></v-btn>
            <v-btn  text fab small 
              @click="editDocument"
              ><v-icon small>mdi-file-document-edit-outline</v-icon></v-btn>
            <v-spacer></v-spacer>
            <v-btn  text fab small
            @click="changeTheme"
            ><v-icon small>mdi-compare</v-icon></v-btn>
            <v-btn  text fab small><v-icon small>mdi-help-circle-outline</v-icon></v-btn>
            <v-btn  text fab small><v-icon small>mdi-printer</v-icon></v-btn>
            <v-btn  text fab small
            @click="logOut"
            ><v-icon small>mdi-exit-to-app</v-icon>
              
            </v-btn>
      </template>
    </v-app-bar>

    <v-content>
      <router-view ref="mainRouter" @loginRequest="handleLogin"/>
      <!--
      <KfwApp ref="mainApp" v-show="false"/>
      <KfwLogin ref="loginPanel" v-show="false" />
      -->
    </v-content>


    <v-footer
      height="40px"
      :class="theme"
      :dark="themeDark"
      :light="!themeDark"
      padless
      >
      <span style="font-size:11px;padding-left:15px;">{{new Date().toUTCString()}} | {{composeUserInfo()}}</span>
      <v-spacer></v-spacer>
      <v-btn  text fab small disabled><v-icon small>mdi-chevron-double-left</v-icon></v-btn>
      <v-btn  text fab small><v-icon small>mdi-chevron-double-right</v-icon></v-btn>
    </v-footer>
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
  </v-app>
</template>

<script lang="ts">

export const Props={
  connectToDB: true,
  connectToServer: true,
};


import { Component, Vue, Ref } from 'vue-property-decorator';
import KfwApp from './components/KfwApp.vue';
import KfwLogin from './components/KfwLogin.vue';
import VueRouter from 'vue-router';
import {routes} from './router';
import axios from 'axios';
import KfwStore from './KfwStore';

@Component({
  components: {
    KfwApp,
    KfwLogin,
    
  }, 
})  

export default class App extends Vue {

  $refs!:{
    mainApp:KfwApp,
    loginPanel:KfwLogin,
  }

  public alertOnError:boolean = false;
  public errorMessage:string = '';
  public theme: string = 'app_toolbar_blue';
  public themeDark : boolean = true;
  public appTitle: string = '${app_title}';
  public showLogin:boolean = false;
  
  public user_def = {
    id:'J483',
    name: 'Jamil Mohammed',
    role:'KFW-ENTW'
  };

  public user = {
    id: null,
    name: null,
    role: null
  };

  constructor(){
    super();
    window.addEventListener("load", this.onWindowLoad);
  }

  public mounted():void{
    console.log("Mounted is called ...");
    this.handleRouting();    
  } 

  private handleRouting():void{
    if (!this.isLoggedIn()){
      if(this.$router.currentRoute.path!='/login')
        this.$router.replace("/login");
    }else {
      this.fillUserInfo();
      if (this.$router.currentRoute.path!='/')
        this.$router.replace("/");
    }
  }

  public composeUserInfo():string{
    if (!Props.connectToServer)
      this.user = this.user_def;
    return (this.user.id==null?'':this.user.id + ' (' + this.user.name+') | ' + this.user.role);
  }

  private fillUserInfo():void{
    if ((this.user.id==null) && (localStorage.getItem('user-token'))){
      let jwtData = JSON.parse(atob(localStorage.getItem('user-token').split('.')[1]));
      this.user.id = jwtData.userId;
      this.user.name = jwtData.userName;
      this.user.role = jwtData.userRole;
    }
  }

  private isLoggedIn():boolean{
    return (localStorage.getItem("user-token")!=null);
  }

  onWindowLoad():void{

  }


  editDocument():void{
    Props.connectToDB = !Props.connectToDB;
    Props.connectToServer = !Props.connectToServer;
    //console.log("Connect to db is now : " + Props.connectToDB);
  }

  changeTheme():void{
    //this.$vuetify.application.bar. = "app_toolbar_grey";
    this.theme = this.themeDark?'app_toolbar_grey':'app_toolbar_blue';
    this.themeDark = !this.themeDark;
  }

  handleLogin(e):void{
    console.log('Logging a user ' + e.user + ' with passwored ' + e.pwd);
    let authenticateServer1:string = "http://localhost:8099/authenticate";
    let authenticateServer2:string = "http://localhost:9080/KFW/authenticate";
    axios.post<any>(authenticateServer2,{username: e.user,password:e.pwd}).then((res) => {
          if (res.data.token){
            console.log('Successfully logged in and got the token : ' + res.data.token);
            localStorage.setItem('user-token',res.data.token);
            let jwtData = JSON.parse(atob(res.data.token.split('.')[1]));
            this.user.id = jwtData.userId;
            this.user.name = jwtData.userName;
            this.user.role = jwtData.userRole;
            axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
            console.log(jwtData);
            this.$router.replace("/");
          }else{
            console.log('Response ::: ' +  res.data);
            this.handleLoginFailure();
          }
        }).catch(error =>{
          console.log(" Got an error !!!  " + error);
          this.handleLoginFailure();
        });
  }

  handleLoginFailure():void{
    this.errorMessage = 'Login Failed !';
    this.alertOnError = true;
    KfwStore.setProperty(KfwStore.PROPERTY_CLEAR_LOGIN,true);
  }
    
  public logOut():void{
    localStorage.removeItem('user-token');
    delete axios.defaults.headers.common['Authorization'];
    this.user.id = null;
    this.handleRouting();
  }
}
</script>

<style scoped>
  .app_toolbar_blue{
    background: linear-gradient(180deg, rgba(31,70,101,1) 0%, rgba(52,101,139,1) 100%);
  }
  .app_toolbar_grey{
    background: linear-gradient(180deg, rgba(198,202,205,1) 0%, rgba(243,243,243,1) 100%);
  }
  body{
    background: white;
  }

</style>
