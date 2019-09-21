package org.smyld.app.entityplot.flowchart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import org.smyld.app.entityplot.EntityPlotterImpl;
import org.smyld.app.entityplot.GradientPlot;
import org.smyld.app.entityplot.PlotShape;
import org.smyld.app.entityplot.GradientPlot.GradientDirection;

/**
 * @author jamil
 *
 */
public class EntityBasicFlowChart extends EntityPlotterImpl {

	protected GradientPlot bodyGrad          = new GradientPlot(new Color(83,105,174),new Color(199,211,231));
	
	//protected GradientPlot bodyHighlightGrad = new GradientPlot(Color.RED,Color.WHITE);
	//protected GradientPlot bodyConnectedGrad = new GradientPlot(Color.BLUE,Color.WHITE);
	protected GradientPlot bodyHighlightGrad = new GradientPlot(new Color(180,32,32),new Color(221,136,136));
	protected GradientPlot bodyConnectedGrad = new GradientPlot(new Color(29,29,123),new Color(90,90,255));

	protected ChartElement element;
	public EntityBasicFlowChart(int x, int y, int width, int height) {
		super(x, y, width, height);
		init();
	}

	public EntityBasicFlowChart(int x, int y, int width, int height, PlotShape shape,
			Color contentBGColor, Color borderColor, String title) {
		super(x, y, width, height, shape, contentBGColor, borderColor, title);
		init();
	}

	public EntityBasicFlowChart(int x, int y, int width, int height, PlotShape shape,
			Color contentBGColor, Color borderColor) {
		super(x, y, width, height, shape, contentBGColor, borderColor);
		init();
	}

	public EntityBasicFlowChart(int x, int y, int width, int height, PlotShape shape,
			ChartElement element) {
		super(x, y, width, height);
		this.shape =shape;
		this.element = element;
		init();
	}
	public EntityBasicFlowChart(int x, int y, int width, int height, ChartElement element) {
		super(x, y, width, height);
		this.element = element;
		init();
	}
	
	private void init(){
		bodyGrad.setDirection(GradientDirection.ButtomToTop);
		bodyHighlightGrad.setDirection(GradientDirection.ButtomToTop);
		bodyConnectedGrad.setDirection(GradientDirection.ButtomToTop);
		showTitle                   = false;
		connectorColor              = new Color(83,105,174);
		contentsHorizontalAlignment = ALIGNMENT_CENTER;
		contentsVerticalAlignment   = ALIGNMENT_MIDDLE;
		cornerCurve                 = 25;
		contentFontColor            = Color.BLACK;
		contentsFont                = new Font("Arial",Font.BOLD,12);
		borderWidth                 = 1;
		showBorder                  = false;
	}
	protected GradientPlot getContentGradiant(){
		if (doShowConnecting){
			contentFontColor            = Color.YELLOW;
			return bodyConnectedGrad;
		
		}else if (doHighlight){
			contentFontColor            = Color.WHITE;
			return bodyHighlightGrad;
		}else{
			contentFontColor            = Color.BLACK;
			return bodyGrad;
		}
		
	}
	protected void drawContentsBackground(Graphics2D g){
		
		int bw = borderWidth;
		if (!isShowBorder()) bw = 0;

		g.setPaint(getContentGradiant().generate(x,y,width,height));

		switch(shape){
			case Circle:
				g.fillArc(x, y, width, width, 0, 360);
				break;
			case Rectangle:
				g.fillRect(x+bw, y+bw, width-(bw*2), height-(bw*2));
				break;

			case RoundedRectangle:
				g.fillRoundRect(x+bw, y+bw, width-(bw*2), height-(bw*2),cornerCurve,cornerCurve);
				break;
			case Oval:
				g.fillOval(x, y, width, height);
				break;
				
		}
		super.drawContentsBackground(g);
	}
	
	
	public enum ChartElement{
		Start1(0),
		Start2(1),
		ManualInput(2),
		PaperTape(3),
		Card(4),
		Document(5),
		Decision(6),
		Process(7),
		Data(8),
		DirectData(9),
		SequentialData(10),
		InternalStorage(11),
		StoredData(12),
		PredefinedProcess(13),
		Preparation(14),
		ManualOperation(15),
		Display(16),
		Delay(17),
		OffPageReference(18),
		OnPageReference(19),
		LoopLimit(20),
		Terminator(21);
		int order = 0;
		ChartElement(int order){this.order = order;}
		
		
		
	}
	
	
	

}
