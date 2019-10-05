package org.smyld.app.pe.flowchart.source;

import org.jdom2.Element;
import org.smyld.app.pe.ChartType;
import org.smyld.app.pe.EntityPlotter;
import org.smyld.app.pe.flowchart.FlowChartElementsEnum;
import org.smyld.app.pe.source.EPChartXmlReader;

public class EPFlowChartXmlReader extends EPChartXmlReader {
    public EPFlowChartXmlReader(String xmlFileName) throws Exception {
        super(xmlFileName);
    }

    protected void checkFileType() {
        if (ChartType.valueOf(rootEl.getName()) != ChartType.epflowchart)
            throw new RuntimeException("Unsupported Chart type " + rootEl.getName());
    }

    protected Class<?> getImplClass(String type) {
        return FlowChartElementsEnum.valueOf(type).getImplClass();
    }

    protected <T extends EntityPlotter> void readShape(T item, Element curEl) {
        // Should skip this since the flow chart classes will set it
    }
}
