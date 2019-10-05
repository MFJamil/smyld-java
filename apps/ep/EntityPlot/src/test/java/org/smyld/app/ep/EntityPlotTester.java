package org.smyld.app.ep;

import org.junit.Test;
import org.smyld.app.pe.GUIPlotter;
import org.smyld.app.pe.builder.EPChartBuilder;
import org.smyld.app.pe.flowchart.FlowChartPlotter;
import org.smyld.app.pe.flowchart.source.EPFlowChartXmlReader;
import org.smyld.app.pe.source.EPChartXmlReader;

import javax.swing.*;
import java.util.stream.Stream;

public class EntityPlotTester {


    public static void main(String[] args)throws Exception {
        testCharts();
    }

    private static void testCharts(){
        String filesPath = "/media/mfjamil/Ubuntu_second/work/java/smyld-java/apps/ep/EntityPlot/src/test/resources/files/";
        String[] testFiles = {
                "EPChart_css.xml"
      /*        ,"EPFlowChart_css.xml"
                ,"EPFlowChart.xml"
                ,"EPFlowChart_NoShadow.xml"
                ,"EPFlowChart_NoShadow_NoReflect.xml"
                ,"EPFlowChart_NoShadow_NoReflect_NoImage.xml"
        */
        };
        Stream.of(testFiles).forEach(curFile -> {
            try {
                new EntityPlotTester().testChart(EPChartBuilder.getInstance().buildFromXML(filesPath + curFile));
            } catch (Exception e) { e.printStackTrace();}
        });


    }

    @Test
    public  void testChart(GUIPlotter pnlProductPlot){
        JFrame frm = new JFrame("Billing Configuration");
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.setSize(1000, 800);
        JScrollPane sp = new JScrollPane(pnlProductPlot);
        pnlProductPlot.setScrollContainer(sp);
        sp.getViewport().add(pnlProductPlot);
        frm.getContentPane().add(sp);
        frm.setVisible(true);
    }
}
