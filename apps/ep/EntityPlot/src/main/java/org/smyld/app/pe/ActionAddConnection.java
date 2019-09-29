package org.smyld.app.pe;

public class ActionAddConnection extends EntityPlotAction {
	EntityConnector connector;
	public ActionAddConnection(EntityConnector connector){
		super(TYPE_ADD_CONNECTION);
		this.connector = connector;
	}

}
