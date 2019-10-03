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
        new EntityPlotTester().testFlowChartDiagram();
    }
    @Test
    public void testFlowChartDiagram() throws Exception{
        JFrame frm = new JFrame("Billing Configuration");
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frm.setSize(1000, 800);

        // Initializing the plotter here
        FlowChartPlotter pnlProductPlot = new FlowChartPlotter(new EPFlowChartXmlReader("/media/mfjamil/Ubuntu_second/work/java/smyld-java/apps/ep/EntityPlot/src/test/resources/files/EPFlowChart.xml"));
        JScrollPane sp = new JScrollPane(pnlProductPlot);
        pnlProductPlot.setScrollContainer(sp);
        sp.getViewport().add(pnlProductPlot);
        frm.getContentPane().add(sp);
        frm.setVisible(true);
        //Thread.sleep(5000);


    }
}
