package org.smyld.app.pe.action;

import org.smyld.app.pe.EntityPlotAction;
import org.smyld.app.pe.EntityPlotter;

public class ActionDelEntity extends EntityPlotAction {
	public ActionDelEntity(EntityPlotter entity){
		super(TYPE_DELETE_ENTITY,entity);
	}
	
	
}
