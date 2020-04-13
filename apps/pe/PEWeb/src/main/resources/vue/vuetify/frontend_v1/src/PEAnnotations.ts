

export function PEAction(value:any){
    
    return function (target, propertyKey: string, descriptor: PropertyDescriptor) {
        actionHandlers.push({id: value.action,handler:descriptor.value,host:target});        

        console.log("PEAction(): called " + JSON.stringify(value) + 
            " PropKey: " + propertyKey + " descriptor : " +  JSON.stringify(descriptor) + 
            " target : " + target + " function " + descriptor.value);


    }
}

export function PEApplication(constructorFunction: Function) {
  console.log("PEApplication is invoked for Object : " + JSON.stringify(constructorFunction.name) );  
}

export const actionHandlers =[
];
