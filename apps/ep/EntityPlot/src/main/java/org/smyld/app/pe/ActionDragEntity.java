package org.smyld.app.pe;

import java.awt.Point;

public class ActionDragEntity extends EntityPlotAction {
	Point         oldPosition;
	public ActionDragEntity(EntityPlotter entity,Point oldPosition){
		super(TYPE_DRAG_ENTITY,entity);
		this.oldPosition = oldPosition;
	}
	

}
