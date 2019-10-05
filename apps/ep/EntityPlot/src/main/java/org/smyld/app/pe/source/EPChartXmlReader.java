/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.source;

import com.sun.jdi.CharType;
import org.jdom2.Element;
import org.smyld.app.pe.*;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;
import org.smyld.app.pe.flowchart.FlowChartElementsEnum;
import org.smyld.xml.XMLUtil;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.*;
import java.util.stream.Stream;

/**
 * Class for reading the xml file and creating the chart entities
 */
public class EPChartXmlReader implements ChartReader {
    protected Element rootEl;
    String imagesPath;
    static Map<String, Color> colormap;

    /**
     * Constructor to process the file and creates the relevant chart elements and their connections
     * @param xmlFileName
     * @throws Exception
     */
    public EPChartXmlReader(String xmlFileName) throws Exception {
        rootEl =  XMLUtil.getRootNode(xmlFileName);
        init();
    }

    private void init(){
        if (rootEl!=null){
            imagesPath  = rootEl.getChildText("images");
            checkFileType();
        }
    }
    protected void checkFileType(){
        if (ChartType.valueOf(rootEl.getName())!=ChartType.epchart) throw new RuntimeException("Unsupported Chart type " + rootEl.getName());
    }

    @Override
    public Set<EntityPlotter> loadEntities() {
        Set<EntityPlotter> result = new HashSet<>();

        if (rootEl.getChild("elements")!=null){
            rootEl.getChild("elements").getChildren().forEach(curEl ->{
                result.add(readItem(curEl,getImplClass(curEl.getAttributeValue("type")))) ;
            });
        }
        return result;
    }

    protected Class<?> getImplClass(String type){
        return EntityPlotterImpl.class;
    }


    private <T extends EntityPlotter> T readItem(Element curEl,Class<?> itemClass){
        try {
            Map<String,String> styleList = readKeyPairAttribute(curEl,"style");
            int x = getIntValue(curEl,"x"     ,styleList);
            int y = getIntValue(curEl,"y"     ,styleList);
            int w = getIntValue(curEl,"width" ,styleList);
            int h = getIntValue(curEl,"height",styleList);
            int curve = getIntValue(curEl,"curve",styleList);
            boolean shadow = getBooleanValue(curEl,"shadow",styleList);
            boolean reflect = getBooleanValue(curEl,"reflect",styleList);
            String id = curEl.getAttributeValue("id");
            String comment = curEl.getText().trim();
            String bgImage = getStringValue(curEl,"bgImage",styleList);
            String bgColor = getStringValue(curEl,"bgColor",styleList);
            String borderColor = getStringValue(curEl,"borderColor",styleList);
            boolean connTo = getBooleanValue(curEl,"to",styleList);
            boolean connFrom = getBooleanValue(curEl,"from",styleList);
            Class[] classes = {Integer.TYPE,Integer.TYPE,Integer.TYPE,Integer.TYPE};
            Constructor constr =  itemClass.getDeclaredConstructor(classes);
            T newItem = (T)constr.newInstance(x,y,w,h);
            newItem.setId(id);
            newItem.setContents(comment);
            newItem.activateConnectionTo(connTo);
            newItem.activateConnectionFrom(connFrom);
            newItem.setDropShadow(shadow);
            newItem.setDropReflection(reflect);
            readShape(newItem,curEl);
            newItem.setTitle(curEl.getAttributeValue("title"));
            if (bgImage!=null) {
                String imagePath = imagesPath != null ? imagesPath + bgImage : bgImage;
                newItem.setBackgroundImage(new ImageIcon(imagePath).getImage());
            }
            if(bgColor!=null)
                newItem.setContentBackground(parseColor(bgColor));
            if(borderColor!=null)
                newItem.setBorderColor(parseColor(bgColor));
            if (curve!=0) newItem.setCornerCurve(curve);
            return newItem;

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    protected <T extends EntityPlotter> void readShape(T item,Element curEl){
        PlotShape target = PlotShape.Free;
        String shapeAtt = curEl.getAttributeValue("shape");
        if (shapeAtt==null) item.setShape(target);
        try{
            target = PlotShape.valueOf(shapeAtt);
        }catch (IllegalArgumentException ex){ item.setShape(PlotShape.Free);}
        item.setShape(target);

    }

    /**
     * This method to be extended by any child for further properties
     * @param curEl
     * @param item
     * @param styleList
     * @param <T>
     */
    protected <T extends EntityPlotter> void postProcessItem(Element curEl,T item,Map<String,String> styleList){

    }

    private Map<String,String> readKeyPairAttribute(Element el, String att){
        String value = el.getAttributeValue(att);
        if (value==null) return Map.of(el.getName(),"Nothing");
        if (!value.contains(";"))
            return Map.of(el.getName(),value);
        Map<String,String> tags = new HashMap<>();
        Stream.of(value.split(";")).forEach(curToken ->{
            String[] parts = curToken.split(":");
            tags.put(parts[0],parts[1]);
        });
        return tags;
    }

    private boolean getBooleanValue(Element el, String att,Map<String,String> list){
        String val = getStringValue(el,att,list);
        if (val!=null) return Boolean.parseBoolean(val);
        return false;
    }

    private int getIntValue(Element el, String att,Map<String,String> list){
        String val = getStringValue(el,att,list);
        if (val!=null) return Integer.parseInt(val);
        return 0;
    }

    private String getStringValue(Element el, String att,Map<String,String> list){
        if (el.getAttributeValue(att)!=null) return el.getAttributeValue(att);
        if (list.containsKey(att)) return list.get(att);
        return null;

    }



    @Override
    public <T extends EntityPlotter>  List<EntityConnector> loadConnections(Set<T> definedEntities) {
        List<EntityConnector> result = new ArrayList<>();

        if (rootEl.getChild("connections")!=null){
            rootEl.getChild("connections").getChildren().forEach(curEl ->{
                //TODO need to check the reason why the connector is picking the Orthogonal
                T fromEnt = get(curEl.getAttributeValue("from"),definedEntities).get();
                T toEnt = get(curEl.getAttributeValue("to"),definedEntities).get();
                if ((fromEnt!=null)&&(toEnt!=null)){
                    String colorText = curEl.getAttributeValue("color");
                    String comment  = curEl.getText();
                    if (colorText!=null){
                        Color color = parseColor(colorText);
                        if (comment!=null){
                            result.add(new OrthogonalEntityConnector(fromEnt,toEnt,color,comment));
                        }else{
                            result.add(new OrthogonalEntityConnector(fromEnt,toEnt,color));
                        }
                    }else{
                        result.add(new OrthogonalEntityConnector(fromEnt,toEnt));
                    }
                }
            });
        }

        return result;
    }
    private Color parseColor(String colorValue){
        if (colormap.containsKey(colorValue.toLowerCase()))
            return colormap.get(colorValue.toLowerCase());
        throw new RuntimeException("Unsupported color value " + colorValue);
    }

    private <T extends EntityPlotter>  Optional<T> get(String id, Set<T> definedEntities){
        if (id==null) return Optional.empty();
        return definedEntities.stream().filter(curEl -> curEl.getId().equals(id)).findFirst();
    }
    static{
        colormap = Map.of("black",Color.black,"blue",Color.blue,"red",Color.red,"yellow",Color.yellow,"gray",Color.gray,"lightgray",Color.lightGray);

    }
}
