package org.smyld.app.ep;

import org.junit.Assert;
import org.junit.Test;
import org.smyld.app.pe.EntityConnector;
import org.smyld.app.pe.EntityPlotter;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;
import org.smyld.app.pe.flowchart.source.EPFlowChartXmlReader;

import java.util.List;
import java.util.Set;

public class EPSourceTester {


    @Test
    public void testPEFlowchartXmlReader()throws Exception{
        EPFlowChartXmlReader reader = new EPFlowChartXmlReader("src/test/resources/files/EPFlowChart.xml");
        Set<EntityPlotter> entities = reader.loadEntities();
        entities.forEach(curItem -> System.out.println(curItem));
        Assert.assertEquals(6,entities.size());
        List<EntityConnector> conns =  reader.loadConnections(entities);
        conns.forEach(curItem -> System.out.println(curItem));
        Assert.assertEquals(5,conns.size());
    }
}
