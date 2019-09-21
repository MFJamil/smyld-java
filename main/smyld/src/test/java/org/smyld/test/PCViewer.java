package org.smyld.test;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

/*
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
*/

public class PCViewer extends Robot implements Runnable {
	boolean viewActive;
	int viewInterval = 200;
	int sliceIndex = 0;
	PCControlerListener listener;

	public PCViewer() throws AWTException {

	}

	public void startView() {
		viewActive = true;
		new Thread(this).start();
	}

	public void stopView() {
		viewActive = false;
	}

	public void run() {
		try {
			while (viewActive) {
				// view process
				processView();
				if (listener != null)
					Thread.sleep(listener.getCaptureInterval());
				else
					Thread.sleep(viewInterval);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void processView() {
		try {
			BufferedImage scrschot = createScreenCapture(new Rectangle(Toolkit
					.getDefaultToolkit().getScreenSize()));
			if (listener != null) {
				listener.newShot(scrschot);
			} else {
				FileOutputStream fout = new FileOutputStream(
						"d:/docs/pcviewer/img_" + sliceIndex++ + ".jpg");
				// JPEGImageEncoder no longer exists, we need to user the standard ImageIO instead 
				//JPEGImageEncoder jpEncoder = JPEGCodec.createJPEGEncoder(fout);
				//jpEncoder.encode(scrschot);
				fout.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JPEGCodec jp = new JPEGCodec(
		// scrschot.getRaster().getDataBuffer().
	}

	public void addPCControlViewer(PCControlerListener newListener) {
		listener = newListener;
	}

}
