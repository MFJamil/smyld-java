package org.smyld.app.pe.flowchart;

import org.smyld.app.pe.flowchart.elements.*;

public enum FlowChartElementsEnum {
    Start1(FlowChartStart1.class),
    Start2(FlowChartStart2.class),
    Terminator(FlowChartTerminator.class),
    Process(FlowChartProcess.class),
    Decision(FlowChartDecision.class),
    OnPageReference(FlowChartOnPageReference.class);
    Class<?> implClass;

    FlowChartElementsEnum(Class<?> implClass){
        this.implClass = implClass;
    }

    public Class<?> getImplClass() {
        return implClass;
    }
}
