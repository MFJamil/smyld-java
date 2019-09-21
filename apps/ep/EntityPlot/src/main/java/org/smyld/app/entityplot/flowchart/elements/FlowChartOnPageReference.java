package org.smyld.app.entityplot.flowchart.elements;

import org.smyld.app.entityplot.PlotShape;
import org.smyld.app.entityplot.flowchart.EntityBasicFlowChart;

public class FlowChartOnPageReference extends EntityBasicFlowChart {
	public FlowChartOnPageReference(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.Circle,ChartElement.OnPageReference);
	}

}
