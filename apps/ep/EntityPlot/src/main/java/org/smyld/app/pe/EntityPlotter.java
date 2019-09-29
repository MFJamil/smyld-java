package org.smyld.app.pe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Vector;



public interface EntityPlotter extends PlotConstants {
	public void                          plot(Graphics2D g);
	public void                          calibrate(Graphics2D g);
	public Point                         getConnectionPoint(int position,boolean checkDisplacement);
	public void                          paintConnectorOn(int position,Graphics2D g);
	public boolean                       hitPoint(Point p);
	public String                        getId();
	public void                          setPosition(Point p);
	public void                          setRelativePosition(Point p);
	public void                          startDragForPosition(Point p);
	public void                          setAutoExpand(boolean expand);
	public Rectangle2D                   getBounds();
	public int                           getConnectorSize();
	public boolean                       equals(EntityPlotter compare);
	public boolean                       canConnectFrom();
	public boolean                       canConnectTo();
	public HashMap<String,EntityPlotter> getConnectedEntities();
	public Vector<EntityConnector>       getConnections();
	public EntityConnector               getConnectorForEntity(EntityPlotter entity);
	public void                          newConnection(EntityConnector newCon);
	public void                          removeConnection(EntityConnector delCon);
	public void                          setConnectingState(boolean connecting);
	public String                        dumpLayoutInfo();
	public String                        dumpGUIConfig();
	public void                          adjustToLayoutInfo(String info);
	public void                          adjustToGUIConfigInfo(String info);
	public void                          setBGImageTransperancy(float newValue);
	
	public void                          setUserObject(Object userObject);
	public Object                        getUserObject();
	public void                          setContents(String text);
	public String                        getContents();
	public void                          setTitle(String title);
	public String                        getTitle();
	public Color                         getConnectorColor();
	
	public int                           getWidth();
	public void                          setWidth(int width);
	public int                           getHeight();
	public void                          setHeight(int height);
	public int                           getX();
	public void                          setX(int newX);
	public int                           getY();
	public void                          setY(int newY);

	public int                           getContentsHorizontalAlignment();
	public void                          setContentsHorizontalAlignment(int contentsAlignment);
	public int                           getContentsVerticalAlignment();
	public void                          setContentsVerticalAlignment(int contentsAlignment);

	public int                           getTitleAlignment();
	public void                          setTitleAlignment(int titleAlignment);
	public void                          setType(int entityType);
	public int                           getType();
	public void                          setDraggable(boolean draggable);
	public boolean                       isDraggable();
	public void                          highlight(boolean highlight);
	public boolean                       isHighlighted();
	public void                          setSelected(boolean selected);
	public boolean                       isSelected();
	public void                          setVisible(boolean visible);
	public boolean                       isVisible();
	public void                          setShowTitle(boolean visible);
	public boolean                       isShowTitle();
	public boolean                       isDropShadow();
	public void                          setDropShadow(boolean dropShadow);
	public boolean                       isDropReflection();
	public void                          setDropReflection(boolean dropReflection);
	
	public void                          setTitleDarkColor(Color titleDarkColor);
	public Color                         getTitleDarkColor();
	public void                          setTitleLightColor(Color titleLightColor);
	public Color                         getTitleLightColor();
	public Color                         getTitleFontColor();
	public void                          setTitleFontColor(Color titleFontColor);
	public Color                         getContentFontColor();
	public void                          setContentFontColor(Color contentFontColor);
	public Color                         getContentBackground();
	public void                          setContentBackground(Color contentBackground);
	public Font                          getTitleFont();
	public void                          setTitleFont(Font titleFont);
	public Font                          getContentsFont();
	public void                          setContentsFont(Font contentsFont);
	public float                         getEntityTransperancy();
	public void                          setEntityTransperancy(float entityTransperancy);
	public boolean                       isTransperant();	
	public void                          setTransperant(boolean setTransperant);
	
	
	
	
	
	
	
	

}
