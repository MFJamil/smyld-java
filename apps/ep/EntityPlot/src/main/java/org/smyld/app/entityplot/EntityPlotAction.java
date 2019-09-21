package org.smyld.app.entityplot;

public class EntityPlotAction {
	int           type;
	EntityPlotter entity;
	public EntityPlotAction (int type){
		this.type    = type;
	}
	public EntityPlotAction (int type,EntityPlotter entity){
		this(type);
		this.entity = entity;
		
	}
	

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public EntityPlotter getEntity() {
		return entity;
	}
	public void setEntity(EntityPlotter entity) {
		this.entity = entity;
	}


	// Actions list
	public static final int TYPE_DELETE_ENTITY     = 1;
	public static final int TYPE_DELETE_CONNECTION = 2;
	public static final int TYPE_DRAG_ENTITY       = 3;
	public static final int TYPE_ADD_ENTITY        = 4;
	public static final int TYPE_ADD_CONNECTION    = 5;
	public static final int TYPE_UPDATE_ENTITY     = 6;
	public static final int TYPE_ALIGN_ENTITY      = 7;
	public static final int TYPE_ENTITY_SIZE       = 8;
	
	

}
