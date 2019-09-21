package com.smyld.gui.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import com.smyld.gui.SMYLDDialog;
import com.smyld.resources.Resource;

/**
 * Title: Description: Copyright: Copyright (c) Company:
 * 
 * @author
 * @version 1.0
 */

public class SMYLDProgressWindow extends SMYLDDialog implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JPanel jPanel3 = new JPanel();
	JPanel jPanel4 = new JPanel();
	JPanel jPanel5 = new JPanel();
	JPanel jPanel6 = new JPanel();
	JPanel jPanel7 = new JPanel();
	JPanel jPanel9 = new JPanel();
	JPanel jPanel10 = new JPanel();
	JPanel jPanel8 = new JPanel();
	JProgressBar singleBar = new JProgressBar();
	JProgressBar totalBar = new JProgressBar();
	JButton buttonOK = new JButton();
	JButton buttonCancel = new JButton();
	BorderLayout borderLayout1 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	BorderLayout borderLayout3 = new BorderLayout();
	BorderLayout borderLayout4 = new BorderLayout();
	BorderLayout borderLayout5 = new BorderLayout();
	FlowLayout flowLayout1 = new FlowLayout();
	JCheckBox doClose = new JCheckBox();
	JLabel singleStatement = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel totalStatement = new JLabel();
	JLabel progressIcon = new JLabel();
	Box box1;
	private boolean canceled;
	private boolean ok;

	// private FTPsender ftpThread;

	public SMYLDProgressWindow(Frame parentFrame, String title) {
		super(parentFrame);
		setTitle(title);
		try {
			jbInit();
			pack();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	void jbInit() throws Exception {

		buttonOK.setText(BTN_OK_TEXT);
		buttonOK.setEnabled(false);
		buttonOK.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonOK_actionPerformed(e);
			}
		});
		buttonCancel.setText(BTN_CANCEL_TEXT);
		buttonCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonCancel_actionPerformed(e);
			}
		});
		singleStatement.setHorizontalTextPosition(SwingConstants.LEFT);
		jLabel2.setText(LBL_CLOSINGWINDOW_TEXT);

		box1 = Box.createVerticalBox();
		jPanel8.setLayout(borderLayout4);
		jPanel8.add(singleStatement, BorderLayout.CENTER);
		jPanel8.add(jPanel9, BorderLayout.WEST);
		jPanel10.setLayout(borderLayout5);
		jPanel10.add(totalStatement, BorderLayout.CENTER);
		jPanel1.setLayout(borderLayout2);
		progressIcon.setIcon(new Resource("com.smyld.resources.Resource")
				.getImageIcon("img/" + ICON_FILEUPLOAD));
		progressIcon.setIconTextGap(0);
		progressIcon.setHorizontalAlignment(SwingConstants.CENTER);
		jPanel1.add(jPanel5, BorderLayout.WEST);
		jPanel1.add(jPanel6, BorderLayout.EAST);
		jPanel1.add(progressIcon, BorderLayout.CENTER);
		jPanel1.add(jPanel8, BorderLayout.SOUTH);
		jPanel4.setLayout(flowLayout1);
		jPanel4.add(buttonOK, null);
		jPanel4.add(buttonCancel, null);
		jPanel7.setLayout(borderLayout3);
		jPanel7.add(jLabel2, BorderLayout.CENTER);
		jPanel7.add(doClose, BorderLayout.WEST);
		box1.add(singleBar, null);
		box1.add(jPanel10, null);
		box1.add(totalBar, null);
		box1.add(jPanel7, null);

		this.getContentPane().setLayout(borderLayout1);
		this.getContentPane().add(box1, BorderLayout.CENTER);
		this.getContentPane().add(jPanel2, BorderLayout.WEST);
		this.getContentPane().add(jPanel3, BorderLayout.EAST);
		this.getContentPane().add(jPanel4, BorderLayout.SOUTH);
		this.getContentPane().add(jPanel1, BorderLayout.NORTH);
		this.setResizable(true);
		this.setModal(true);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				canceled = true;
				dispose();
			}
		});
	}

	public void setProgressIcon(ImageIcon newIcon) {
		progressIcon.setIcon(newIcon);
	}

	@Override
	public void show() {
		Thread separateThread = new Thread(this);
		separateThread.start();
	}

	public void hideSingleBar() {
		singleBar.setVisible(false);
		singleStatement.setVisible(false);
		pack();
	}

	public void hideTotalBar() {
		totalBar.setVisible(false);
		totalStatement.setVisible(false);
		pack();
	}

	void buttonOK_actionPerformed(ActionEvent e) {
		ok = true;
		dispose();
	}

	void buttonCancel_actionPerformed(ActionEvent e) {
		// ftpThread.setStopThread(true);
		setCanceled(true);
		dispose();
	}

	public void setCurrentItem(int newValue) {
		singleBar.setValue(newValue);
		pack();
	}

	public void setCurrentItemRange(int newValue) {
		singleBar.setMaximum(newValue);
	}

	public void setAllItems(int newValue) {
		totalBar.setValue(newValue);
		detectEnd();
		pack();
	}

	private void detectEnd() {
		if (totalBar.getValue() >= totalBar.getMaximum()) {
			if (doClose.isSelected()) {
				ok = true;
				dispose();
			} else {
				buttonOK.setEnabled(true);
			}
		}

	}

	public void setAllItemsRange(int newValue) {
		totalBar.setMaximum(newValue);
	}

	public int getAllItemsRange() {
		return totalBar.getMaximum();
	}

	public void enhanceTotalItems(int increaseAmount) {
		totalBar.setValue(totalBar.getValue() + increaseAmount);
		detectEnd();
		pack();
	}

	public int getCurrentItemsRange() {
		return singleBar.getMaximum();
	}

	public void setCurrentItemStatement(String newText) {
		singleStatement.setText(newText);

		pack();
	}

	public void setAllItemsStatement(String newText) {
		totalStatement.setText(newText);
		pack();
	}

	public void setWindowTitle(String newTitle) {
		setTitle(newTitle);
		pack();
	}

	public void setCanceled(boolean newCanceled) {
		canceled = newCanceled;
	}

	public boolean isCanceled() {

		return canceled;
	}

	public static final String ICON_QUESTION = "QUESTION.gif";
	public static final String BTN_OK_TEXT = "Ok";
	public static final String BTN_CANCEL_TEXT = "Cancel";
	public static final String LBL_CLOSINGWINDOW_TEXT = "Close window when finished";
	public static final String ICON_FILEUPLOAD = "FILECOPY.gif";

	public void run() {
		while ((!canceled) && (!ok)) {
			if (!this.isVisible()) {
				this.show();
				repaint();
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {

			}
		}

	}
}
