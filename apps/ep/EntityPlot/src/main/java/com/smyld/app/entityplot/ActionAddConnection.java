package com.smyld.app.entityplot;

public class ActionAddConnection extends EntityPlotAction {
	EntityConnector connector;
	public ActionAddConnection(EntityConnector connector){
		super(TYPE_ADD_CONNECTION);
		this.connector = connector;
	}

}
