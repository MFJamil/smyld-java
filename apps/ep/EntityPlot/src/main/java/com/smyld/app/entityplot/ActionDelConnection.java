package com.smyld.app.entityplot;

public class ActionDelConnection extends EntityPlotAction {
	EntityConnector connector;
	public ActionDelConnection(EntityConnector connector){
		super(TYPE_DELETE_CONNECTION);
		this.connector = connector;
	}
	

}
