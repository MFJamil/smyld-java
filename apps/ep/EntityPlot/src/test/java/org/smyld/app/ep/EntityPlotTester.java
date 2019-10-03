package org.smyld.app.ep;

import org.junit.Test;
import org.smyld.app.pe.flowchart.FlowChartPlotter;
import org.smyld.app.pe.flowchart.source.EPFlowChartXmlReader;

import javax.swing.*;

public class EntityPlotTester {

    @Test
    public void TestNormalDiagram(){


    }

    public static void main(String[] args)throws Exception {
        String filesPath = "/media/mfjamil/Ubuntu_second/work/java/smyld-java/apps/ep/EntityPlot/src/test/resources/files/";
        new EntityPlotTester().testFlowChartDiagram(filesPath + "EPFlowChart.xml");
        new EntityPlotTester().testFlowChartDiagram(filesPath + "EPFlowChart_NoShadow.xml");
        new EntityPlotTester().testFlowChartDiagram(filesPath + "EPFlowChart_NoShadow_NoReflect.xml");
        new EntityPlotTester().testFlowChartDiagram(filesPath + "EPFlowChart_NoShadow_NoReflect_NoImage.xml");
    }
    @Test
    public void testFlowChartDiagram(String filename) throws Exception{
        JFrame frm = new JFrame("Billing Configuration");
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.setSize(1000, 800);

        // Initializing the plotter here
        FlowChartPlotter pnlProductPlot = new FlowChartPlotter(new EPFlowChartXmlReader(filename));
        JScrollPane sp = new JScrollPane(pnlProductPlot);
        pnlProductPlot.setScrollContainer(sp);
        sp.getViewport().add(pnlProductPlot);
        frm.getContentPane().add(sp);
        frm.setVisible(true);
        //Thread.sleep(5000);


    }
}
