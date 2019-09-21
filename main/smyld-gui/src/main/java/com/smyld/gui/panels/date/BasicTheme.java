package com.smyld.gui.panels.date;

import java.awt.Color;
import java.awt.Image;

import com.smyld.SMYLDObject;

public class BasicTheme extends SMYLDObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image arrowRight;
	Image arrowLeft;
	Image square;
	Image selSquare;
	Image arrowRightOver;
	Image arrowLeftOver;
	Color bgColor;
	Color selBGColor;
	Color mouseOverColor;
	Color daysForegroundColor;
	Color selDaysColor;
	Color dayBGColor;
	Color headerBGColor;
	Color headerFGColor;
	Color dayNamesFGColor;
	Color dayNameBGColor;

	public BasicTheme() {
	}

	public Image getArrowRight() {
		return arrowRight;
	}

	public void setArrowRight(Image arrowRight) {
		this.arrowRight = arrowRight;
	}

	public Image getArrowLeft() {
		return arrowLeft;
	}

	public void setArrowLeft(Image arrowLeft) {
		this.arrowLeft = arrowLeft;
	}

	public Image getSquare() {
		return square;
	}

	public void setSquare(Image square) {
		this.square = square;
	}

	public Image getSelSquare() {
		return selSquare;
	}

	public void setSelSquare(Image selSquare) {
		this.selSquare = selSquare;
	}

	public Image getArrowRightOver() {
		return arrowRightOver;
	}

	public void setArrowRightOver(Image arrowRightOver) {
		this.arrowRightOver = arrowRightOver;
	}

	public Image getArrowLeftOver() {
		return arrowLeftOver;
	}

	public void setArrowLeftOver(Image arrowLeftOver) {
		this.arrowLeftOver = arrowLeftOver;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Color getSelBGColor() {
		return selBGColor;
	}

	public void setSelBGColor(Color selBGColor) {
		this.selBGColor = selBGColor;
	}

	public Color getMouseOverColor() {
		return mouseOverColor;
	}

	public void setMouseOverColor(Color mouseOverColor) {
		this.mouseOverColor = mouseOverColor;
	}

	public Color getDaysForegroundColor() {
		return daysForegroundColor;
	}

	public void setDaysForegroundColor(Color daysForegroundColor) {
		this.daysForegroundColor = daysForegroundColor;
	}

	public Color getSelDaysColor() {
		return selDaysColor;
	}

	public void setSelDaysColor(Color selDaysColor) {
		this.selDaysColor = selDaysColor;
	}

	public Color getDayBGColor() {
		return dayBGColor;
	}

	public void setDayBGColor(Color dayBGColor) {
		this.dayBGColor = dayBGColor;
	}

	public Color getHeaderBGColor() {
		return headerBGColor;
	}

	public void setHeaderBGColor(Color headerBGColor) {
		this.headerBGColor = headerBGColor;
	}

	public Color getHeaderFGColor() {
		return headerFGColor;
	}

	public void setHeaderFGColor(Color headerFGColor) {
		this.headerFGColor = headerFGColor;
	}

	public Color getDayNamesFGColor() {
		return dayNamesFGColor;
	}

	public void setDayNamesFGColor(Color dayNamesFGColor) {
		this.dayNamesFGColor = dayNamesFGColor;
	}

	public Color getDayNameBGColor() {
		return dayNameBGColor;
	}

	public void setDayNameBGColor(Color dayNameBGColor) {
		this.dayNameBGColor = dayNameBGColor;
	}
}
