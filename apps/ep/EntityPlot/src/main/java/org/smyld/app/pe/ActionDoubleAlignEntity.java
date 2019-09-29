package org.smyld.app.pe;

public class ActionDoubleAlignEntity extends ActionAlignEntity {

	int verticalDistance;
	int horizontalDistance;
	public ActionDoubleAlignEntity(EntityPlotter entity, int distance, int type) {
		super(entity, distance, type);
		
	}
	public ActionDoubleAlignEntity(EntityPlotter entity, int verticalDistance,int horizontalDistance, int type) {
		super(entity, verticalDistance, type);
		setVerticalDistance(verticalDistance);
		setHorizontalDistance(horizontalDistance);
		
	}
	/**
	 * @return the verticalDistance
	 */
	public int getVerticalDistance() {
		return verticalDistance;
	}
	/**
	 * @param verticalDistance the verticalDistance to set
	 */
	public void setVerticalDistance(int verticalDistance) {
		this.verticalDistance = verticalDistance;
	}
	/**
	 * @return the horizontalDistance
	 */
	public int getHorizontalDistance() {
		return horizontalDistance;
	}
	/**
	 * @param horizontalDistance the horizontalDistance to set
	 */
	public void setHorizontalDistance(int horizontalDistance) {
		this.horizontalDistance = horizontalDistance;
	}
	
	
}
