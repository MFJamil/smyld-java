import Vue from 'vue';




export interface InfoListener{
    stateChanged(property:string,value:any);
}

export class KfwStore{
    listeners :InfoListener[]=[];

    public PROPERTY_CLEAR_LOGIN: string = 'clearLogIn';
    public PROPERTY_THEME_DARK: string = 'themeDark';
    public PROPERTY_THEME_CLASS: string = 'themeClass';
    public PROPERTY_THEME_MIDDLE: string = 'themeMiddle';
    public PROPERTY_SERACH_TEXT: string = 'searchText';
    public PROPERTY_RECHERCHE_LISTE: string = 'rechercheListe';
    public PROPERTY_RATINGS_LISTE: string = 'ratingsListe';
    public PROPERTY_ACTION_NEXT: string = 'actionNext';
    public PROPERTY_ACTION_BACK: string = 'actionBack';

    public PROPERTY_ACTIVATE_NEXT: string = 'activateNext';
    public PROPERTY_ACTIVATE_BACK: string = 'activateBack';

    public PROPERTY_HIDE_RECHERCHE: string = 'hideRecherche';

    public PROPERTY_SHOW_ALERT: string = 'showAlert';
    public PROPERTY_ALERT_TEXT: string = 'alertText';


    public error_No_Results:string = 'Ihre suche ergab leider keine Treffer';
    

    public info = {
    };

    constructor(){ }

    addListener(component: InfoListener):void{
        this.listeners.push(component);

    }

    public getProperty(property:string):any{    
        return this.info[property];
    }
    public setProperty(property:string,value:any){
        //console.log(this.info);
        this.info[property] = value;
        this.listeners.forEach(currentListener => currentListener.stateChanged(property, value));
    }

    public showNoDataError():void{
        this.showError(this.error_No_Results);
    }
    public showError(message:string):void{
        this.setProperty(this.PROPERTY_ALERT_TEXT,message);
        this.setProperty(this.PROPERTY_SHOW_ALERT,true);

    }

    public fetchURL(urlText:string) :string{
        return process.env.NODE_ENV == 'development'?'/' + urlText: '/Recherche/' + urlText;
    }

    public isEmpty(value:string):boolean{
        return (
            (value==undefined) || 
            (value==null) || 
            (value.trim().length==0));


    }
  
}

const kfwStore = new KfwStore();

export default kfwStore;