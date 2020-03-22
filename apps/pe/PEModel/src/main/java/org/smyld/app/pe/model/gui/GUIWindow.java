package org.smyld.app.pe.model.gui;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.smyld.app.pe.model.gui.holders.GUIToolbarHolder;

import java.util.HashMap;

/**
 * 
 * @author
 * @version
 * @see
 * @since
 */
@Setter
@Getter
@Slf4j
public class GUIWindow extends GUIComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String menuBarID;
	HashMap<String, GUIToolbarHolder> toolbars;
	WindowType windowType = WindowType.unknown;
	String lableID;
	String body;
	String bodyType;
	String bodyID;
	String startUpMethod;
	String bodyListenerTarget;
	String menuHandler;
	String resizable;
	String toolbarID;
	String startup;
	String statusBar;
	/**
	 * 
	 * @see
	 * @since
	 */
	public GUIWindow() {
	}

	public void addToolbar(GUIToolbarHolder newToolbar){
		if (toolbars==null) toolbars = new HashMap<>();
		toolbars.put(newToolbar.getToolbar().getID(),newToolbar);
	}

	private void addToObjectInfo(String label,String value,StringBuffer sb){
		if (value!=null) {
			sb.append(label);
			sb.append(" : ");
			sb.append(value);
			sb.append("\n");
		}
	}



	public String toString(){
		StringBuffer sb = new StringBuffer();
		addToObjectInfo("ID        ", getID(),sb);
		addToObjectInfo("Type      ", getWindowType().name(),sb);

		addToObjectInfo("Menu Bar  ", getMenuBarID(),sb);
		addToObjectInfo("Body      ", getBody(),sb);
		addToObjectInfo("Resizable ", getResizable(),sb);
		addToObjectInfo("Startup   ", getStartup(),sb);
		if ((getToolbars()!=null) && (getToolbars().size()>0)){
			StringBuffer sbuff = new StringBuffer();
			getToolbars().forEach((id,el)->{
				sbuff.append("id : ");
				sbuff.append(id);
				sbuff.append(",");
			});
			addToObjectInfo("Toolbars", sbuff.toString(),sb);
		}
		return sb.toString();
	}






}
