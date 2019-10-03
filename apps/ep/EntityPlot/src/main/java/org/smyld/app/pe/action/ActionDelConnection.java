package org.smyld.app.pe.action;

import org.smyld.app.pe.EntityConnector;
import org.smyld.app.pe.EntityPlotAction;

public class ActionDelConnection extends EntityPlotAction {
	public EntityConnector getConnector() {
		return connector;
	}

	EntityConnector connector;
	public ActionDelConnection(EntityConnector connector){
		super(TYPE_DELETE_CONNECTION);
		this.connector = connector;
	}
	

}
