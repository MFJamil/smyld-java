package org.smyld.test;

import java.awt.image.BufferedImage;

public interface PCControlerListener {
	public int getCaptureInterval();

	public void newShot(BufferedImage shot);
}
