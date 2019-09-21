package com.smyld.app.entityplot.flowchart.elements;

import com.smyld.app.entityplot.PlotShape;
import com.smyld.app.entityplot.flowchart.EntityBasicFlowChart;

public class FlowChartTerminator extends EntityBasicFlowChart {
	public FlowChartTerminator(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.RoundedRectangle,ChartElement.Terminator);
	}
}
