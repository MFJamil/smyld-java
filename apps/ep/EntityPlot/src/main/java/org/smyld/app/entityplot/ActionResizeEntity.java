package org.smyld.app.entityplot;

public class ActionResizeEntity extends EntityPlotAction {

	int size;
	int resizeType;
	public ActionResizeEntity(EntityPlotter entity,int size,int type){
		super(TYPE_ENTITY_SIZE,entity);
		this.size      = size;
		this.resizeType = type;
		
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getResizeType() {
		return resizeType;
	}
	public void setResizeType(int resizeType) {
		this.resizeType = resizeType;
	}

	public static final int SIZING_WIDTH  = 1;
	public static final int SIZING_HEIGHT = 2;
}
