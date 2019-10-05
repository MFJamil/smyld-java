/*
 *
 *  * Copyright smyld.org Authors.
 *  *
 *  * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 *
 */

package org.smyld.app.pe.builder;

import org.jdom2.Element;
import org.smyld.app.pe.ChartType;
import org.smyld.app.pe.GUIPlotter;
import org.smyld.app.pe.flowchart.FlowChartPlotter;
import org.smyld.app.pe.flowchart.source.EPFlowChartXmlReader;
import org.smyld.app.pe.source.EPChartXmlReader;
import org.smyld.xml.XMLUtil;

/**
 *
 */
public class EPChartBuilder {

   private  static EPChartBuilder instance;
   public EPChartBuilder(){}
   public static EPChartBuilder getInstance(){
       if (instance==null)
           instance = new EPChartBuilder();
       return instance;

   }

    public GUIPlotter buildFromXML(String xmlFileName)throws Exception{
        Element rootEl = XMLUtil.getRootNode(xmlFileName);
        switch (ChartType.valueOf(rootEl.getName())) {
            case epchart:
                return new GUIPlotter(new EPChartXmlReader(xmlFileName));
            case epflowchart:
                return new FlowChartPlotter(new EPFlowChartXmlReader(xmlFileName));
        }
        throw new RuntimeException("Unsupported file type");
    }
}
