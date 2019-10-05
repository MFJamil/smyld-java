package org.smyld.app.ep;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.smyld.app.pe.*;
import org.smyld.app.pe.flowchart.FlowChartPlotter;
import org.smyld.app.pe.flowchart.elements.FlowChartDecision;
import org.smyld.app.pe.flowchart.elements.FlowChartStart1;
import org.smyld.app.pe.flowchart.elements.FlowChartTerminator;

public class GUITester implements EntityPlotListener {

	public GUITester(){
		try {
			testFlowChart();
			testEntityPlotter();
			//testPrinting();
			//testBillingPlotter();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GUITester();
	}
	private void testEntityPlotter(){
		// TODO Auto-generated method stub
		JFrame frm = new JFrame("Billing Configuration");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(1000, 800);
		
		// Initializing the plotter here
		GUIPlotter pnlProductPlot = new GUIPlotter();
		EntityPlotterImpl valueCalc = new EntityPlotterImpl(80,100,200,100,PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLUE,"Value Calculation");
		valueCalc.setContents("Unit Size = 1000000\nUnit Type = Started \nPrice Per Unit = 5");
		valueCalc.setId("valueCalc");
		valueCalc.setCornerCurve(15);
		valueCalc.activateConnectionTo(true);
		valueCalc.activateConnectionFrom(false);
		EntityPlotterImpl prodSpot = new EntityPlotterImpl(550,50,120,50,PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLACK,"360t Product  for long title-----");
		prodSpot.setContents("Type = Spot");
		prodSpot.setId("prodSpot");
		//prodSpot.setContents("Hello this is me how are you, I am fine\n I just want to say that this is a very long text\n I should be expanded for sure in width\n and in length");
		//prodSpot.setAutoExpand(true);
		prodSpot.activateConnectionTo(false);
		prodSpot.activateConnectionFrom(true);
		
		EntityPlotterImpl prodSwap = new EntityPlotterImpl(550,150,120,50, PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodSwap.setContents("Type = Swap");
		prodSwap.setId("prodSwap");
		prodSwap.activateConnectionTo(false);
		prodSwap.activateConnectionFrom(true);

		EntityPlotterImpl prodForward = new EntityPlotterImpl(550,250,120,50,PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodForward.setContents("Type = Forward");
		prodForward.setId("prodForward");
		prodForward.activateConnectionTo(false);
		prodForward.activateConnectionFrom(true);

		EntityPlotterImpl prodSpotSS = new EntityPlotterImpl(550,150,120,50,PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		
		prodSpotSS.setId("prodSpotSS");
		prodSpotSS.activateConnectionTo(false);
		prodSpotSS.activateConnectionFrom(true);
		prodSpotSS.setContents("Type = Spot Super Sonic");

		EntityPlotterImpl valueCalc1 = new EntityPlotterImpl(80,100,200,100,PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLUE,"Value Calculation");
		valueCalc1.setContents("Unit Size = 2500000\nUnit Type = Completed \nPrice Per Unit = 12");
		valueCalc1.setId("valueCalc1");
		valueCalc1.activateConnectionTo(true);
		valueCalc1.activateConnectionFrom(false);

		EntityPlotterImpl prodOption = new EntityPlotterImpl(550,150,120,50,PlotShape.RoundedRectangle,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodOption.setContents("Type = Option");
		prodOption.setId("prodOption");
		prodOption.activateConnectionTo(false);
		prodOption.activateConnectionFrom(true);
		

		
		pnlProductPlot.addEntity("valueCalc1", valueCalc1);
		pnlProductPlot.addEntity("prodSpotSS", prodSpotSS);
		pnlProductPlot.addEntity("prodOption", prodOption);
		pnlProductPlot.addEntity("valueCalc" , valueCalc);
		pnlProductPlot.addEntity("spot"      , prodSpot);
		pnlProductPlot.addEntity("swap"      , prodSwap );
		pnlProductPlot.addEntity("forward"   , prodForward );

		pnlProductPlot.connectEntities(prodSpotSS  , valueCalc1);
		pnlProductPlot.connectEntities(prodOption  , valueCalc1);
		pnlProductPlot.connectEntities(prodOption  , valueCalc);
		pnlProductPlot.connectEntities(prodSpot    , valueCalc);
		pnlProductPlot.connectEntities(prodSwap    , valueCalc);
		pnlProductPlot.connectEntities(prodForward , valueCalc);
		pnlProductPlot.emptyUndoActions();
		
		
		//*
		JScrollPane sp = new JScrollPane(pnlProductPlot);
		sp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		sp.setAutoscrolls(true);
		pnlProductPlot.setScrollContainer(sp);
		
		//sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		sp.getViewport().add(pnlProductPlot);
		//*/
		//frm.getContentPane().add(pnlProductPlot);
		frm.getContentPane().add(sp);
		frm.setVisible(true);
		pnlProductPlot.setDoubleBuffered(true);
		pnlProductPlot.addEntityPlotListener(this);
		pnlProductPlot.showToolbar= false;
		pnlProductPlot.setShowConnectedEntities(true);
		valueCalc.setX(valueCalc.getX() + 100);
		//manager.doLayout(pnlProductPlot.entities, pnlProductPlot.conectors);
		//frm.repaint();
		
		
		
	}
	private void testFlowChart(){
		JFrame frm = new JFrame("Billing Configuration");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(1000, 800);
		
		// Initializing the plotter here
		FlowChartPlotter pnlProductPlot = new FlowChartPlotter();
		
		// Entities creation
		FlowChartStart1 start = new FlowChartStart1(80,100,80,20);
		start.setContents("Trade");
		start.setId("trade");
		start.activateConnectionTo(false);
		start.activateConnectionFrom(true);
		pnlProductPlot.addEntity("trade", start);
		
		FlowChartDecision dsSimon = new FlowChartDecision(80,100,100,80);
		dsSimon.setContents("Simon");
		dsSimon.setId("simon");
		dsSimon.activateConnectionTo(true);
		dsSimon.activateConnectionFrom(true);
		dsSimon.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/users.png").getImage());
		pnlProductPlot.addEntity("simon", dsSimon);

		FlowChartDecision dsMarkus = new FlowChartDecision(80,100,100,80);
		dsMarkus.setContents("Markus");
		dsMarkus.setId("markus");
		dsMarkus.activateConnectionTo(true);
		dsMarkus.activateConnectionFrom(true);
		dsMarkus.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/users.png").getImage());
		pnlProductPlot.addEntity("markus", dsMarkus);

		FlowChartDecision dsProd1 = new FlowChartDecision(80,100,100,80);
		dsProd1.setContents("Spot");
		dsProd1.setId("dsProd1");
		dsProd1.activateConnectionTo(true);
		dsProd1.activateConnectionFrom(true);
		dsProd1.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/objects-48.png").getImage());
		pnlProductPlot.addEntity("dsProd1", dsProd1);

		FlowChartDecision dsProd2 = new FlowChartDecision(80,100,100,80);
		dsProd2.setContents("Fx Swap");
		dsProd2.setId("dsProd2");
		dsProd2.activateConnectionTo(true);
		dsProd2.activateConnectionFrom(true);
		dsProd2.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/objects-48.png").getImage());
		pnlProductPlot.addEntity("dsProd2", dsProd2);

		/*
		FlowChartProcess process = new FlowChartProcess(80,100,100,40);
		process.setContents("Process");
		process.setId("process");
		process.activateConnectionTo(true);
		process.activateConnectionFrom(true);
		pnlProductPlot.addEntity("process", process);
		icon_AddressBook
		*/
		FlowChartDecision dsAdd1 = new FlowChartDecision(80,100,100,80);
		dsAdd1.setContents("Frankfurt");
		dsAdd1.setId("add1");
		dsAdd1.activateConnectionTo(true);
		dsAdd1.activateConnectionFrom(true);
		dsAdd1.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/icon_AddressBook.png").getImage());
		pnlProductPlot.addEntity("add1", dsAdd1);
		
		FlowChartDecision dsAdd2 = new FlowChartDecision(80,100,100,80);
		dsAdd2.setContents("Singapore");
		dsAdd2.setId("add2");
		dsAdd2.activateConnectionTo(true);
		dsAdd2.activateConnectionFrom(true);
		dsAdd2.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/icon_AddressBook.png").getImage());
		pnlProductPlot.addEntity("add2", dsAdd2);

		FlowChartTerminator inv1 = new FlowChartTerminator(80,100,80,40);
		inv1.setContents("Invoice 1");
		inv1.setId("inv1");
		inv1.activateConnectionTo(true);
		inv1.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/bookmark-new-32.png").getImage());
		inv1.activateConnectionFrom(false);
		pnlProductPlot.addEntity("inv1", inv1);
		
		FlowChartTerminator inv2 = new FlowChartTerminator(80,100,80,40);
		inv2.setContents("Invoice 2");
		inv2.setId("inv2");
		inv2.activateConnectionTo(true);
		inv2.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/bookmark-new-32.png").getImage());
		inv2.activateConnectionFrom(false);
		pnlProductPlot.addEntity("inv2", inv2);
		
		FlowChartTerminator inv3 = new FlowChartTerminator(80,100,80,40);
		inv3.setContents("Invoice 3");
		inv3.setId("inv3");
		inv3.activateConnectionTo(true);
		inv3.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/bookmark-new-32.png").getImage());
		inv3.activateConnectionFrom(false);
		pnlProductPlot.addEntity("inv3", inv3);

		FlowChartTerminator inv4 = new FlowChartTerminator(80,100,80,40);
		inv4.setContents("Invoice 4");
		inv4.setId("inv4");
		inv4.activateConnectionTo(true);
		inv4.setBackgroundImage(new ImageIcon("/home/jamil/workspace/tools-billing-interface/sources/images/bookmark-new-32.png").getImage());
		inv4.activateConnectionFrom(false);
		pnlProductPlot.addEntity("inv4", inv4);
		
		// Connection test
		pnlProductPlot.connectEntities(start, dsSimon); 
		pnlProductPlot.connectEntities(dsSimon,dsMarkus,Color.BLACK,"No");
		pnlProductPlot.connectEntities(dsSimon,inv1    ,Color.BLUE,"Yes");
		pnlProductPlot.connectEntities(dsMarkus,dsProd1,Color.BLACK,"No");
		pnlProductPlot.connectEntities(dsMarkus,inv2   ,Color.BLUE,"Yes");
		pnlProductPlot.connectEntities(dsProd1,dsProd2 ,Color.BLACK,"No");
		pnlProductPlot.connectEntities(dsProd1,inv3    ,Color.BLUE,"Yes");
		
		JScrollPane sp = new JScrollPane(pnlProductPlot);
		pnlProductPlot.setScrollContainer(sp);
		sp.getViewport().add(pnlProductPlot);
		frm.getContentPane().add(sp);
		frm.setVisible(true);
		
	}
	
	private void testPrinting() throws Exception{
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new Printable(){
			public int print(Graphics g, PageFormat pf,int pageIndex) throws PrinterException{
				System.out.println("Call made to the printable interface");
				((Graphics2D)g).translate(pf.getImageableX(), pf.getImageableY());
				g.drawString("My Test",20,20);
				return PAGE_EXISTS;
			}


		});
		
		boolean doPrint = job.printDialog();
		if(doPrint){
			job.print();
		}

	}
	public void testBillingPlotter(){
		/*
		JFrame frm = new JFrame("Billing Configuration");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(1000, 800);
		

		BillingEntityPlot pnlProductPlot = new BillingEntityPlot();
		EntityPlotterImpl valueCalc = new EntityPlotterImpl(80,100,200,100,1,Color.LIGHT_GRAY,Color.BLUE,"Value Calculation");
		valueCalc.setContents("Unit Size = 1000000\nUnit Type = Started \nPrice Per Unit = 5");
		valueCalc.setId("valueCalc");
		valueCalc.activateConnectionTo(true);
		valueCalc.activateConnectionFrom(false);
		EntityPlotterImpl prodSpot = new EntityPlotterImpl(550,50,120,50,1,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodSpot.setContents("Type = Spot");
		prodSpot.setId("prodSpot");
		prodSpot.setContents("Hello this is me how are you, I am fine\n I just want to say that this is a very long text\n I should be expanded for sure in width\n and in length");
		//prodSpot.setAutoExpand(true);
		prodSpot.activateConnectionTo(false);
		prodSpot.activateConnectionFrom(true);
		
		EntityPlotterImpl prodSwap = new EntityPlotterImpl(550,150,120,50,1,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodSwap.setContents("Type = Swap");
		prodSwap.setId("prodSwap");
		prodSwap.activateConnectionTo(false);
		prodSwap.activateConnectionFrom(true);

		EntityPlotterImpl prodForward = new EntityPlotterImpl(550,250,120,50,1,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodForward.setContents("Type = Forward");
		prodForward.setId("prodForward");
		prodForward.activateConnectionTo(false);
		prodForward.activateConnectionFrom(true);

		EntityPlotterImpl prodSpotSS = new EntityPlotterImpl(550,150,120,50,1,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodSpotSS.setContents("Type = Spot Super Sonic");
		prodSpotSS.setId("prodSpotSS");
		prodSpotSS.activateConnectionTo(false);
		prodSpotSS.activateConnectionFrom(true);

		EntityPlotterImpl valueCalc1 = new EntityPlotterImpl(80,100,200,100,1,Color.LIGHT_GRAY,Color.BLUE,"Value Calculation");
		valueCalc1.setContents("Unit Size = 2500000\nUnit Type = Completed \nPrice Per Unit = 12");
		valueCalc1.setId("valueCalc1");
		valueCalc1.activateConnectionTo(true);
		valueCalc1.activateConnectionFrom(false);

		EntityPlotterImpl prodOption = new EntityPlotterImpl(550,150,120,50,1,Color.LIGHT_GRAY,Color.BLACK,"360t Product");
		prodOption.setContents("Type = Option");
		prodOption.setId("prodOption");
		prodOption.activateConnectionTo(false);
		prodOption.activateConnectionFrom(true);

		
		pnlProductPlot.addEntity("valueCalc1", valueCalc1);
		pnlProductPlot.addEntity("prodSpotSS", prodSpotSS);
		pnlProductPlot.addEntity("prodOption", prodOption);
		pnlProductPlot.addEntity("valueCalc" , valueCalc);
		pnlProductPlot.addEntity("spot"      , prodSpot);
		pnlProductPlot.addEntity("swap"      , prodSwap );
		pnlProductPlot.addEntity("forward"   , prodForward );
		pnlProductPlot.connectEntities(prodSpotSS , valueCalc1);
		pnlProductPlot.connectEntities(prodOption , valueCalc1);
		pnlProductPlot.connectEntities(prodSpot   , valueCalc);
		pnlProductPlot.connectEntities(prodSwap   , valueCalc);
		pnlProductPlot.connectEntities(prodForward,valueCalc);
		
		JScrollPane sp = new JScrollPane();
		sp.setAutoscrolls(true);
		pnlProductPlot.setContainer(sp);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.getViewport().add(pnlProductPlot);
		//frm.getContentPane().add(pnlProductPlot);
		frm.getContentPane().add(sp);
		frm.setVisible(true);
		pnlProductPlot.addEntityPlotListener(this);
		*/
		
	}
	public void entityDoubleClicked(EntityPlotter entity) {
		System.out.println("Entity : " + entity.getId() + " was double clicked ..");  
		
	}
	public void entityPopup(EntityPlotter entity,Point location) {
		System.out.println("Entity : " + entity.getId() + " was popped up ..");
		
	}
	public void entityDeleted(EntityPlotter entity) {
		// TODO Auto-generated method stub
		
	}
	public void entityConnected(EntityPlotter from, EntityPlotter to) {
		// TODO Auto-generated method stub
		
	}
	public void entityDisconnected(EntityPlotter from, EntityPlotter to) {
		// TODO Auto-generated method stub
		
	}

}
