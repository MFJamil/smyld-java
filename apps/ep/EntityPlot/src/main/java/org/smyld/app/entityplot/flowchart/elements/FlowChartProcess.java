package org.smyld.app.entityplot.flowchart.elements;

import org.smyld.app.entityplot.PlotShape;
import org.smyld.app.entityplot.flowchart.EntityBasicFlowChart;

public class FlowChartProcess extends EntityBasicFlowChart {
	public FlowChartProcess(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.Rectangle,ChartElement.Process);
	}

}
