package org.smyld.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.Popup;
import javax.swing.PopupFactory;

import org.smyld.gui.panels.DatePanel;
import org.smyld.resources.Resource;
import org.smyld.text.TextUtil;
import org.smyld.util.SMYLDDate;

public class DateControl extends SMYLDPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image square;
	Image selSquare;
	Image rightArrow;
	Image leftArrow;
	Image rightOArrow;
	Image leftOArrow;

	Graphics2D g;
	int mouseX;
	int mouseY;
	int selX;
	int selY;
	int curSelection = -1;
	int firstDay;
	int maxDays;
	int curMonthDay;
	Dimension origSize;
	Point origPoint;
	Color bgColor;
	Color mouseOverColor;
	Color daysForegroundColor;
	Color daysSelectedColor;
	Color dayNamesFRColor;
	Color selBgColor;
	Color daysBgColor;
	Color headerBG;
	Color minimizedBGColor;
	boolean inSelect;
	boolean draw3D;
	public boolean isPopedUp = false;
	boolean localResize = false;
	boolean localRelocated = false;
	boolean paintDown = true;
	boolean showAsCombo = true;
	boolean showDate = false;

	String[] days = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };
	GregorianCalendar cal;

	SMYLDDate date;
	String[] dayNames;
	String[] monthNames;

	Popup popWindow;
	DatePanel popDate;

	public DateControl(SMYLDDate startDate) {
		date = startDate;
		init();
	}

	public DateControl() {

		init();
	}

	private void init() {
		System.out.println("Date Panel version 1.002");
		initDate();
		// origPoint = getLocation();
		Resource resource = new Resource();
		bgColor = new Color(31, 120, 247);
		minimizedBGColor = Color.WHITE;
		rightArrow = resource.getImage("arrowr_3d_w.png");

		setBackground(minimizedBGColor);
		setForeground(daysForegroundColor);
		origSize = getComboSize();

		// square = new Resource().getImage("square_3d.png");

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				selX = evt.getX();
				selY = evt.getY();
				inSelect = true;
				repaint();
				// inSelect = false;
			}
		});
	}

	private void initDate() {
		if (date == null) {
			date = new SMYLDDate(SMYLDDate.FRM_MMM);
		}
		cal = (GregorianCalendar) Calendar.getInstance();
		cal.setTime(date);
		refreshDate();
	}

	private void refreshDate() {
		// cal.set(cal.MONTH,0);
		curSelection = cal.get(Calendar.DATE);
		cal.set(Calendar.DATE, 1);
		firstDay = cal.get(Calendar.DAY_OF_WEEK);
		maxDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// System.out.println(firstDay);
		// System.out.println(maxDays);

	}

	@Override
	public void setSize(int width, int height) {
		// System.out.println("Set size called : " + width + "," + height + " ,
		// local call:" + localResize);
		if (!localResize) {
			origSize = new Dimension(width, height);
		}
		super.setSize(width, height);
		localResize = false;
	}

	@Override
	public void setSize(Dimension newSize) {
		if (!localResize) {
			origSize = newSize;
		}
		super.setSize(newSize);
		localResize = false;
	}

	@Override
	public void paint(Graphics g) {
		if (origPoint == null) {
			origPoint = getLocation();
		}
		/*
		 * localRelocated = true; if ((!paintDown)&&(isPopedUp))
		 * super.setLocation(origSize.width - 7*32,origPoint.y + origSize.height -
		 * 7*32); else super.setLocation(origPoint);
		 */
		this.g = (Graphics2D) g;
		// setOpaque(true);
		// g.clearRect(0,0,getWidth(),getHeight());
		if (isPopedUp) {
			paintPoped();
		} else if (popWindow != null) {
			popWindow.hide();
			
		}else{
			paintUnPoped();
		}

		
		this.g.setStroke(new BasicStroke(2.1f, 1, BasicStroke.JOIN_ROUND));
		g.drawRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
	}

	@Override
	public void setLocation(Point newPoint) {
		if (!localRelocated) {
			origPoint = newPoint;
		}
		super.setLocation(newPoint);
		localRelocated = false;
	}

	@Override
	public void setLocation(int x, int y) {
		// System.out.println("Set location called : " + x + "," + y + " , local
		// call:" + localRelocated);
		if (!localRelocated) {
			origPoint = new Point(x, y);
		}
		super.setLocation(x, y);
		localRelocated = false;
	}

	/*
	 * public void setBounds(int x,int y,int width,int height){
	 * System.out.println("Set Bounds called : " + x + "," + y + "," + width +
	 * "," + height); if (!localRelocated) origPoint = new Point(x,y); if
	 * (!localResize) origSize = new Dimension(width,height); localRelocated =
	 * false; localResize = false; super.setBounds(x,y,width,height); }
	 */

	private void paintUnPoped() {

		this.setOpaque(false);
		curMonthDay = 0;
		if (origSize == null) {
			origSize = getComboSize();
		}
		setSize(origSize);
		g.clearRect(origPoint.x, origPoint.y, origSize.width, origSize.height);
		g.setColor(getBackground());
		g.fillRect(0, 0, origSize.width, origSize.height);
		g.setColor(getForeground());
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.drawString(date.toString("dd-MMM-yy"), 10, 15);
		if (isMousePointing(origSize.width - 20, 0, 20, 20)) {
			g.drawImage(rightOArrow, origSize.width - 20, 0, 20, 20, headerBG,
					null);
		} else {
			g.drawImage(rightArrow, origSize.width - 20, 0, 20, 20, headerBG,
					null);
		}
		if ((inSelect)
				&& (isInside(origSize.width - 20, 0, 20, 20, selX, selY))) {
			isPopedUp = true;
			//repaint();
			inSelect = false;
		}
		// container.setBounds(this.getBounds());
		// container.show();
	}

	private Dimension getComboSize() {
		return new Dimension(100, 20);
	}
	@SuppressWarnings("unused")
	private Dimension getPopSize() {
		return new Dimension(7 * 32, 8 * 32);
	}

	private void paintPoped() {
		Point scrLoc = this.getLocationOnScreen();
		if (popDate == null) {
			popDate = new DatePanel(date, this);
		}
		PopupFactory popupFactory = PopupFactory.getSharedInstance();
		popWindow = popupFactory.getPopup(this, popDate, scrLoc.x, scrLoc.y);
		popWindow.show();
	}
	@SuppressWarnings("unused")
	private void paintCalendarHeader() {
		// g.setColor(headerBG);
		GradientPaint gp = new GradientPaint(0, 0, Color.BLUE, 7 * 32, 32,
				Color.BLACK, true);
		this.g.setPaint(gp);

		g.fillRect(0, 0, 7 * 32, 32);
		// Drawing month
		paintBetweenArrows(date.toString(), 25, 0);
		paintBetweenArrows(Integer.toString(cal.get(Calendar.YEAR)), 115, 0);
		if (inSelect) {
			if (isInside(25, 6, 20, 20, selX, selY)) {
				int origMonth = cal.get(Calendar.MONTH);
				cal.set(Calendar.MONTH, origMonth - 1);
				refreshDate();
				date.setTime(cal.getTime().getTime());
			} else if (isInside(76, 6, 20, 20, selX, selY)) {
				int origMonth = cal.get(Calendar.MONTH);
				cal.set(Calendar.MONTH, origMonth + 1);
				refreshDate();
				date.setTime(cal.getTime().getTime());
			} else if (isInside(115, 6, 20, 20, selX, selY)) {
				int origYear = cal.get(Calendar.YEAR);
				cal.set(Calendar.YEAR, origYear - 1);
				refreshDate();
				date.setTime(cal.getTime().getTime());
			} else if (isInside(173, 6, 20, 20, selX, selY)) {
				int origYear = cal.get(Calendar.YEAR);
				cal.set(Calendar.YEAR, origYear + 1);
				refreshDate();
				date.setTime(cal.getTime().getTime());

			}

			inSelect = false;
			repaint();

		}
	}

	private void paintBetweenArrows(String text, int x, int y) {
		int icSize = 20;
		int ydif = 6;
		int textW = 7 * text.length() + 10;
		g.setColor(new Color(0, 0, 0, 0));
		if (isMousePointing(x, ydif, 20, 20)) {
			// g.drawImage(leftOArrow,x,ydif,icSize,icSize,headerBG,null);
			g.drawImage(leftOArrow, x, ydif, icSize, icSize, null);
		} else {
			// g.drawImage(leftArrow,x,ydif,icSize,icSize,headerBG,null);
			g.drawImage(leftArrow, x, ydif, icSize, icSize, null);
		}
		g.setColor(dayNamesFRColor);
		g.drawString(text, x + 25, 20);
		if (isMousePointing(x + textW + 20, ydif, 20, 20)) {
			g
					.drawImage(rightOArrow, x + textW + 20, ydif, icSize,
							icSize, null);
		} else {
			g.drawImage(rightArrow, x + textW + 20, ydif, icSize, icSize, null);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return origSize;
	}

	@Override
	public Dimension getMinimumSize() {
		return origSize;
	}

	@Override
	public Dimension getMaximumSize() {
		return origSize;
	}
	@SuppressWarnings("unused")
	private void paintOn(int x, int y, int count, Color textColor,
			boolean enableMouse) {
		paintOn(x, y, Integer.toString(count), textColor, enableMouse);
	}

	private void paintOn(int x, int y, String text, Color textColor,
			boolean enableMouse) {

		if (draw3D) {
			g.drawImage(square, x, y, 32, 32, null);
		} else {
			if ((TextUtil.isNumeric(text)) || (TextUtil.isEmpty(text))) {
				g.setColor(daysBgColor);
			} else {
				g.setColor(bgColor);
			}
			g.fillRect(x, y, 32, 32);
		}
		if (enableMouse) {
			if (isSelected(x + 1, y + 1, 30, 30, text)) {
				g.setColor(selBgColor);
				if (draw3D) {
					g.drawImage(selSquare, x, y, 32, 32, null);
				} else {
					g.setColor(Color.BLACK);
					g.fillOval(x + 1, y + 6, 31, 20);
					g.setColor(selBgColor);
					g.fillOval(x + 4, y + 9, 26, 14);
				}
				// g.setColor(selBgColor);
				// g.fillRect(x,y,32,32);
				g.setColor(daysSelectedColor);

			} else if (isMousePointing(x + 1, y + 1, 30, 30)) {
				g.setColor(mouseOverColor);
			} else {
				g.setColor(textColor);
			}
		} else {
			g.setColor(textColor);
		}
		int pos = 13;
		if (text.length() == 2) {
			pos = 10;
		} else if (text.length() == 3) {
			pos = 5;
		}
		g.drawString(text, x + pos, y + 20);

	}

	private boolean isMousePointing(int x, int y, int w, int h) {
		return isInside(x, y, w, h, mouseX, mouseY);
	}

	private boolean isSelected(int x, int y, int w, int h, String text) {
		if (!isInside(x, y, w, h, selX, selY)) {
			if (TextUtil.isNumeric(text)) {
				return (curSelection == Integer.parseInt(text));
			}

		} else if (TextUtil.isNumeric(text)) {
			curSelection = Integer.parseInt(text);
			cal.set(Calendar.DATE, curSelection);
			date.setTime(cal.getTimeInMillis());
			refreshDate();
			return true;
		}
		return false;
	}

	private boolean isInside(int x, int y, int w, int h, int testX, int testY) {
		if ((testX >= x) && (testX <= x + w)) {
			return ((testY >= y) && (testY <= y + h));
		}
		return false;
	}

	public Date getSelectedDate() {
		return date;
	}

	/*
	public boolean alwaysOnTop() {
		return true;
	}*/

	public String[] getDayNames() {
		return dayNames;
	}

	public void setDayNames(String[] dayNames) {
		this.dayNames = dayNames;
	}

	public String[] getMonthNames() {
		return monthNames;
	}

	public void setMonthNames(String[] monthNames) {
		this.monthNames = monthNames;
	}

	public boolean isShowAsCombo() {
		return showAsCombo;
	}

	public void setShowAsCombo(boolean showAsCombo) {
		this.showAsCombo = showAsCombo;
	}

}
