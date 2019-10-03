package org.smyld.app.pe.flowchart.source;

import org.smyld.app.pe.EntityConnector;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;

import java.util.List;
import java.util.Set;

public interface FlowChartReader {

    Set<EntityBasicFlowChart> loadEntities();
    List<EntityConnector> loadConnections(Set<EntityBasicFlowChart> definedEntties);
}
