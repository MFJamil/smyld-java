/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.util;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

 public class Props<T extends Enum<T>> {
    EnumMap<T,Object> props ;
    Class<T> enumType;

    public Props(Class<T> type){
        this.enumType  = type;
        props = new EnumMap<T, Object>(type);
    }



    public void addProperty(T prop,Object value){props.put(prop,value);}

    public String getString(T prop){return (String)props.get(prop);}
    public int getInt(T prop){return props.containsKey(prop)? (Integer)props.get(prop):0;}
    public boolean is(T prop){return props.containsKey(prop)? (Boolean)props.get(prop):false;}
    public Font getFont(T prop){return (Font)props.get(prop);}
    public Color getColor(T prop){return (Color)props.get(prop);}
    public BasicStroke getBasicStroke(T prop){return (BasicStroke)props.get(prop);}




}
