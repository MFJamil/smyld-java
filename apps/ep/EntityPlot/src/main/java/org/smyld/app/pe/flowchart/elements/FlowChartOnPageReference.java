package org.smyld.app.pe.flowchart.elements;

import org.smyld.app.pe.PlotShape;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;

public class FlowChartOnPageReference extends EntityBasicFlowChart {
	public FlowChartOnPageReference(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.Circle,ChartElement.OnPageReference);
	}

}
