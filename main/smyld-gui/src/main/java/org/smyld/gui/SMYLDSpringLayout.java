package org.smyld.gui;

import java.awt.Component;

import javax.swing.SpringLayout;

public class SMYLDSpringLayout extends SpringLayout {
	public void addLayoutComponent(Component comp,Object constraints) {
		if (comp instanceof SMYLDLabeledComponent){
			SMYLDLabeledComponent curComp = (SMYLDLabeledComponent)comp;
			super.addLayoutComponent(curComp.getLabelComponent(), constraints);
			super.addLayoutComponent(curComp.getMainComponent(), constraints);
			
		}else{
			super.addLayoutComponent(comp, constraints);
		}
	}
}
