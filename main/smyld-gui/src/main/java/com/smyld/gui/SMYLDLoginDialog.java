package com.smyld.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This dialog is used to enable the user authorize himself during the
 * initialization of the control console
 */
public class SMYLDLoginDialog extends SMYLDDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SMYLDPanel mainPanel; // The main panel contained in this dialog

	protected SMYLDLabeledTextField     userField; // The text field for the user name
	protected SMYLDLabeledPasswordField pwdField; // The test field for password

	protected SMYLDButton ok; // The OK Button
	protected SMYLDButton cancel; // The cancel button
	protected SMYLDButton reset; // The reset button

	private static int attempts = 0; // Trace the attempts for logging in

	// private HVEDataProcess data; //The object communicating with the server
	// private HVEUser user;

	/**
	 * Constructs a new LoginDialog with the a parent and HVEDataProces object
	 * 
	 * @param owner
	 *            the owner frame of this dialog
	 * @param source
	 *            the object communicating to the server on the client side
	 */
	public SMYLDLoginDialog(JFrame owner) {
		super(owner);
		jbinit();
		closeWindow();
	}

	/**
	 * Initializes the graphical components contained in this Login Dialog
	 */
	private void jbinit() {
		userField = new SMYLDLabeledTextField("User Name",40);
		pwdField  = new SMYLDLabeledPasswordField("User Password",40);
		initButtons();
		initPanels();

		mainPanel.setBorder(BorderFactory.createTitledBorder("Login Info "));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		this.setResizable(false);
		this.setSize(450, 200);
		this.setModal(true);
		centerWindow();
	}

	/**
	 * Initializes the buttons contained in this dialog and adds the action
	 * listeners to them
	 */
	private void initButtons() {
		ok = new SMYLDButton("OK");
		cancel = new SMYLDButton("Cancel");
		reset = new SMYLDButton("Reset");

		ok.addActionListener(this);
		cancel.addActionListener(this);
		reset.addActionListener(this);
	}

	/**
	 * Initialises the sub-panels contained in this dialog and the components to
	 * them
	 */
	private void initPanels() {
		//SMYLDPanel pane1 = new SMYLDPanel();
		
		
		
		
		//*
		SMYLDPanel pane1 = new SMYLDPanel();
		pane1.setLayout(new BoxLayout(pane1, BoxLayout.X_AXIS));
		pane1.add(new SMYLDLabel("User name: "));
		pane1.add(Box.createRigidArea(new Dimension(10, 0)));
		pane1.add(userField);

		SMYLDPanel pane2 = new SMYLDPanel();
		pane2.setLayout(new BoxLayout(pane2, BoxLayout.X_AXIS));
		pane2.add(new SMYLDLabel("Password: "));
		pane2.add(Box.createRigidArea(new Dimension(13, 0)));
		pane2.add(pwdField);

		SMYLDPanel btnPane = new SMYLDPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.X_AXIS));
		btnPane.add(ok);
		btnPane.add(Box.createRigidArea(new Dimension(3, 0)));
		btnPane.add(cancel);
		btnPane.add(Box.createRigidArea(new Dimension(3, 0)));
		btnPane.add(reset);

		mainPanel = new SMYLDPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(pane1);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		mainPanel.add(pane2);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		mainPanel.add(btnPane);
		//*/
	}

	/**
	 * Trace the actions perfomed in this dialog
	 * 
	 * @param e
	 *            the action event
	 */
	public void actionPerformed(ActionEvent e) {
		checkAttempts(e);

		if (e.getSource() == ok) {
			actionPerformed_ok();
		} else if (e.getSource() == cancel) {
			actionPerformed_cancel();
		} else if (e.getSource() == reset) {
			actionPerformed_reset();
		}
	}

	protected void actionPerformed_ok() {
		// To be overridden in the child classes
	}

	protected void actionPerformed_reset() {
		userField.getSMYLDTextField().reset();
		pwdField.getSMYLDPasswordField().reset();
	}

	protected void actionPerformed_cancel() {
		System.exit(1);
	}

	private void closeWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				// --Do something here to exit the application if the user
				// closes this window
				System.exit(-1);
			}
		});
	}

	private void checkAttempts(ActionEvent e) {
		attempts++;
		if (attempts <= 3) {
			return;
		}

		e.setSource(cancel); // If attempts count has been exceeded exit the
								// application
		JOptionPane.showMessageDialog(this,
				"Maximum number of attempts exceeded", "Failure",
				JOptionPane.ERROR_MESSAGE);

	}
}
