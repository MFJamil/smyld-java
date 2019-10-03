package org.smyld.app.pe.flowchart.source;

import org.jdom2.Element;
import org.smyld.app.pe.EntityConnector;
import org.smyld.app.pe.OrthogonalEntityConnector;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;
import org.smyld.app.pe.flowchart.FlowChartElementsEnum;
import org.smyld.xml.XMLUtil;

import javax.swing.*;
import java.awt.Color;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;


public class EPFlowChartXmlReader implements FlowChartReader {
    Element rootEl;
    String imagesPath;
    static Map<String, Color> colormap;
    public EPFlowChartXmlReader(String xmlFileName) throws Exception {
        rootEl =  XMLUtil.getRootNode(xmlFileName);
        init();
    }

    private void init(){
        if (rootEl!=null){
            imagesPath  = rootEl.getChildText("images");

        }
    }

    @Override
    public Set<EntityBasicFlowChart> loadEntities() {
        Set<EntityBasicFlowChart> result = new HashSet<>();

        if (rootEl.getChild("elements")!=null){
            rootEl.getChild("elements").getChildren().forEach(curEl ->{
                result.add(readItem(curEl,FlowChartElementsEnum.valueOf(curEl.getAttributeValue("type")).getImplClass())) ;
            });
        }

        return result;
    }
    private <T extends EntityBasicFlowChart> T readItem(Element curEl,Class<?> itemClass){
        try {
            int x = XMLUtil.getIntAttribute(curEl,"x");
            int y = XMLUtil.getIntAttribute(curEl,"y");
            int w = XMLUtil.getIntAttribute(curEl,"width");
            int h = XMLUtil.getIntAttribute(curEl,"height");
            boolean shadow = XMLUtil.getBooleanAttribute(curEl,"shadow");
            boolean reflect = XMLUtil.getBooleanAttribute(curEl,"reflect");
            String id = curEl.getAttributeValue("id");
            String comment = curEl.getText().trim();
            String bgImage = curEl.getAttributeValue("bgImage");
            boolean connTo = XMLUtil.getBooleanAttribute(curEl,"connTo");
            boolean connFrom = XMLUtil.getBooleanAttribute(curEl,"connFrom");
            Class[] classes = {Integer.TYPE,Integer.TYPE,Integer.TYPE,Integer.TYPE};
            Constructor constr =  itemClass.getDeclaredConstructor(classes);
            T newItem = (T)constr.newInstance(x,y,w,h);
            newItem.setId(id);
            newItem.setContents(comment);
            newItem.activateConnectionTo(connTo);
            newItem.activateConnectionFrom(connFrom);
            newItem.setDropShadow(shadow);
            newItem.setDropReflection(reflect);
            if (bgImage!=null) {
                String imagePath = imagesPath != null ? imagesPath + bgImage : bgImage;
                newItem.setBackgroundImage(new ImageIcon(imagePath).getImage());
            }
            return newItem;

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }



    @Override
    public List<EntityConnector> loadConnections(Set<EntityBasicFlowChart> definedEntities) {
        List<EntityConnector> result = new ArrayList<>();

        if (rootEl.getChild("connections")!=null){
            rootEl.getChild("connections").getChildren().forEach(curEl ->{
                //TODO need to check the reason why the connector is picking the Orthogonal
                EntityBasicFlowChart fromEnt = get(curEl.getAttributeValue("from"),definedEntities).get();
                EntityBasicFlowChart toEnt = get(curEl.getAttributeValue("to"),definedEntities).get();
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

    private Optional<EntityBasicFlowChart> get(String id, Set<EntityBasicFlowChart> definedEntities){
        if (id==null) return Optional.empty();
        return definedEntities.stream().filter(curEl -> curEl.getId().equals(id)).findFirst();
    }
    static{
        colormap = Map.of("black",Color.black,"blue",Color.blue,"red",Color.red,"yellow",Color.yellow,"gray",Color.gray);

    }
}
