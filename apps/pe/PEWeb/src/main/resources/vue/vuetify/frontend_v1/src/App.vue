<template>

  <v-app  style="background:white;" >

    <AppHeader
      :appTitle="appTitle"
      :theme="theme"
      :themeDark="themeDark"
      @changeTheme="doChangeTheme"    
      @logOut="doLogOut"
      @editDocument="doEditDocument"
      @runAction="handleAction"
     
     />

    <v-main>
      <router-view ref="mainRouter" @loginRequest="handleLogin"/>
    </v-main>

    <AppFooter       
          :theme="theme"
          :themeDark="themeDark"
          v-bind:user="user"
          @next="goNext"
          @back="goBack"
    />

    <Alert 
      v-bind:alertOnError.sync="alertOnError"
      :errorMessage="errorMessage"
    />

  </v-app>
</template>

<script lang="ts">

export const Props={
  connectToDB: true,
  connectToServer: true,
  insideWebContainer:false,
};

import { Component, Vue, Ref } from 'vue-property-decorator';
import KfwApp from './module/ratingAnzeige/KfwApp.vue';
import KfwLogin from './components/KfwLogin.vue';
import VueRouter from 'vue-router';
import {routes} from './router';
import Axios from 'axios';
import KfwStore from './KfwStore';
import {InfoListener} from './KfwStore';
import AppFooter from './components/panels/AppFooter.vue';
import AppHeader from './components/panels/AppHeader.vue';
import Alert from './components/gui/Alert.vue';
import {KfwUser} from './model/kfwUser';
import {PEApplication,PEAction, actionHandlers} from './PEAnnotations';
import ${custAppClass} from '${custAppFile}';
//import MyApplication from './CustomerApp';

@Component({
  components: {
    KfwApp,
    AppHeader,
    Alert,
    AppFooter,
  }, 
})

export default class App extends Vue implements InfoListener {
  $refs!:{
    mainApp:KfwApp;
  }
  public themeMiddle: string = 'app_grad_blue_middle';
  public theme: string = 'app_toolbar_blue';
  public themeDark : boolean = true;
  public appTitle: string = '${app_title}';
  public alertOnError:boolean = false;
  public errorMessage:string = '';
  // Authentication and security  
  public loggedInSession:boolean = false;
  public AuthActionLogin:string = '1';
  public AuthActionLogout:string = '2';
  public AuthActionValidate:string = '3';
  public REQUEST_PARAMETER_J_USERNAME:string = 'j_username';
  public REQUEST_PARAMETER_J_MANDANT:string = 'j_mandant';
  public REQUEST_PARAMETER_J_PASSWORD:string = 'j_password';
	public REQUEST_PARAMETER_J_NEW_PASSWORD:string = 'j_password_new';
	
  
  public user_def = {
    id:'J483',
    name: 'Jamil Mohammed',
    mandat:'KFW-ENTW'
  };

  public user:KfwUser = new KfwUser();

  constructor(){
    super();
    window.addEventListener("load", this.onWindowLoad);
    KfwStore.addListener(this);
    KfwStore.setProperty(KfwStore.PROPERTY_THEME_CLASS,this.theme);
    KfwStore.setProperty(KfwStore.PROPERTY_THEME_MIDDLE,this.themeMiddle);
    KfwStore.setProperty(KfwStore.PROPERTY_THEME_DARK,this.themeDark);

  }
  
  private isLoggedIn():boolean{

    return ((localStorage.getItem("user-token")!=null) && (this.loggedInSession));
  }

  onWindowLoad():void{
    let custApp = Reflect.get(${custAppClass},"PEApplication");
    console.log("Loaded annotations " + JSON.stringify(actionHandlers));
  }

  public mounted():void{
    console.log("Mounted is called ...");
    
    this.handleRouting();    
  } 

  private handleRouting():void{
    if (Props.insideWebContainer){
      this.openPath('/');
      // TODO should send verification request
      this.sendVerificationRequest();
      return;
    }

    if (!this.isLoggedIn()){
      this.openPath('/login');
    }else {
      this.fillUserInfo();
      this.openPath('/');
    }
  }

   private fillUserInfo():void{
    if ((this.user.id==null) && (localStorage.getItem('user-token'))){
      let jwtData = JSON.parse(atob(localStorage.getItem('user-token').split('.')[1]));
      this.user.id = jwtData.userId;
      this.user.name = jwtData.userName;
      this.user.mandat = jwtData.mandat;
    }
  }

  openPath(path:string):void{ 
    if(this.$router.currentRoute.path!=path)
        this.$router.replace(path);

  }


  doEditDocument():void{

  }
  doChangeTheme():void{
    this.theme = this.themeDark?'app_toolbar_gray':'app_toolbar_blue';
    this.themeMiddle = this.themeDark?'app_grad_gray_middle':'app_grad_blue_middle';
    this.themeDark = !this.themeDark;
    KfwStore.setProperty(KfwStore.PROPERTY_THEME_DARK,this.themeDark);
    KfwStore.setProperty(KfwStore.PROPERTY_THEME_CLASS,this.theme);
    KfwStore.setProperty(KfwStore.PROPERTY_THEME_MIDDLE,this.themeMiddle);

  }

  handleLogin(e):void{
    console.log('Logging a user ' + e.user + ' with passwored ' + e.pwd);
    const params = new URLSearchParams();
    params.append(this.REQUEST_PARAMETER_J_USERNAME,e.user);
    params.append(this.REQUEST_PARAMETER_J_PASSWORD,e.password);
    params.append('action',this.AuthActionLogin);
    Axios.post<any>("http://localhost:9080/Recherche/Authenticate",params).then(res =>{
          this.processIncomingToken(res);
        }).catch(error =>{
          console.log("Login Failed :  " + error);
          this.handleLoginFailure();
        });
  }
  processIncomingToken(res: any):void{
    if (res.data.token){
      console.log('Successfully logged in and got the token : ' + res.data.token);
      localStorage.setItem('user-token',res.data.token);
      let jwtData = JSON.parse(atob(res.data.token.split('.')[1]));
      this.user.id = jwtData.userId;
      this.user.name = jwtData.userName;
      this.user.mandat = jwtData.mandat;
      this.loggedInSession = true;
      Axios.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
      console.log(jwtData);
      this.openPath('/');
    }else{
      console.log('Response ::: ' +  res.data);
      this.handleLoginFailure();
    }

  }
  
  handleAction(e):void{
    console.log("Received request to run the action : "  + e);
    let actionHandler = actionHandlers.find(i => i.id == e);
    if (actionHandler != undefined){
      actionHandler.handler();
    }else{
      this.openPath(e);

    }
  }
  
  sendVerificationRequest():void{
    const params = new URLSearchParams();
    params.append('action',this.AuthActionValidate);
    //{username: e.user,password:e.pwd,action:this.AuthActionLogin}
    Axios.post<any>("http://localhost:9080/Recherche/Authenticate",params).then(res =>{
          this.processIncomingToken(res);
      }).catch(error =>{
        console.log("Verification Failed :  " + error);
        this.openPath('/login');
      });

  }

  handleLoginFailure():void{
    KfwStore.showError('Login Failed !');
    KfwStore.setProperty(KfwStore.PROPERTY_CLEAR_LOGIN,true);
  }
    
  public doLogOut():void{
    // Nothing to do if we are inside the container
    if (Props.insideWebContainer) return;
    const params = new URLSearchParams();
    params.append(this.REQUEST_PARAMETER_J_USERNAME,this.user.id);
    params.append(this.REQUEST_PARAMETER_J_PASSWORD,'');
    params.append('action',this.AuthActionLogout);

    Axios.post<any>("http://localhost:9080/Recherche/Authenticate",params).then(res =>{
      localStorage.removeItem('user-token');
      delete Axios.defaults.headers.common['Authorization'];
      this.resetUser();
      this.handleRouting();
      this.loggedInSession = false;

    }).catch(error =>{
      console.log(" Logout Error : " + error);
      
    });

  }

  goNext():void{
    KfwStore.setProperty(KfwStore.PROPERTY_ACTION_NEXT,true);

  }
  goBack():void{
    KfwStore.setProperty(KfwStore.PROPERTY_ACTION_BACK,true);

  }


    public stateChanged(property:string,newValue:any):void{
      if (property==KfwStore.PROPERTY_SHOW_ALERT)
        this.alertOnError = newValue;
      if (property==KfwStore.PROPERTY_ALERT_TEXT)
        this.errorMessage = newValue;

  }


  private resetUser():void{
      this.user.id = null;
      this.user.name = null;
      this.user.mandat = null;

  }
}

</script>
