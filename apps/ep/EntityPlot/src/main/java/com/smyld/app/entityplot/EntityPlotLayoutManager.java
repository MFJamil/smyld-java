package com.smyld.app.entityplot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;



public class EntityPlotLayoutManager {
	protected HashMap<String,EntityPlotter> entities  ;
	protected HashSet<String>               positionedEnt = new HashSet<String>();
	protected Vector<EntityConnector>       conns ; 
	protected ArrayList<EntityPlotter>      conEntities   = new ArrayList<EntityPlotter>();
	protected int                           initWidth     = 800;
	protected int                           initHeight    = 600;
	protected int                           topMargin     = 80;
	protected int                           leftMargin    = 40;
	protected int                           horzEntMargin = 20;
	protected int                           vertEntMargin = 50;
	
	public EntityPlotLayoutManager(){
		
	}
	public EntityPlotLayoutManager(int initWidth,int initHeight){
		this.initWidth  = initWidth;
		this.initHeight = initHeight;
	}
	public void doLayout(HashMap<String,EntityPlotter> entities,Vector<EntityConnector>  conectors){
		doLayout(entities, conectors,false);
	}
	public void doLayout(HashMap<String,EntityPlotter> entities,Vector<EntityConnector>  conectors,boolean force){
		this.entities = entities;
		this.conns    = conectors;
		conEntities.addAll(entities.values());
		Collections.sort(conEntities,createComparator());
		int level = topMargin;
		/*
		System.out.println(" ");
		System.out.println("==============");
		System.out.println(" ");
		*/
		//System.out.println("Top margin : " + topMargin);
		// We sort the entities according to their number of connections first
		for(EntityPlotter curEnt:conEntities){
			//System.out.println("Entity (" + curEnt.getId() + ") has (" + curEnt.getConnectedEntities().size() + ") connections");
			if ((force)||((!force)&&(!positionedEnt.contains(curEnt.getId())))){
				level = setPositionFor(initWidth/2-curEnt.getWidth()/2,level,curEnt) + vertEntMargin;// + curEnt.getHeight();
				//System.out.println("Level : " + level);
			}
			
		}
		
	}
	protected Comparator<EntityPlotter> createComparator(){
		return new Comparator<EntityPlotter>(){
			public int compare(EntityPlotter from,EntityPlotter to){
				int result  = to.getConnectedEntities().size()-from.getConnectedEntities().size();
				if (result==0)
					result = to.canConnectFrom()? -1:1;
				return result;
			}};
	}
	
	private int setPositionFor(int horizontalOffset,int verticalOffset,EntityPlotter curEnt){
		curEnt.setPosition(new Point(horizontalOffset,verticalOffset));
		positionedEnt.add(curEnt.getId());
		int curPos = leftMargin;
		verticalOffset += curEnt.getHeight()+vertEntMargin;
		int maxHeight = 0;
		for(EntityPlotter childEnt: curEnt.getConnectedEntities().values()){
			if (childEnt.getHeight()>maxHeight) maxHeight = childEnt.getHeight(); 
			childEnt.setPosition(new Point(curPos, verticalOffset));
			positionedEnt.add(childEnt.getId());
			curPos += (childEnt.getWidth() + horzEntMargin);
		}
		return verticalOffset + maxHeight;
		
	}
	private int getTotalWidth(Collection<EntityPlotter> ents){
		int totalWidth = 0;
		for(EntityPlotter curEnt :ents) 
			totalWidth += curEnt.getWidth();
		return totalWidth;
	}
	public int getInitWidth() {
		return initWidth;
	}
	public void setInitWidth(int initWidth) {
		this.initWidth = initWidth;
	}
	public int getInitHeight() {
		return initHeight;
	}
	public void setInitHeight(int initHeight) {
		this.initHeight = initHeight;
	}
	
	public void reset(){
		initWidth     = 800;
		initHeight    = 600;
		topMargin     = 20;
		leftMargin    = 40;
		horzEntMargin = 20;
		vertEntMargin = 50;
	}
	

}
