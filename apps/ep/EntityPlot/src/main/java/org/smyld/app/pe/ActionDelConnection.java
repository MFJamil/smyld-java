package org.smyld.app.pe;

public class ActionDelConnection extends EntityPlotAction {
	EntityConnector connector;
	public ActionDelConnection(EntityConnector connector){
		super(TYPE_DELETE_CONNECTION);
		this.connector = connector;
	}
	

}
