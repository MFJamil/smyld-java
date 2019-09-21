package com.smyld.app.entityplot.flowchart;

import java.awt.Color;

import javax.swing.JOptionPane;

import com.smyld.app.entityplot.EntityConnector;
import com.smyld.app.entityplot.EntityPlotLayoutManager;
import com.smyld.app.entityplot.EntityPlotter;
import com.smyld.app.entityplot.GUIPlotter;
import com.smyld.app.entityplot.OrthogonalEntityConnector;
import com.smyld.app.entityplot.flowchart.EntityBasicFlowChart.ChartElement;

public class FlowChartPlotter extends GUIPlotter {

	public FlowChartPlotter(){
		super();
	}
	protected EntityPlotLayoutManager createManager(){
		return new FCEntityLayoutManager();
		
	}
	public void connectEntities(EntityPlotter from,EntityPlotter to ,Color conColor){
		EntityBasicFlowChart frm = (EntityBasicFlowChart)from;
		if (frm.element==ChartElement.Decision){
			processDecisionConnection(from, to, conColor);
		}else if (isStartElement(frm)){
			processStartElementConnection(frm,to,conColor);
		}else{
			super.connectEntities(from, to,conColor);
		}
	}
	private boolean isStartElement(EntityBasicFlowChart ent){
		return ((ent.element==ChartElement.Start1)||(ent.element==ChartElement.Start2));
	}
	private void processStartElementConnection(EntityBasicFlowChart ent,EntityPlotter to ,Color conColor){
		if ((ent.getConnectedEntities()!=null)&&(ent.getConnectedEntities().size()>0)){
			int answer = JOptionPane.showConfirmDialog((sccontainer!=null?sccontainer:null), "The start element already connected, do you want to replace it?");
			if (answer!=JOptionPane.YES_OPTION) return ;
			super.deleteConnector(ent.getConnections().get(0));
		}
		super.connectEntities(ent, to,conColor);
	}
	private void processDecisionConnection(EntityPlotter from,EntityPlotter to ,Color conColor){
		//provided that the decision element should be detected for the two cases (i.e. yes or no) differently (not only text!)
		Object[] possibilities = {"Yes", "No"};
		// Adding check for the available connections of this item, we should not allow more than one Yes connection and one No connection
		String response = (String)JOptionPane.showInputDialog((sccontainer!=null?sccontainer:null),"Do you want to connect when condition is met?",
		                    "Condition Direction",JOptionPane.PLAIN_MESSAGE, null, possibilities,"Yes");
		if (response!=null){
			EntityConnector entCon = getCommentedConnection(from, response,true);
			if (entCon!=null){
				// Creating dialog for showing
				int answer = JOptionPane.showConfirmDialog((sccontainer!=null?sccontainer:null), "The " + response + " connection already available, do you want to replace it?");
				if (answer!=JOptionPane.YES_OPTION)return;
				super.deleteConnector(entCon);
			}
			conColor = (response.equals("Yes")?new Color(251,172,66):new Color(131,42,42));
			super.connectEntities(from, to,conColor,response);
		}
	}
	// Need to create factory for it
	protected EntityConnector createConnector(EntityPlotter from,EntityPlotter to ,Color conColor){
		return new OrthogonalEntityConnector(from,to,from.getConnectorSize(),conColor);
	}
	protected EntityConnector createConnector(EntityPlotter from,EntityPlotter to ,Color conColor,String commentText){
		return new OrthogonalEntityConnector(from,to,from.getConnectorSize(),conColor,commentText);
	}
}
