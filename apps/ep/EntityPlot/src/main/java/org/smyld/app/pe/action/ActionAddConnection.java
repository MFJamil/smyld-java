package org.smyld.app.pe.action;

import org.smyld.app.pe.EntityConnector;
import org.smyld.app.pe.EntityPlotAction;

public class ActionAddConnection extends EntityPlotAction {
	public EntityConnector getConnector() {
		return connector;
	}

	EntityConnector connector;
	public ActionAddConnection(EntityConnector connector){
		super(TYPE_ADD_CONNECTION);
		this.connector = connector;
	}

}
