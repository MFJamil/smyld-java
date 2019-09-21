package org.smyld.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SMYLDLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon rolloverIcon;
	ImageIcon originalIcon;

	public SMYLDLabel(String Title) {
		super(Title);
	}

	public SMYLDLabel(String Title, Icon icon, int horizontalAlignment) {
		super(Title, icon, horizontalAlignment);
	}

	public SMYLDLabel(Icon icon) {
		super(icon);
	}

	public SMYLDLabel(String Title, Icon icon) {
		super(Title, icon, SwingConstants.RIGHT);
		originalIcon = (ImageIcon) icon;
	}

	public void setRolloverIcon(ImageIcon newIcon) {
		rolloverIcon = newIcon;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				setIcon(rolloverIcon);
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				setIcon(originalIcon);
			}

		});
	}

	public void refresh() {
		if ((rolloverIcon != null) && (originalIcon != null)) {
			setIcon(originalIcon);
		}
	}

	public void setIcon(ImageIcon labelIcon) {
		if (rolloverIcon == null) {
			originalIcon = labelIcon;
		}
		super.setIcon(labelIcon);
	}

	@Override
	public void setText(String newValue) {
		super.setText(newValue);
	}

}
