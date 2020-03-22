
import {RechercheResult_1} from './RechercheMock'
export default class RechercheService{
        
    constructor(){}

    public fetchResult( queryText:string ): any[] {
       
        return RechercheResult_1;
    }



}