package org.smyld.app.entityplot;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Vector;

public class EntityConnectorsPlotter {
	
	EntityPlotController                    controller;
	protected HashMap<String,EntityPlotter> entities ;
	protected Vector<EntityConnector>       conectors;
	protected Vector<EntityPlotAction>      undoActions;
	EntityPlotListener                      listener;
	
	
	public void addController(EntityPlotController controller){
		this.controller = controller;
	}
	
	public void setInfo(HashMap<String,EntityPlotter> entities,Vector<EntityConnector>  conectors,Vector<EntityPlotAction>   undoActions){
		this.entities    = entities;
		this.conectors   = conectors;
		this.undoActions = undoActions;
	}
	public void addEntityPlotListener(EntityPlotListener newListener){
		this.listener = newListener;
	}
	public void drawConnections(Graphics2D g){
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(1.2f));
		for(EntityConnector curCon:conectors){
			if (!curCon.isDeactivated()){
				if ((curCon.from.isVisible())&&(curCon.to.isVisible())) 
					curCon.drawLine(g);
			}

		}
	}
	public void drawTransperantConnections(Graphics2D g){
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(1.2f));
		for(EntityConnector curCon:conectors){
			if (!curCon.isDeactivated()){
				Composite ocomp = null;
				if ((isEntTrans(curCon.from))||(isEntTrans(curCon.to))){
					ocomp = g.getComposite();
					float trans = curCon.from.getEntityTransperancy();
					if (trans>curCon.to.getEntityTransperancy()) trans = curCon.to.getEntityTransperancy(); 
					AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans);
					g.setComposite(myAlpha);
					if ((curCon.from.isVisible())&&(curCon.to.isVisible())) 
						curCon.drawLine(g);
					g.setComposite(ocomp);
				}
				
			}

		}
	}
	public void drawAllConnections(Graphics2D g){
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(1.2f));
		for(EntityConnector curCon:conectors){
			if (!curCon.isDeactivated()){
				Composite ocomp = null;
				if ((isEntTrans(curCon.from))||(isEntTrans(curCon.to))){
					ocomp = g.getComposite();
					float trans = curCon.from.getEntityTransperancy();
					if (trans>curCon.to.getEntityTransperancy()) trans = curCon.to.getEntityTransperancy(); 
					AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,trans);
					g.setComposite(myAlpha);
					if ((curCon.from.isVisible())&&(curCon.to.isVisible())) 
						curCon.drawLine(g);
					g.setComposite(ocomp);
				}else if ((!isEntTrans(curCon.from))&&(!isEntTrans(curCon.to))){
					if ((curCon.from.isVisible())&&(curCon.to.isVisible())) 
						curCon.drawLine(g);
				}
			}
		}
	}
	
	public void drawNonTransperantConnections(Graphics2D g){
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(1.2f));
		for(EntityConnector curCon:conectors){
			if (!curCon.isDeactivated()){
				Composite ocomp = null;
				if ((!isEntTrans(curCon.from))&&(!isEntTrans(curCon.to))){
					if ((curCon.from.isVisible())&&(curCon.to.isVisible())) 
						curCon.drawLine(g);
				}
			}
		}
	}
	private boolean isEntTrans(EntityPlotter ent){
		//return (ent.getEntityTransperancy()<1f);
		return ent.isTransperant();
	}

}
