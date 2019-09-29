package org.smyld.app.pe.flowchart.elements;

import org.smyld.app.pe.PlotShape;
import org.smyld.app.pe.flowchart.EntityBasicFlowChart;

public class FlowChartProcess extends EntityBasicFlowChart {
	public FlowChartProcess(int x, int y, int width, int height) {
		super(x,y,width,height,PlotShape.Rectangle,ChartElement.Process);
	}

}
