package org.smyld.app.entityplot;

public class ActionAlignEntity extends EntityPlotAction {
	int alignType;
	int distance;
	public ActionAlignEntity(EntityPlotter entity,int distance,int type){
		super(TYPE_ALIGN_ENTITY,entity);
		this.distance  = distance;
		this.alignType = type;
	}
	public int getAlignType() {
		return alignType;
	}
	public void setAlignType(int alignType) {
		this.alignType = alignType;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
