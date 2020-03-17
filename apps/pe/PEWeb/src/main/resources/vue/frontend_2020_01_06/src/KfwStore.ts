import Vue from 'vue';




export interface InfoListener{
    stateChanged(property:string,value:boolean);
}

export class KfwStore{
    listeners :InfoListener[]=[];

    public PROPERTY_CLEAR_LOGIN: string = 'clearLogIn';

    public info = {
        PROPERTY_CLEAR_LOGIN: false,
    };

    constructor(){ }

    addListener(component: InfoListener):void{
        this.listeners.push(component);

    }

    public setProperty(property:string,value:boolean){
        console.log(this.info);
        this.info[property] = value;
        this.listeners.forEach(currentListener => currentListener.stateChanged(property, value));
    }

}


export default new KfwStore();