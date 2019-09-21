package org.smyld.app.entityplot;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.RepaintManager;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import org.smyld.app.entityplot.util.Three60TLogo;


public class GUIPlotter extends JPanel implements Scrollable{

	private static final Logger LOG = Logger.getLogger("org.smyld.app.entityplot.GUIPlotter");

	protected JScrollPane                   sccontainer; 
	// Temporarely set to public for testing purpose, should be removed later
	protected HashMap<String,EntityPlotter> entities      = new HashMap<String,EntityPlotter>();
	protected HashMap<Integer,String>       entGUIConfs   = new HashMap<Integer,String>();
	protected Vector<EntityConnector>       conectors     = new Vector<EntityConnector>();
	protected Vector<EntityPlotter>         selected      = new Vector<EntityPlotter>();
	protected Vector<EntityPlotAction>      undoActions   = new Vector<EntityPlotAction>();
	EntityPlotter                           draggedEntity = null,latestHighlighted,conFrom;
	
	EntityConnector                         draggedCon    = null;
	Toolbar                                 tools         = new Toolbar();
	Dimension                               curSize       = new Dimension(10,10);
	Point                                   selStartPnt   = null;
	Point                                   selEndPnt     = null;
	Rectangle                               curVisRect    = new Rectangle();
	Rectangle                               prevPanelRect = new Rectangle();
	KeyStroke                               undoKey       = KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_MASK);
	KeyStroke                               selAllKey     = KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_MASK);
	EntityPlotListener                      listener;
	EntityPlotController                    controller;
	String                                  presetLayout;
	RenderingHints                          qualityHints;
	protected EntityPlotLayoutManager       manager      ;
	boolean                                 inDrag,inConDrag,searchCon,inDeleteMode,exportingImage;
	boolean                                 allowMultiSelection = true,allowEntityDelete = true,doAutoLayout=true,firstTimeCreation=true,showToolbar=true;
	boolean                                 doZooming,selecting;
	boolean                                 showConnectedEntities = false,connDraw=true,animationActive = true;
	float                                   showConTransp  = .15f; 
	double                                  zoomValue      = 1.0;
	int                                     processMode    = MODE_NO_PROCESSING;
	
	EntityConnectorsPlotter                 connPlotter    = new EntityConnectorsPlotter();
	RepaintManager                          mgr            = RepaintManager.currentManager(null);
	
	Three60TLogo                            compLogo;
    // Selection variables, this might be changed in the future to a more advanced selection criteria and might be included in objects
	Color             selectionBG  = new Color(120,120,120,40);
	Color             selTextColor = new Color(0,73,0,90);
    Color             selectionBR  = new Color(0,73,0,255);
    float[]           dash         = {4f,1f,4f,1f,4f};
    Stroke            selStroke    = new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,14f,dash,0);
    Rectangle2D.Float selRect      = new Rectangle2D.Float();
    Font              selFont      = new Font("Arial",Font.BOLD|Font.ITALIC,12);
    // Logo related variables
	BufferedImage logo   ;
	BufferedImage reflect;
	UndoListener  undoListener;
    
	public GUIPlotter(JScrollPane container,String presetLayout){
		super();
		setPresetLayout(presetLayout);
		this.sccontainer    = container;
		init();
		
	}

	public GUIPlotter(JScrollPane container){
		super();
		this.sccontainer = container;
		init();
		
	}

	public GUIPlotter(){
		super();
		init();
		
	}
	
	
	public void addEntityPlotListener(EntityPlotListener newListener){
		this.listener = newListener;
		connPlotter.addEntityPlotListener(newListener);
	}
	public void addUndoListener(UndoListener newUndoListener){
		this.undoListener = newUndoListener;
	}
	public void setScrollContainer(JScrollPane container){
		this.sccontainer = container;
		if (sccontainer!=null){
			sccontainer.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
			sccontainer.setAutoscrolls(true);
			
		}
	}
	public void emptyUndoActions(){
		undoActions.removeAllElements();
	}
	public void zoom(boolean zoomOut){
		doZooming = true;
		if (zoomOut)
			zoomValue -= 0.1;
		else
			zoomValue += 0.1;
		
		repaint();
		
	}
	
	public void init(){
		compLogo = new Three60TLogo();
		//setAutoscrolls(true);
		// Code needed for initializing the panel
		super.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				processMouseReleasedEvent(e);
				requestFocus();
			}
			public void mousePressed(MouseEvent e){
				processMousePressedEvent(e);
			}
		});
		super.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e){
				processMouseDraggedEvent(e);
			}
			public void mouseMoved(MouseEvent e){
				processMouseMovedEvent(e);
				requestFocus();
			}
		});
		//*
		
		mgr.setDoubleBufferingEnabled(true);
		setRequestFocusEnabled(true);
		mgr.removeInvalidComponent(this);
		setFocusable(true);
		requestFocusInWindow();
		qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY); 
		qualityHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		qualityHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		//*/
		//setBorder(BorderFactory.createLineBorder(Color.black));
		//*
		super.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				processKeyPressedEvent(e);
			}
		});
		setFocusable(true);
		setBackground(Color.WHITE);
		//*/
		manager = createManager();
	}
	
	
	public void validateTree(){
		// we do not do anything here
		Rectangle rect = mgr.getDirtyRegion(this);
		mgr.removeInvalidComponent(this);
		//LOG.info("Is completely dirty result was : " + mgr.isCompletelyDirty(this));
		if (rect!=null){
			//LOG.info(" =========== Dirtry Region is : " + rect.toString()); 
		}
		//LOG.info("****************** Validate Tree is called  ******************");
	}
	public boolean isValidateRoot(){
		//LOG.info("****************** is Validate Tree ? called  ******************");
		return true;
	}
	public boolean isOptimizedDrawingEnabled(){
		//LOG.info("****************** is Optimized Drawing ? called  ******************");
		return true;}
	
	public void setMode(int newMode){
		processMode = newMode;
		switch(newMode){
			case MODE_ADD_CONNECTOR:
				searchCon    = true;
				inDeleteMode = false;
				break;
			case MODE_DEL_CONNECTOR:
				conFrom = null;
				searchCon    = false;
				inDeleteMode = true;
				break;
			default:
				searchCon    = false;
				inDeleteMode = false;
		}
		resetAllEntitiesStates();
		repaint();
	}
	
	public void undo(){
		if (undoActions.size()>0){
			EntityPlotAction curAction = undoActions.lastElement();
			// We informed the undo listener and gave him the ability not to carry on with implementing the undo in this plotter
			if ((undoListener!=null)&&(!undoListener.undo(curAction))) return; 
			EntityPlotter    ent       = curAction.getEntity();
			switch(curAction.type){
				case EntityPlotAction.TYPE_DELETE_ENTITY:
					ent.setVisible(true);
					break;
				case EntityPlotAction.TYPE_DELETE_CONNECTION:
					EntityConnector connector = ((ActionDelConnection)curAction).connector;
					connector.setDeactivated(false);
					if (listener!=null) listener.entityConnected(connector.from, connector.to);
					break;
				case EntityPlotAction.TYPE_DRAG_ENTITY:
					ent.setPosition(((ActionDragEntity)curAction).oldPosition);
					break;
				case EntityPlotAction.TYPE_ADD_CONNECTION:
					connector = ((ActionAddConnection)curAction).connector;
					connector.setDeactivated(true);
					if (listener!=null) listener.entityDisconnected(connector.from, connector.to);

					break;
				case EntityPlotAction.TYPE_ADD_ENTITY:
					ent.setVisible(false);
					break;
				case EntityPlotAction.TYPE_ALIGN_ENTITY:
					ActionAlignEntity entAct = (ActionAlignEntity)curAction;
					switch(entAct.getAlignType()){
						case EntityPlotter.ALIGN_POSITION_TOP:case EntityPlotter.ALIGN_POSITION_BUTTOM:case EntityPlotter.ALIGN_POSITION_SPACE_VERTICAL:
							ent.setY(entAct.getDistance());
							break;
						case EntityPlotter.ALIGN_POSITION_RIGHT:case EntityPlotter.ALIGN_POSITION_LEFT:case EntityPlotter.ALIGN_POSITION_SPACE_HORIZONTAL:
							ent.setX(entAct.getDistance());
							break;
						case EntityPlotter.ALIGN_POSITION_STAIR_RIGHT:case EntityPlotter.ALIGN_POSITION_STAIR_LEFT:
							ActionDoubleAlignEntity dblAct = (ActionDoubleAlignEntity)entAct;
							
							ent.setX(dblAct.getHorizontalDistance());
							ent.setY(dblAct.getVerticalDistance());
							break;

					}
					break;
				case EntityPlotAction.TYPE_ENTITY_SIZE:
					ActionResizeEntity resAct = (ActionResizeEntity)curAction;
					switch(resAct.getResizeType()){
						case ActionResizeEntity.SIZING_HEIGHT:
							ent.setHeight(resAct.getSize());
							break;
						case ActionResizeEntity.SIZING_WIDTH:
							ent.setWidth(resAct.getSize());
							break;
					}
					break;
					
			}
			repaint();
			undoActions.remove(curAction);
		}
	}
	public void delete(){
		if ((allowEntityDelete)&&(selected.size()>0)){
			for(EntityPlotter curEntity:selected)
				delete(curEntity);
			selected.removeAllElements();
		}
	}
	
	public String exportLayout(){
		StringBuilder sb = new StringBuilder();
		for(EntityPlotter ent:entities.values()){
			sb.append("~~");
			sb.append(ent.dumpLayoutInfo());
		}
		return sb.toString();
	}
	//*
	public static void main(String[] args){
		
		String test = "~~FxForward|0|0|120|35~~mlniin-7f011-fm1k43f3-cp|0|0|200|110~~c9u0ti-7f011-fm1k43f1-cf|0|0|200|110~~FxOption|0|0|120|35~~oe3hw5-7f011-fm1k43f0-c7|0|0|200|110~~ltvor-7f011-fm1k43f0-c3|0|0|200|110~~6u759s-7f011-fm1k43f0-c4|0|0|200|100~~FxSwap|0|0|120|35~~q64j4g-7f011-fm1k43f3-ct|0|0|200|110~~LoanDeposit|0|0|120|35~~FRA|0|0|120|35";
		String[] entConfs =  test.split("~~");
		
		for(String curEntConf: entConfs){
			if (!curEntConf.isEmpty()){
				LOG.info("Entity Configuration : " + curEntConf);
				String id = curEntConf.substring(0,curEntConf.indexOf("|"));
				LOG.info("ID is  : " + id);
			}
		}
					
	}
	//*/
	public void importLayout(String conf){
		String[] entConfs =  conf.split("~~");
//		LOG.info("Importing layout information : " + conf);
		for(String curEntConf: entConfs){
			if (!curEntConf.isEmpty()){
				//LOG.info("Entity Configuration : " + curEntConf);
				String id = curEntConf.substring(0,curEntConf.indexOf("|"));
				if ((id!=null)&&(entities.containsKey(id))){
					EntityPlotter curEnt = entities.get(id);
					curEnt.adjustToLayoutInfo(curEntConf);
				
				}
			}
		}
	}




	private void processKeyPressedEvent(KeyEvent e){
		KeyStroke curKeyStroke = KeyStroke.getKeyStrokeForEvent(e);
		if (curKeyStroke.equals(undoKey)){
			undo();
		}else if (curKeyStroke.equals(selAllKey)){
			selected.addAll(entities.values());
			for(EntityPlotter ent:entities.values()) 
				ent.setSelected(true);
			repaint();
			//LOG.info("Undo was typed !");
		}else if (e.getKeyCode()==KeyEvent.VK_DELETE){
			//LOG.info("Delete typed !");
			delete();
		}
		
	}
	private void processMouseReleasedEvent(MouseEvent e){
//		LOG.info("(Release)Selected items count is  : " + selected.size());
		if (e.getButton()==e.BUTTON1){
			if (inDrag){
				inDrag= false;
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				repaint();
			}
			
		} 
		if (selecting){
			selecting   = false;
			selStartPnt = null;
			selEndPnt   = null;
			// Code for setting the included entities to be selected
			//applySelection(e);
			repaint();
		}
	}
	private void processMousePressedEvent(MouseEvent e){
//		LOG.info("(Press)Selected items count is  : " + selected.size());
		if (inDeleteMode)
			handleConnectionDelete(e);
		if(e.getButton()==MouseEvent.BUTTON1){
			if ((e.getModifiersEx()&MouseEvent.CTRL_DOWN_MASK)==MouseEvent.CTRL_DOWN_MASK){
				handleSingleEntitySelection(e);
			}else if (e.getClickCount()==1){
				checkMultiSelectionCancel(e);
				handleConnectionAdd(e);
				handleToolbar(e);
				
				checkTransperancy(e);
			}else if (e.getClickCount()==2){
				handleEntityDblClickEvent(e);
			}
		}else if(e.getButton()==MouseEvent.BUTTON2){
			//This is the mouse wheel button
		}else if(e.getButton()==MouseEvent.BUTTON3){
			// The user clicked the right mouse, pop-up event
			handleEntityPopupEvent(e);
		}
	}
	private void checkTransperancy(MouseEvent e){
		if (animationActive)
			setFadeTransperancy(e);
		else
			setNoFadeTransperancy(e);
	}		
	private void setNoFadeTransperancy(MouseEvent e){
		if (showConnectedEntities){
			EntityPlotter curEntity = hitsEntity(e.getPoint());
			if (curEntity!=null){
				for (EntityPlotter ent:entities.values()){
					if ((!ent.equals(curEntity))&&(!curEntity.getConnectedEntities().containsKey(ent.getId()))){
						ent.setTransperant(true);
					}else{
						ent.setTransperant(false);
					}
				}
				repaint();
			}
		}
		

	}
	private  void setFadeTransperancy(final MouseEvent e){
		new Thread(new Runnable(){
			public void run(){
				if (showConnectedEntities){
					EntityPlotter curEntity = hitsEntity(e.getPoint());
					if (curEntity!=null){
						float transDiff   = 1f - showConTransp;
						int   transSimNo  = 5; 
						float transStep   = transDiff/transSimNo;
						for (int i = 0; i < transSimNo; i++) {
							float transChange = (float)(transStep*(i+1));
							float fadeOut     = 1f - transChange;
							float fadeIn      = showConTransp + transChange;
							for (EntityPlotter ent:entities.values()){
								
								if ((!ent.equals(curEntity))&&(!curEntity.getConnectedEntities().containsKey(ent.getId()))){
									ent.setTransperant(true);
									if (ent.getEntityTransperancy()>fadeOut) ent.setEntityTransperancy(fadeOut);
								}else{
									if (ent.isTransperant())
										ent.setEntityTransperancy(fadeIn);
								}
							}
							repaint();
							try {
								Thread.sleep(50);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						for (EntityPlotter ent:entities.values())
							ent.setTransperant((!ent.equals(curEntity))&&(!curEntity.getConnectedEntities().containsKey(ent.getId())));
						repaint();
					}
				}
				
			}
			
		}).start();
		

	}

	private void checkMultiSelectionCancel(MouseEvent e){
		if ((selected.size()>0)&&(hitsEntity(e.getPoint()))==null){
			for(EntityPlotter curEnt:selected)
				curEnt.setSelected(false);
			selected.removeAllElements();
			repaint();
		}
	}
	private void handleEntityDblClickEvent(MouseEvent e){
		if (listener!=null){
			EntityPlotter curEntity = hitsEntity(e.getPoint());
			if (curEntity!=null) listener.entityDoubleClicked(curEntity);
		}
		
	}
	private void handleEntityPopupEvent(MouseEvent e){
		if (listener!=null){
			EntityPlotter curEntity = hitsEntity(e.getPoint());
			Point realLocation = getBounds().getLocation();
			realLocation.x += e.getPoint().x;
			realLocation.y += e.getPoint().y;
			if (curEntity!=null) listener.entityPopup(curEntity,realLocation);
		}
		
	}
	private void handleSingleEntitySelection(MouseEvent e){
		if (allowMultiSelection){
			EntityPlotter curEntity = hitsEntity(e.getPoint());
			if (curEntity!=null){
				if (curEntity.isSelected()){
					curEntity.setSelected(false);
					selected.remove(curEntity);
				}else{
					curEntity.setSelected(true);
					if (!selected.contains(curEntity))
							selected.add(curEntity);
				}
				repaint();
			}
		}
	}
	private void handleToolbar(MouseEvent e){
		if ((showToolbar)&&(tools.hitsTool(e.getPoint()))){
			if (tools.getSelectedTool()==TOOL_ADD_CONNECTOR){
				searchCon = tools.isSelecteToolActive();
				inDeleteMode = false;
				setMode(MODE_ADD_CONNECTOR);
			}else if (tools.getSelectedTool()==TOOL_DEL_CONNECTOR){
				inDeleteMode = tools.isSelecteToolActive();
				if (latestHighlighted!=null) latestHighlighted.highlight(false);
				searchCon = false;
				setMode(MODE_DEL_CONNECTOR);
			}
			
		}
		
	}
	private void handleConnectionAdd(MouseEvent e){
		if (searchCon){
			EntityPlotter curEntity = hitsEntity(e.getPoint());
			if (curEntity!=null){
				if ((conFrom!=null)&&(!conFrom.equals(curEntity))&&(curEntity.canConnectTo())){
					LOG.info("Do Connect "+ conFrom.getId() + " to " + curEntity.getId());
					connectEntities(conFrom, curEntity);
					resetSearchingConnection();
				}else if ((latestHighlighted!=null)&&(latestHighlighted.equals(curEntity))){
					conFrom = curEntity;
					curEntity.setConnectingState(true);
					inConDrag = true;
					repaint();
				}
			}else{
				LOG.info("Coniditon met");
				resetSearchingConnection();
			}
		}
		
	}
	private void handleConnectionDelete(MouseEvent e){
		for(EntityConnector con:conectors){
			if (con.hitPoints(e.getX(), e.getY())){
				deleteConnector(con);
				/*
				con.setDeactivated(true);
				if (listener!=null) listener.entityDisconnected(con.from, con.to);
				con.from.removeConnection(con);
				con.to.removeConnection(con);
				
				undoActions.add(new ActionDelConnection(con));
				repaint();
				*/
			}
		}
		
	}
	private void resetSearchingConnection(){
		if (conFrom!=null){
			conFrom.setConnectingState(false);
			conFrom           = null;
		}
		latestHighlighted = null;
		inConDrag         = false;
		resetAllEntitiesStates();
		repaint();
	}
	public Dimension getVisibleDiagramSize(){
		Dimension result = new Dimension(getWidth(),getHeight());
		int maxWidth  = 0;
		int maxHeight = 0;
		int minWidth  = -1;
		int minHeight = -1;

		for(EntityPlotter curEnt: entities.values()){
			if (curEnt.isVisible()){
				if (minWidth==-1) minWidth   = curEnt.getX();
				if (minHeight==-1) minHeight = curEnt.getY();
				if (curEnt.getX()+curEnt.getWidth()>maxWidth) maxWidth = curEnt.getX()+curEnt.getWidth();
				if (curEnt.getX()<minWidth) minWidth = curEnt.getX();
				if (curEnt.getY()+curEnt.getHeight()>maxHeight) maxHeight = curEnt.getY()+curEnt.getHeight();
				if (curEnt.getY()<minHeight) minHeight = curEnt.getY();
			}
		}
		result.setSize(maxWidth+10, maxHeight+10);
		return result;
	}
	public Rectangle2D getVisibleDiagramBounds(){
		Rectangle result = new Rectangle();
		int maxWidth  = 0;
		int maxHeight = 0;
		int minWidth  = -1;
		int minHeight = -1;

		for(EntityPlotter curEnt: entities.values()){
			if (curEnt.isVisible()){
				if (minWidth==-1) minWidth   = curEnt.getX();
				if (minHeight==-1) minHeight = curEnt.getY();
				if (curEnt.getX()+curEnt.getWidth()>maxWidth) maxWidth = curEnt.getX()+curEnt.getWidth();
				if (curEnt.getX()<minWidth) minWidth = curEnt.getX();
				if (curEnt.getY()+curEnt.getHeight()>maxHeight) maxHeight = curEnt.getY()+curEnt.getHeight();
				if (curEnt.getY()<minHeight) minHeight = curEnt.getY();
				//LOG.info("Ent ("+curEnt.getTitle()+") location is (" + curEnt.getX() + "," + curEnt.getY() + ")");
			}
		}
		result.setSize(maxWidth-minWidth, maxHeight-minHeight);
		result.setLocation(minWidth, minHeight);
		return result;
	}
	
	protected void highlightAllEntities(boolean highlight){
		for(EntityPlotter ent:entities.values()) ent.highlight(highlight);
	}
	protected void setAllEntitiesConnectingState(boolean select){
		for(EntityPlotter ent:entities.values()) 
			ent.setConnectingState(select);
	}
	protected void resetAllEntitiesStates(){
		for(EntityPlotter ent:entities.values()) {
			ent.highlight(false);
			ent.setConnectingState(false);
			
		}
	}
	
	
	private void processMouseDraggedEvent(MouseEvent e){
		boolean doHits = false;
		// Checking entities
		EntityPlotter curEntity = hitsEntity(e.getPoint());
		//LOG.info("(Drag)Selected items count is  : " + selected.size());
		//LOG.info("(Drag)CurEntity is  : " + (curEntity==null?"Null":"NOT Null ...."));
		if ((!selecting)&&(curEntity!=null)){
			// We disable the connection process in case drag is in progress
			if(inConDrag){
				if (conFrom!=null) conFrom.setConnectingState(false);
				inConDrag = false;
				conFrom   = null;
			}
			doHits = true;
			if (curEntity.isDraggable()){
				if (!inDrag){
					setCursor(new Cursor(Cursor.HAND_CURSOR));
					doAutoLayout = false; // No logic to keep the automatic layout process as long as the user changed the entity positions
					inDrag   = true;
					Point mp = convertToZooming(e.getPoint());
					//applySelection(e);
					if (selected.size()>1){
						for(EntityPlotter ent:selected){
							//draggedEntity= curEntity;
							undoActions.add(new ActionDragEntity(ent,new Point(ent.getX(),ent.getY())));
							ent.startDragForPosition(mp);
						}
					}else{
						draggedEntity = curEntity;
						undoActions.add(new ActionDragEntity(curEntity,new Point(curEntity.getX(),curEntity.getY())));
						curEntity.startDragForPosition(mp);
					}
				}
				repaint();
			}
		}else if(!inDrag){
			handleSelection(e);
		}
			
		
		if (inDrag){
			Point mp = convertToZooming(e.getPoint());
			if (selected.size()>1){
				for(EntityPlotter ent:selected){
					ent.setRelativePosition(mp);
					checkScrolling(mp, ent);
				}
			}else{
				draggedEntity.setRelativePosition(mp);
				checkScrolling(mp, draggedEntity);
			}
			repaint();
		}
	}
	private void applySelection(MouseEvent e){
		for(EntityPlotter ent:entities.values()){
			if (selRect.intersects(ent.getBounds())){
				ent.setSelected(true);
				if (!selected.contains(ent))
					selected.add(ent);
			}else{
				if (((e.getModifiersEx()&MouseEvent.CTRL_DOWN_MASK)!=MouseEvent.CTRL_DOWN_MASK)&&(ent.isSelected())){
					ent.setSelected(false);
					selected.remove(ent);
				}
			}
		}
		
	}
	
	private void handleSelection(MouseEvent e){
		// Code for checking if the user is about to make a big selection, in this case we need to enable that via a quardratic selection 
		if (!selecting){
			selecting = true;
			selStartPnt = convertToZooming(e.getPoint());
		// The user is still moving his hand, we need to continue changing the selection square size
		}else{
			selEndPnt = convertToZooming(e.getPoint());
			applySelection(e);
			
		}
		repaint();
		
	}
	private Point convertToZooming(Point src){
		src.x = (int)(src.x/zoomValue); 
		src.y = (int)(src.y/zoomValue);
		return src;
	}
	
	private void processMouseMovedEvent(MouseEvent e){
		if (searchCon){
			boolean foundOne = false;
			EntityPlotter foundEntity = null;
			EntityPlotter curEntity = hitsEntity(e.getPoint());
			if(curEntity!=null){
				if(((conFrom == null)&&(curEntity.canConnectFrom()))||((conFrom != null)&&(curEntity.canConnectTo()))){
					foundOne= true;
					if (!curEntity.isHighlighted()){
						curEntity.highlight(true);
						foundEntity = curEntity;
						if ((latestHighlighted!=null)&&(!latestHighlighted.equals(curEntity)))
							latestHighlighted.highlight(false);
						repaint();
					}
				}
			}
			if (foundEntity!=null)
				latestHighlighted = foundEntity;
		}
	}
	
	public boolean hasMultiSelects(){
		return selected.size()>1;
	}
	public void setEntitiesWidth(){
		if (selected.size()>1){
			EntityPlotter lastEntity = selected.lastElement();
			for(EntityPlotter ent:selected){
				ent.setWidth(lastEntity.getWidth());
				undoActions.add(new ActionResizeEntity(ent,ent.getWidth(),ActionResizeEntity.SIZING_WIDTH));
			}
			repaint();
		}
	}
	public void setEntitiesHeight(){
		if (selected.size()>1){
			EntityPlotter lastEntity = selected.lastElement();
			for(EntityPlotter ent:selected){
				ent.setHeight(lastEntity.getHeight());
				undoActions.add(new ActionResizeEntity(ent,ent.getHeight(),ActionResizeEntity.SIZING_HEIGHT));
			}
			repaint();
		}
	}

	public void alignEntities(int direction){
		if (selected.size()>1){
			switch(direction){
				case EntityPlotter.ALIGN_POSITION_TOP:
					alignToTop();
					break;
				case EntityPlotter.ALIGN_POSITION_RIGHT:
					alignToRight();
					break;
				case EntityPlotter.ALIGN_POSITION_CENTER_HORIZONTAL:
					alignToCenterHorizontal();
					break;
				case EntityPlotter.ALIGN_POSITION_CENTER_VERTICAL:
					alignToCenterVertical();
					break;
					
				case EntityPlotter.ALIGN_POSITION_LEFT:
					alignToLeft();
					break;
				case EntityPlotter.ALIGN_POSITION_BUTTOM:
					alignToButtom();
					break;
				case EntityPlotter.ALIGN_POSITION_SPACE_HORIZONTAL:
					if (selected.size()>=2) horizontalSpacing();
					break;
				case EntityPlotter.ALIGN_POSITION_SPACE_VERTICAL:
					if (selected.size()>=2) verticalSpacing();
					break;
				case EntityPlotter.ALIGN_POSITION_STAIR_RIGHT:
					if (selected.size()>=2) alignAsStair(true);
					break;
				case EntityPlotter.ALIGN_POSITION_STAIR_LEFT:
					if (selected.size()>=2) alignAsStair(false);
					break;
					
			}
			repaint();
		}
		
	}
	private void alignAsStair(boolean right){
		Collections.sort(selected,new Comparator<EntityPlotter>(){
			public int compare(EntityPlotter ent1,EntityPlotter ent2){
				return ent1.getY()-ent2.getY();
			}
		});
		EntityPlotter ent1 = selected.get(0);
		EntityPlotter ent2 = selected.get(1);
		int space = -1;
		if (ent1.getY()>ent2.getY()){
			space = ent1.getY()-(ent2.getY()+ent2.getHeight());
		}else{
			space = ent2.getY()-(ent1.getY()+ent1.getHeight());
		}
		if (space<=0){
			space = 5; // Some default value
			ent1  = selected.lastElement();
			
		}
		
		if (space>0){
			LOG.info("Space is " + space);
			int curYPos = ent1.getY();
			int curXPos = ent1.getX();
			for(int i=0;i<selected.size();i++) {
				EntityPlotter ent = selected.get(i);
				undoActions.add(new ActionDoubleAlignEntity(ent,ent.getY(),ent.getX(),(right?EntityPlotter.ALIGN_POSITION_STAIR_RIGHT:EntityPlotter.ALIGN_POSITION_STAIR_LEFT)));
				ent.setY(curYPos);
				ent.setX(curXPos);
//				LOG.info( ent.getTitle()+ " - Position is " + curPos);
				curYPos += ent.getHeight()+space;
				curXPos += (right?+1:-1)*ent.getWidth()/3;
			}
		}
	}
	
	private void alignToCenterVertical(){
		EntityPlotter lastEntity = selected.lastElement();
		int midPoint = lastEntity.getY() + lastEntity.getHeight()/2;
		for(EntityPlotter ent:selected){
			if(ent!=lastEntity){
				int curMidPoint = ent.getY() + ent.getHeight()/2;
				int newY =  ent.getY() + midPoint-curMidPoint;
				undoActions.add(new ActionAlignEntity(ent,ent.getY(),EntityPlotter.ALIGN_POSITION_TOP));
				ent.setY(newY);
			}
		}
		
	}
	private void horizontalSpacing(){
		Collections.sort(selected,new Comparator<EntityPlotter>(){
			public int compare(EntityPlotter ent1,EntityPlotter ent2){
				return ent1.getX()-ent2.getX();
			}
		});
		EntityPlotter ent1 = selected.get(0);
		EntityPlotter ent2 = selected.get(1);
		int space = -1;
		if (ent1.getX()>ent2.getX()){
			space = ent1.getX()-(ent2.getX()+ent2.getWidth());
		}else{
			space = ent2.getX()-(ent1.getX()+ent1.getWidth());
		}
		
		
		if (space>0){
			//LOG.info("Space is " + space);
			int curPos = ent2.getX() +ent2.getWidth()+space;
			for(int i=2;i<selected.size();i++) {
				EntityPlotter ent = selected.get(i);
				undoActions.add(new ActionAlignEntity(ent,ent.getX(),EntityPlotter.ALIGN_POSITION_SPACE_HORIZONTAL));
				ent.setX(curPos);
				//LOG.info( ent.getTitle()+ " - Position is " + curPos);
				curPos += ent.getWidth()+space;
				
			}
		}
		
	}
	
	private void verticalSpacing(){
		Collections.sort(selected,new Comparator<EntityPlotter>(){
			public int compare(EntityPlotter ent1,EntityPlotter ent2){
				return ent1.getY()-ent2.getY();
			}
		});
		EntityPlotter ent1 = selected.get(0);
		EntityPlotter ent2 = selected.get(1);
		int space = -1;
		if (ent1.getY()>ent2.getY()){
			space = ent1.getY()-(ent2.getY()+ent2.getHeight());
		}else{
			space = ent2.getY()-(ent1.getY()+ent1.getHeight());
		}
		
		
		if (space>0){
//			LOG.info("Space is " + space);
			int curPos = ent2.getY() +ent2.getHeight()+space;
			for(int i=2;i<selected.size();i++) {
				EntityPlotter ent = selected.get(i);
				undoActions.add(new ActionAlignEntity(ent,ent.getY(),EntityPlotter.ALIGN_POSITION_SPACE_HORIZONTAL));
				ent.setY(curPos);
//				LOG.info( ent.getTitle()+ " - Position is " + curPos);
				curPos += ent.getHeight()+space;
				
			}
		}
		
	}

	
	private void alignToCenterHorizontal(){
		EntityPlotter lastEntity = selected.lastElement();
		int midPoint = lastEntity.getX() + lastEntity.getWidth()/2;
		for(EntityPlotter ent:selected){
			if(ent!=lastEntity){
				int curMidPoint = ent.getX() + ent.getWidth()/2;
				int newX =  ent.getX() + midPoint-curMidPoint;
				undoActions.add(new ActionAlignEntity(ent,ent.getX(),EntityPlotter.ALIGN_POSITION_TOP));
				ent.setX(newX);
			}
		}
		
	}

	private void alignToTop(){
		EntityPlotter lastEntity = selected.lastElement();
		for(EntityPlotter ent:selected){
			if(ent!=lastEntity){
				undoActions.add(new ActionAlignEntity(ent,ent.getY(),EntityPlotter.ALIGN_POSITION_TOP));
				ent.setY(lastEntity.getY());
			}
		}
		
	}
	private void alignToLeft(){
		EntityPlotter lastEntity = selected.lastElement();
		for(EntityPlotter ent:selected){
			if(ent!=lastEntity){
				undoActions.add(new ActionAlignEntity(ent,ent.getX(),EntityPlotter.ALIGN_POSITION_LEFT));
				ent.setX(lastEntity.getX());
			}
		}
		
	}
	private void alignToButtom(){
		EntityPlotter lastEntity = selected.lastElement();
		int buttom =  lastEntity.getY() + lastEntity.getHeight();
		for(EntityPlotter ent:selected){
			if(ent!=lastEntity){
				int newY = buttom-ent.getHeight();
				undoActions.add(new ActionAlignEntity(ent,ent.getY(),EntityPlotter.ALIGN_POSITION_BUTTOM));
				ent.setY(newY);
			}
		}
		
	}
	private void alignToRight(){
		EntityPlotter lastEntity = selected.lastElement();
		int right =  lastEntity.getX() + lastEntity.getWidth();
		for(EntityPlotter ent:selected){
			if(ent!=lastEntity){
				int newX = right - ent.getWidth();
				undoActions.add(new ActionAlignEntity(ent,ent.getX(),EntityPlotter.ALIGN_POSITION_RIGHT));
				ent.setX(newX);
			}
		}
		
	}
	
	private EntityPlotter hitsEntity(Point p){
		p.x = (int)(p.x/zoomValue);
		p.y = (int)(p.y/zoomValue);
		for(EntityPlotter curEntity:entities.values()){
			if (curEntity.isVisible()){
				if (curEntity.getBounds().contains(p))
					return curEntity;
			}
		}
		return null;
	}
	public void trim(){
		// writing code for trimming the additional spaces from the diagram
		Rectangle2D visRect= getVisibleDiagramBounds();
		shiftEntities((int)visRect.getX(),(int)visRect.getY(), false);
		setSize((int)visRect.getWidth() + 10,(int)visRect.getHeight() + 10); // 10 is some extra space because the shapes are sometimes trimmed
		repaint();
		
	}
	private void shiftEntities(int dx,int dy,boolean forward){
		dx = dx * (forward?1:-1);
		dy = dy * (forward?1:-1);
		for(EntityPlotter curEnt:entities.values()){
			curEnt.setPosition(new Point(curEnt.getX()+dx,curEnt.getY()+dy));
		}
		
	}
	public void clearAll(){
		entities.clear();
		conectors.removeAllElements();
		selected.removeAllElements();
		undoActions.removeAllElements();
		draggedEntity       = null;
		latestHighlighted   = null;
		conFrom             = null;
		manager             = createManager();
		draggedCon          = null;
		curSize             = new Dimension(10,10);
		curVisRect          = new Rectangle();
		prevPanelRect       = new Rectangle();
		inDrag              = false;
		inConDrag           = false;
		searchCon           = false;
		inDeleteMode        = false;
		allowMultiSelection = true;
		allowEntityDelete   = true;
		doAutoLayout        = true;
		firstTimeCreation   = true;
		showToolbar         = true;
		processMode         = MODE_NO_PROCESSING;
		
	}
	private void checkScrolling(Point p,EntityPlotter ent){
		if (checkSize())
			repaint();
		EntityPlotter checkEnt = draggedEntity; 
		if ((checkEnt==null)&&(selected.size()>0))
			checkEnt = selected.get(0);
		if (checkEnt!=null){
			if ((checkEnt.getX()>=0)&&(checkEnt.getY()>=0)){
				Rectangle rect = checkEnt.getBounds().getBounds();
				//rect.setSize((int)(rect.width/zoomValue),(int)(rect.height/zoomValue));
				if (zoomValue==1)
					scrollRectToVisible(rect);
			}
		}
	}
	public Rectangle getVisibleRect(){
		return curVisRect;
	} 
	
	public boolean exportAsImage(final File destFile,final String format) {
		try {
			BufferedImage off_Image = exportImage(); 
			return ImageIO.write(off_Image, format, destFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public BufferedImage exportImage() {
		Dimension visSize = getVisibleDiagramSize();
		BufferedImage off_Image = 
			new BufferedImage(visSize.width+10, visSize.height+10, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = off_Image.createGraphics();
		exportingImage = true;
		paint(g2);
		exportingImage = false;
		g2.dispose();
		return off_Image;
	}
	public BufferedImage[] exportImage(int pwidth,int pheight) {
		BufferedImage big_Image = exportImage();
		Dimension     visSize   = getVisibleDiagramSize();
		
		int colCount    = (int)Math.ceil(visSize.getWidth()/pwidth);
		int rowCount    = (int)Math.ceil(visSize.getHeight()/pheight);
		//LOG.info(" &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  ============================ Exporting  image, the computed visual dimension is (" + visSize.getWidth() + "," + visSize.getHeight() + " while the supported page size is (" + pwidth + "," + pheight + ")");
		int totalNumber = rowCount * colCount;
		//LOG.info(" Row Count is  : (" + rowCount + ") Column count is : (" + colCount + ") ");
		if (totalNumber==1) return new BufferedImage[] {big_Image};
		BufferedImage[] images = new BufferedImage[totalNumber];
		int totalCounter = 0;
		for (int row=0;row<rowCount;row++){
			for (int col=0;col<colCount;col++){
				int x          = col*pwidth;
				int y          = row*pheight;
				int partWidth  = pwidth;
				int partHeight = pheight;
				if (x+pwidth>visSize.width)   partWidth  = visSize.width - x;
				if (y+pheight>visSize.height) partHeight = visSize.height - y;
				Rectangle partRect   = new Rectangle(x,y,partWidth,partHeight);
				//LOG.info("Reading the part (" + partRect.toString() + ") from the big image that have the size (w : " + big_Image.getWidth() + ",h:" + big_Image.getHeight() + ")");
				BufferedImage pageImg    = new BufferedImage(partWidth,partHeight,BufferedImage.TYPE_INT_ARGB);
				Graphics2D newg = pageImg.createGraphics();
				newg.drawImage(big_Image, 0, 0, partWidth, partHeight, x, y, x+partWidth, y+partHeight, null);
				newg.dispose();
				
				images[totalCounter++] = pageImg;
			}
		}
		return images;
	}

	
	public void print() {
		new Thread(new Runnable(){public void run(){
			try {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new Printable(){
					public int print(Graphics g, PageFormat pf,int pageIndex) throws PrinterException{
//						LOG.info("Call made to the printable interface");
						exportingImage = true;
						((Graphics2D)g).translate(pf.getImageableX(), pf.getImageableY());
						paint(g);
						exportingImage = false;
						return PAGE_EXISTS;
					}


				});
				
				boolean doPrint = job.printDialog();
				if(doPrint){
					job.print();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}}).start();
		
		
	}
	public void paintComponent(Graphics gr){
	
		//Thread.dumpStack();
		
		if ((entities==null)||(entities.size()==0)){ 
			super.paintComponent(gr);
			return;
		}
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D)gr;
		g.setRenderingHints(qualityHints);
		g.scale(zoomValue, zoomValue);
		Rectangle gRect = g.getClipBounds();
		//LOG.info("Graphic bounds : " + gRect.toString()); 
		if(!exportingImage){
			if (firstTimeCreation){
				LOG.info("***** Paint ******** In first time creation ");
				if (doAutoLayout){
					LOG.info("***** Paint ******** Doing Auto Layout");
					for(EntityPlotter curEntity:entities.values())
						curEntity.calibrate(g);
					manager.doLayout(entities, conectors);
				}else if (presetLayout!=null){
					LOG.info("***** Paint ******** Importing");
					importLayout(presetLayout);
				}
				firstTimeCreation = false;
			}else if ((!prevPanelRect.getSize().equals(gRect.getSize()))&&(doAutoLayout)){
				LOG.info("Graphics bounds changed doing an automatic layout .....");
				manager.setInitWidth(gRect.width);
				manager.setInitHeight(gRect.height);
				manager.doLayout(entities, conectors);
				prevPanelRect = gRect;
			}
			boolean sizeDetected = false;
			checkSize();
			paintBackground(g);
			
		}
		// Drawing connectors
		if (showConnectedEntities){
			drawAllConnections(g);
			for(EntityPlotter curEntity:entities.values())
				if ((curEntity.isVisible())&&((curEntity.isTransperant()))) curEntity.plot(g);
			
			/*
			drawTransConnections(g);
			for(EntityPlotter curEntity:entities.values())
				if ((curEntity.isVisible())&&((curEntity.isTransperant()))) curEntity.plot(g);
			drawNonTransConnections(g);
			*/
		}else{
			drawConnections(g);
		}	
		
		
		int originalWidth  = curSize.width;
		int originalHeight = curSize.height;
		int calcWidth      = curSize.width;
		int calcHeight     = curSize.height;
		
		// Drawing entities
		if (showConnectedEntities){
			for(EntityPlotter curEntity:entities.values())
				if ((curEntity.isVisible())&&((!curEntity.isTransperant()))) curEntity.plot(g);
		
		}else{
			for(EntityPlotter curEntity:entities.values())
				if (curEntity.isVisible())	curEntity.plot(g);
		}
		
		if (showToolbar)
			tools.plot(10,10, g);
		if((selecting)&&(selStartPnt!=null)&&(selEndPnt!=null))
			paintSelection(g);
	}
	
	private void paintBackground(Graphics2D g){
		g.setColor(getBackground());
		// Causes the performance to go down, but it is nice to have 
		//g.setPaint(new GradientPaint(0,0,Color.GRAY, gRect.width, gRect.height,Color.WHITE));
		//g.fillRect(gRect.x, gRect.y, gRect.width,gRect.height);
		g.fillRect(0, 0, curSize.width,curSize.height);
		// Drawing the company logo 
		g.drawImage(compLogo.getReflectImg(), 20, 21 + compLogo.getHeight(), null );
        g.drawImage(compLogo.getLogoImg(), 20, 20, null );

		
		
	}

	private void paintSelection(Graphics2D g){
		Shape sel = constructSelectionShape();
		g.setColor(selectionBG);
		g.fill(sel);
		g.setColor(selectionBR);
		g.setStroke(selStroke);
		g.draw(sel);
		if ((selRect.width>50)&&(selRect.height>30)){
			g.setColor(selTextColor);
			g.setFont(selFont);
			g.drawString("SMYLD", selRect.x + 5, selRect.y + 20);
		}
		
	}
	private Shape constructSelectionShape(){
		Point frm = selStartPnt;
		Point to  = selEndPnt;
		
		selRect.width  = Math.abs(selStartPnt.x-selEndPnt.x);
		selRect.height = Math.abs(selStartPnt.y-selEndPnt.y);
		selRect.x      = selStartPnt.x>selEndPnt.x?selEndPnt.x:selStartPnt.x;
		selRect.y      = selStartPnt.y>selEndPnt.y?selEndPnt.y:selStartPnt.y;
		return selRect;
		
	}
	private boolean checkSize(){
		Dimension contSize = getVisibleDiagramSize();
		contSize.setSize(contSize.width*zoomValue,contSize.height*zoomValue);
		if (sccontainer!=null){
			Dimension scSize = sccontainer.getVisibleRect().getSize();
			if (scSize.width>contSize.width)
				contSize.width = scSize.width;
			if (scSize.height>contSize.height)
				contSize.height = scSize.height;
	
		}
		
		if (!curSize.equals(contSize)){
			curSize.setSize(contSize);
			this.setSize(curSize);
			return true;
		}
		return false;
	
	}	
	public void doAutomaticLayout(){
		for (EntityPlotter ent:entities.values()) ent.setPosition(new Point(0,0));
		trim();
		doAutoLayout = false;
		createManager().doLayout(entities, conectors,true);
		//repaint();
		revalidate();
	}
	public Dimension getPreferredSize(){
		
		return curSize;
	}
	protected EntityPlotLayoutManager createManager(){
		return new EntityPlotLayoutManager();
	}
	private void drawAllConnections(Graphics2D g){
		if(connDraw){
			connPlotter.setInfo(entities, conectors, undoActions);
			connPlotter.drawAllConnections(g);
		}
	}
	

	private void drawTransConnections(Graphics2D g){
		if(connDraw){
			connPlotter.setInfo(entities, conectors, undoActions);
			connPlotter.drawTransperantConnections(g);
		}
	}
	private void drawNonTransConnections(Graphics2D g){
		if(connDraw){
			connPlotter.setInfo(entities, conectors, undoActions);
			connPlotter.drawNonTransperantConnections(g);
		}			
	}

	private void drawConnections(Graphics2D g){
		if(connDraw){
			connPlotter.setInfo(entities, conectors, undoActions);
			connPlotter.drawConnections(g);
		}
		/*
		g.setColor(Color.BLUE);
		g.setStroke(new BasicStroke(1.2f));
		for(EntityConnector curCon:conectors){
			if (!curCon.isDeactivated())
				processEntityConnection(curCon, g);
		}
		*/
	}
	private void drawToolBar(Graphics2D g){
		// Need to draw the toolbar (for testing purpose)
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(5,5, 200, 30, 5, 5);
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2));
		g.drawRoundRect(5,5, 200, 30, 5, 5);
		g.setColor(Color.YELLOW);
		g.fillRoundRect(15, 15, 5, 5, 2, 2);
		g.fillRoundRect(25, 25, 5, 5, 2, 2);
		g.setColor(Color.WHITE);
		g.drawLine(20, 20, 25, 25);
		
	}
	
	public void delete(EntityPlotter entity){
		if (allowEntityDelete){
			entity.setVisible(false);
			entity.setSelected(false);
			undoActions.add(new ActionDelEntity(entity));
			if(listener!=null) listener.entityDeleted(entity);
			repaint();
		}

	}
	public void delete(String entityID){
		if (allowEntityDelete){
			EntityPlotter entity = entities.get(entityID);
			if (entity!=null){
				entity.setVisible(false);
				entity.setSelected(false);
				undoActions.add(new ActionDelEntity(entity));
				if(listener!=null) listener.entityDeleted(entity);
				repaint();
			}
		}

	}

	public void addEntity(String id,EntityPlotter newEntity){
		if (entities.containsKey(id)){
//			LOG.info("Already exists - " + id);
			EntityPlotter old  = entities.get(id);
			//deleteConnectorsForEntity(old);
			old.setUserObject(newEntity.getUserObject());
			old.setType(newEntity.getType());
			old.setContents(newEntity.getContents());
			old.setTitle(newEntity.getTitle());
			old.setVisible(true);
			if (entGUIConfs.containsKey(newEntity.getType())){
				old.adjustToGUIConfigInfo(entGUIConfs.get(newEntity.getType()));
			}
//			LOG.info("Entity visible property is " + old.isVisible()); 
		}else{
			if (entGUIConfs.containsKey(newEntity.getType())){
				newEntity.adjustToGUIConfigInfo(entGUIConfs.get(newEntity.getType()));
			}

			entities.put(id, newEntity);
		}
		undoActions.add(new ActionAddEntity(newEntity));
		repaint();
	}
	private void deleteConnectorsForEntity(EntityPlotter ent){
		if (ent.canConnectFrom()){
			for(EntityPlotter curEnt:ent.getConnectedEntities().values())
				deleteConnector(ent, curEnt);
		}else{
			for(EntityPlotter curEnt:ent.getConnectedEntities().values())
				deleteConnector(curEnt, ent);
			
		}
	}
	private void processEntityConnection(EntityConnector con,Graphics2D g){
		// Process the lines to be connected to the nearest connection point
		if ((!con.from.isVisible())||(!con.to.isVisible())) return;
		con.drawLine(g);
	}
	private boolean onRight(Rectangle2D from,Rectangle2D to){
		return (from.getX()>(to.getX()+to.getWidth()));
	}
	private boolean onLeft(Rectangle2D from,Rectangle2D to){
		return ((from.getX()+from.getWidth())<(to.getX()));
	}
	private boolean onTop(Rectangle2D from,Rectangle2D to){
		return ((from.getY()+from.getHeight())<(to.getY()));
	}
	private boolean onBut(Rectangle2D from,Rectangle2D to){
		return ((from.getY())>(to.getY()+to.getHeight()));
	}
	public void connectEntities(EntityPlotter from,EntityPlotter to ){
		if (controller!=null){
			Color conColor = controller.getEntitiesConnectionColor(from,to);
			// The controller can decide whether to connect or not, in case no connection must be established the returned color should be null
			if (conColor!=null)
				connectEntities(from,to,conColor);
		}else{
			connectEntities(from,to,from.getConnectorColor());
		}
	}
	
	public void addController(EntityPlotController controller){
		this.controller = controller;
		connPlotter.addController(controller);
	}
	public void connectEntities(EntityPlotter from,EntityPlotter to ,Color conColor,String commentText){
		if (!alreadyExistingConnector(from, to,conColor,commentText)){
			EntityConnector newConn = createConnector(from,to,conColor,commentText);
			conectors.add(newConn);
			undoActions.add(new ActionAddConnection(newConn));
			from.newConnection(newConn);
			to.newConnection(newConn);
			if (listener!=null) listener.entityConnected(from, to);
		}
	}
	protected boolean alreadyExistingConnector(EntityPlotter from,EntityPlotter to,Color conColor,String commentText){
		boolean alreadyExists = false;
		for(EntityConnector con: conectors){
			if (con.connects(from, to)){
				con.setDeactivated(false);
				con.setConColor(conColor);
				if (commentText!=null) con.setCommentText(commentText);
				if (listener!=null) listener.entityConnected(from, to);
				alreadyExists = true;
			}
		}
		return alreadyExists;
	}
	public void connectEntities(EntityPlotter from,EntityPlotter to ,Color conColor){
		if (!alreadyExistingConnector(from, to,conColor,null)){
			EntityConnector newConn = createConnector(from, to, conColor);
			conectors.add(newConn);
			undoActions.add(new ActionAddConnection(newConn));
			from.newConnection(newConn);
			to.newConnection(newConn);
			if (listener!=null) listener.entityConnected(from, to);
		}
	}
	// Need to create factory for it
	protected EntityConnector createConnector(EntityPlotter from,EntityPlotter to ,Color conColor){
		//return new EntityConnector(from,to,from.getConnectorSize(),conColor);
		return new OrthogonalEntityConnector(from,to,from.getConnectorSize(),conColor);
	}
	protected EntityConnector createConnector(EntityPlotter from,EntityPlotter to ,Color conColor,String commentText){
		//return new EntityConnector(from,to,from.getConnectorSize(),conColor,commentText);
		return new OrthogonalEntityConnector(from,to,from.getConnectorSize(),conColor,commentText);
	}

	protected void deleteConnector(EntityConnector target){
		target.from.removeConnection(target);
		target.to.removeConnection(target);
		for(EntityConnector con: conectors){
			if (con.equals(target)){
				//conectors.remove(con);
				con.setDeactivated(true);
				if (listener!=null) listener.entityDisconnected(target.from, target.to);
				undoActions.add(new ActionDelConnection(con));
				repaint();
				return;
			}
		}
	}

	public void deleteConnector(EntityPlotter from,EntityPlotter to){
		EntityConnector target = null;
		for(EntityConnector con: conectors){
			if (con.connects(from, to)){
				target = con;
				break;
			}
		}
		if (target!=null) deleteConnector(target);
	}
	
	public boolean isShowToolbar() {
		return showToolbar;
	}

	public void setShowToolbar(boolean showToolbar) {
		this.showToolbar = showToolbar;
	}
	protected EntityConnector getConnectorFor(EntityPlotter from, EntityPlotter to){
		for(EntityConnector curCon: conectors){
			if (curCon.connects(from, to))
				return curCon;
		}
		return null;
	}
	
	public String getPresetLayout() {
		return presetLayout;
	}

	public void setPresetLayout(String presetLayout) {
		// Enabling the layout to take place by setting the relevant flags
		firstTimeCreation = true;
		doAutoLayout      = false;
		this.presetLayout = presetLayout;
	}
	public void setGUIConfig(HashMap<Integer,String> conf) {
		entGUIConfs = conf;
	}
	public String composeGUIConfig(HashMap<Integer,String> conf) {
		StringBuilder sb = new StringBuilder();
		
		for(String curConf:conf.values()){
			sb.append(curConf);
			sb.append("~~");
		}
		return sb.toString();
	}
	public HashMap<Integer,String> parseGUIConfig(String guiConfig) {
		HashMap<Integer,String> parsed = null;
		if (guiConfig!=null){
			String[] confs = guiConfig.split("~~");
			if ((confs!=null)&&(confs.length>0)){
				parsed = new HashMap<Integer,String>();
				for(String curConf:confs){
					if ((curConf!=null)&&(!curConf.isEmpty())){
						String entTypeText = curConf.substring(0,curConf.indexOf("|"));
						parsed.put(Integer.parseInt(entTypeText), curConf.substring(curConf.indexOf("|")+1));
					}
				}
			}
		}
		return parsed;
	}
	
	public void setGUIConfig(String guiConfig) {
		// Enabling the layout to take place by setting the relevant flags
		firstTimeCreation = true;
		entGUIConfs = parseGUIConfig(guiConfig);
	}

	class Toolbar{
		int selectedTool = TOOL_ADD_CONNECTOR;
		int x,y;
		boolean activateAddCon = false,activateDelCon = false;
		public void plot(int x,int y,Graphics2D g){
			this.x = x;
			this.y = y;
			g.setColor(Color.LIGHT_GRAY);
			g.fillRoundRect(x+5,y+5, 200, 30, 5, 5);
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2));
			g.drawRoundRect(x+5,y+5, 200, 30, 5, 5);
			
			// Drawing the connector adding icon
			g.setColor(activateAddCon?Color.YELLOW:Color.RED);
			g.fillRoundRect(x+10,y+10, 5, 5, 2, 2);
			g.fillRoundRect(x+25,y+25, 5, 5, 2, 2);
			g.setColor(Color.WHITE);
			g.setColor(activateAddCon?Color.WHITE:Color.BLUE);
			g.drawLine(x+15,y+15, x+25, y+25);
			// Drawing the connector adding icon
			g.setColor(activateDelCon?Color.YELLOW:Color.RED);
			g.fillRoundRect(x+50,y+10, 5, 5, 2, 2);
			g.fillRoundRect(x+65,y+25, 5, 5, 2, 2);
			g.setColor(Color.WHITE);
			g.setColor(activateDelCon?Color.WHITE:Color.BLUE);
			g.drawLine(x+55,y+15, x+65, y+25);
			g.setColor(activateDelCon?Color.WHITE:Color.BLACK);
			g.drawLine(x+57,y+15, x+63, y+25);
			g.drawLine(x+57,y+25, x+63, y+15);
			

		}
		public boolean hitsTool(Point p){
			// Checking if the connector tool was selected
			if (((p.x >=x+10)&&(p.x <=x+30))&&((p.y >=y+10)&&(p.y <=y+30))){
				selectedTool = TOOL_ADD_CONNECTOR;
				activateAddCon = !activateAddCon;
				activateDelCon = false;
				repaint();
				return true;
			}else if (((p.x >=x+50)&&(p.x <=x+70))&&((p.y >=y+10)&&(p.y <=y+30))){
				selectedTool = TOOL_DEL_CONNECTOR;
				activateDelCon = !activateDelCon;
				activateAddCon = false;
				repaint();
				return true;

			}
			return false;
		}
		public void activateAddCon(boolean activate){
			activateAddCon = activate;
		}
		public int getSelectedTool(){return selectedTool;}
		public boolean isSelecteToolActive(){
			switch(selectedTool){
				case TOOL_ADD_CONNECTOR:
					return activateAddCon;
				case TOOL_DEL_CONNECTOR:
					return activateDelCon;

			}
			return false;
		}
		
	}
	
	
	/*
	 * 
	 *  Setters and getters methods
	 * 
	 * */
	
	/**
	 * @return the showConnectedEntities
	 */
	public boolean isShowConnectedEntities() {
		return showConnectedEntities;
	}

	/**
	 * @param showConnectedEntities the showConnectedEntities to set
	 */
	public void setShowConnectedEntities(boolean showConnectedEntities) {
		if (this.showConnectedEntities==showConnectedEntities) return;
		this.showConnectedEntities = showConnectedEntities;
		if (!showConnectedEntities){
			for(EntityPlotter ent:entities.values()) ent.setTransperant(false); // Resetting all entities to be non Transparent 
		}else{
			for(EntityPlotter ent:entities.values()){
				ent.setEntityTransperancy(showConTransp); // Resetting all entities to be non Transparent
				//ent.setBGImageTransperancy(showConTransp);
			} 
		}
		repaint();
	}
	
	
	

	/**
	 * @return the animationActive
	 */
	public boolean isAnimationActive() {
		return animationActive;
	}

	/**
	 * @param animationActive the animationActive to set
	 */
	public void setAnimationActive(boolean animationActive) {
		this.animationActive = animationActive;
	}
	
	

	/*
	 * Methods for the scrollable interface
	 * */	
	public Dimension getPreferredScrollableViewportSize() {
		
		return curSize;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		//return 60;
		int maxUnitIncrement = 200;
		//Get the current position.
	    int currentPosition = 0;
	    if (orientation == SwingConstants.HORIZONTAL) {
	        currentPosition = visibleRect.x;
	    } else {
	        currentPosition = visibleRect.y;
	    }

	    //Return the number of pixels between currentPosition
	    //and the nearest tick mark in the indicated direction.
	    if (direction < 0) {
	        int newPosition = currentPosition -
	                         (currentPosition / maxUnitIncrement)
	                          * maxUnitIncrement;
	        return (newPosition == 0) ? maxUnitIncrement : newPosition;
	    } else {
	        return ((currentPosition / maxUnitIncrement) + 1)
	                 * maxUnitIncrement
	                 - currentPosition;
	    }
		
		
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	public boolean getScrollableTracksViewportWidth() {
		return false;
	}
	public int getScrollableUnitIncrement(Rectangle visibleRect,
			int orientation, int direction) {
		//return 100;
		int maxUnitIncrement = 200;
	    if (orientation == SwingConstants.HORIZONTAL)
	        return visibleRect.width - maxUnitIncrement;
	    else
	        return visibleRect.height - maxUnitIncrement;		
	}
	
	protected boolean containsCommentedConnection(EntityPlotter ent, String comment,boolean from){
		return (getCommentedConnection(ent, comment,from)!=null);
	}
	protected EntityConnector getCommentedConnection(EntityPlotter ent, String comment,boolean from){
		if((ent!=null)&&(ent.getConnections()!=null)&&(ent.getConnections().size()>0)){
			for(EntityConnector curCon: ent.getConnections()){
				if ((curCon.getCommentText()!=null)&&(curCon.getCommentText().equals(comment))){
					if (from){
						if (curCon.getFrom().getId().equals(ent.getId()))
							return curCon;
					}else{
						if (curCon.getTo().getId().equals(ent.getId()))
							return curCon;
						
					}
				}
			}
		}
		return null;
	}

	public static final int TOOL_ADD_CONNECTOR = 1;
	public static final int TOOL_DEL_CONNECTOR = 2;
	
	public static final int MODE_NO_PROCESSING = 0;
	public static final int MODE_ADD_CONNECTOR = 1;
	public static final int MODE_DEL_CONNECTOR = 2;

	
}
