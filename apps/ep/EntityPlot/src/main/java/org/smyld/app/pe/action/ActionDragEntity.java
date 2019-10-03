package org.smyld.app.pe.action;

import org.smyld.app.pe.EntityPlotAction;
import org.smyld.app.pe.EntityPlotter;

import java.awt.Point;

public class ActionDragEntity extends EntityPlotAction {
	public Point getOldPosition() {
		return oldPosition;
	}

	Point         oldPosition;
	public ActionDragEntity(EntityPlotter entity, Point oldPosition){
		super(TYPE_DRAG_ENTITY,entity);
		this.oldPosition = oldPosition;
	}
	

}
