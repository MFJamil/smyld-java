package org.smyld.app.pe.source;

import org.smyld.app.pe.EntityConnector;
import org.smyld.app.pe.EntityPlotter;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;

import java.util.List;
import java.util.Set;

public interface ChartReader {

    <T extends EntityPlotter> Set<T> loadEntities();
    <T extends EntityPlotter> List<EntityConnector> loadConnections(Set<T> definedEntities);
}
