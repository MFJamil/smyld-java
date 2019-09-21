package com.smyld.app.entityplot.flowchart;

import java.awt.Point;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import com.smyld.app.entityplot.EntityConnector;
import com.smyld.app.entityplot.EntityPlotLayoutManager;
import com.smyld.app.entityplot.EntityPlotter;
import com.smyld.app.entityplot.flowchart.EntityBasicFlowChart.ChartElement;

public class FCEntityLayoutManager extends EntityPlotLayoutManager {
	int verticalPosition;
	HashMap<Integer,Vector<EntityPlotter>> levels      = new HashMap<Integer, Vector<EntityPlotter>>();
	Vector<EntityPlotter>                  terminators = new Vector<EntityPlotter>();
	
	public FCEntityLayoutManager(){
		
	}
	public FCEntityLayoutManager(int initWidth,int initHeight){
		super(initWidth,initHeight);
	}
	
	protected Comparator<EntityPlotter> createComparator(){
		//System.out.println("Creating the comparator");
		return new Comparator<EntityPlotter>(){
			public int compare(EntityPlotter from,EntityPlotter to){
				return ((EntityBasicFlowChart)from).element.order - ((EntityBasicFlowChart)to).element.order; 
			}};
	}
	
	public void doLayout(HashMap<String,EntityPlotter> entities,Vector<EntityConnector>  conectors,boolean force){
		System.out.println("#################################   MAKING LAYOUT ####################################################################################");
		// Resetting the required collections
		positionedEnt.clear();
		levels.clear();
		terminators.clear();
		this.entities = entities;
		this.conns    = conectors;
		
		// Need to get the start point
		EntityPlotter startEntity = getStartElement();
		if (startEntity==null) return; // Can not accept to layout flow chart without start element
		addLevelEntity(0, startEntity);
		fillLevelEntity(0,startEntity); // Recursive till the end of connections
		verticalPosition = topMargin;
		if (terminators.size()>0){
			addLevelEntities(levels.size(),terminators);
		}
		distribute(startEntity);
		
		// Just collecting the rest in a garbage place
		int finalLevelNumber = levels.size();
		for(EntityPlotter check:entities.values()){
			if (!positionedEnt.contains(check.getId()))
				addLevelEntity(finalLevelNumber, check);
		}

		
		//*
		
		/*
		// Plotting the last level
		int curPos = leftMargin;
		for(EntityPlotter childEnt:levels.get(finalLevelNumber)){
			childEnt.setPosition(new Point(curPos, verticalPosition));
			positionedEnt.add(childEnt.getId());
			curPos += (childEnt.getWidth() + horzEntMargin);
		}
		*/
		
		
	}
	private int centerEntity(EntityPlotter ent){
		return initWidth/2-ent.getWidth()/2;
	}
	private void distribute(EntityPlotter curEnt){
		
		curEnt.setPosition(new Point(centerEntity(curEnt),verticalPosition));
		positionedEnt.add(curEnt.getId());
		verticalPosition += curEnt.getHeight()+vertEntMargin;
		for(int level:levels.keySet()){
			if (level>0){
				int maxHeight = 0;
				int curPos    = 0;
				for(EntityPlotter childEnt:levels.get(level)){
					if (maxHeight==0) curPos = centerEntity(childEnt);
					if (childEnt.getHeight()>maxHeight) maxHeight = childEnt.getHeight();
					childEnt.setPosition(new Point(curPos, verticalPosition));
					positionedEnt.add(childEnt.getId());
					curPos += (childEnt.getWidth() + horzEntMargin);
				}
				verticalPosition += maxHeight+vertEntMargin;
			}
		}
		
	}
	
	private void fillLevelEntity(int entLevel,EntityPlotter curEnt){
		if ((curEnt.getConnections()!=null)&&(curEnt.getConnections().size()>0)){
			//addLevelEntities(entLevel+1, curEnt.getConnectedEntities().values());
			for(EntityConnector curConn:curEnt.getConnections()){
				if (curConn.getFrom().equals(curEnt)){
					if (isTerminator((EntityBasicFlowChart)curConn.getTo())){
						terminators.add(curConn.getTo());
					}else{
						addLevelEntity(entLevel+1, curConn.getTo());
						fillLevelEntity(entLevel+1, curConn.getTo());
					}
				}
			}
		}
	}
	
	private EntityPlotter getStartElement(){
		for (EntityPlotter curEnt:entities.values()){
			EntityBasicFlowChart chartEnt = (EntityBasicFlowChart) curEnt;
			if ((chartEnt.element==ChartElement.Start1)||(chartEnt.element==ChartElement.Start2))
				return  curEnt;
		}
		return null;
		
	}
	private boolean isTerminator(EntityBasicFlowChart chartEnt){
		return (chartEnt.element==ChartElement.Terminator);
		
	}
	
	private void addLevelEntity(int level, EntityPlotter ent){
		Vector<EntityPlotter> levelEnts = levels.get(level);
		if (levelEnts==null) levelEnts = new Vector<EntityPlotter>();
		levelEnts.add(ent);
		levels.put(level,levelEnts);
		
	}
	
	private void addLevelEntities(int level, Collection<EntityPlotter> ents){
		Vector<EntityPlotter> levelEnts = levels.get(level);
		if (levelEnts==null) levelEnts = new Vector<EntityPlotter>();
		levelEnts.addAll(ents);
		levels.put(level,levelEnts);
		
	}
	public void reset(){
		super.reset();
		verticalPosition = 0;
		levels.clear();
		

	}


}
