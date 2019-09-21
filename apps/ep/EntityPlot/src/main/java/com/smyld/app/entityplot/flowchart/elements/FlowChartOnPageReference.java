package com.smyld.app.entityplot.flowchart.elements;

import com.smyld.app.entityplot.PlotShape;
import com.smyld.app.entityplot.flowchart.EntityBasicFlowChart;

public class FlowChartOnPageReference extends EntityBasicFlowChart {
	public FlowChartOnPageReference(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.Circle,ChartElement.OnPageReference);
	}

}
