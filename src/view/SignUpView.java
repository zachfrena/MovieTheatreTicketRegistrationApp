package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

/**
 * The SignUpView class defines the layout of the SignUpView where users who are not
 * already registered into the database can sign up and enter their relevant details.
 * It has fields to take their first name, last name, email, username, and credit card number,
 * as well as a check box that allows the user to indicate if they want to become a Registered
 * User.
 */

public class SignUpView extends JPanel{
	private JTextField fName;
	private JTextField lName;
	private JTextField email;
	private JTextField userName;
	private JTextField creditNum;
	private JCheckBox registered;
	private boolean regChoice;
	private JButton confirmButton;
	
	public SignUpView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(new Insets(15,50,15,50)));
		
		JLabel fLabel = new JLabel("First Name: ");
		fName = new JTextField(10);
		
		JPanel firstName = new JPanel();
		firstName.setLayout(new BoxLayout(firstName, BoxLayout.X_AXIS));
		firstName.add(fLabel);
		firstName.add(Box.createRigidArea(new Dimension(5,0)));
		firstName.add(fName);
		
		
		JLabel lLabel = new JLabel("Last Name: ");
		lName = new JTextField(10);
		
		JPanel lastName = new JPanel();
		lastName.setLayout(new BoxLayout(lastName, BoxLayout.X_AXIS));
		lastName.add(lLabel);
		lastName.add(Box.createRigidArea(new Dimension(5,0)));
		lastName.add(lName);
		
		
		JLabel eLabel = new JLabel("Email: ");
		email = new JTextField(10);
		
		JPanel ePanel = new JPanel();
		ePanel.setLayout(new BoxLayout(ePanel, BoxLayout.X_AXIS));
		ePanel.add(eLabel);
		ePanel.add(Box.createRigidArea(new Dimension(5,0)));
		ePanel.add(email);
		
		
		JLabel uLabel = new JLabel("Username: ");
		userName = new JTextField(10);
		userName.setEditable(false);
		
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
		userPanel.add(uLabel);
		userPanel.add(Box.createRigidArea(new Dimension(5,0)));
		userPanel.add(userName);
		
		
		JLabel cLabel = new JLabel("Credit Card Number: ");
		creditNum = new JTextField(10);
		
		JPanel creditPanel = new JPanel();
		creditPanel.setLayout(new BoxLayout(creditPanel, BoxLayout.X_AXIS));
		creditPanel.add(cLabel);
		creditPanel.add(Box.createRigidArea(new Dimension(5,0)));
		creditPanel.add(creditNum);
		
		
		JLabel rLabel = new JLabel("<html>Would you like to become a Registered User?"
				+ "<br>" + "Registered Users get early access to movies"
						+ "<br>"
						+ " and can freely "
						+ "cancel movies with no fees,"
						+ "<br>" + " for the low cost of $20 a year." + "<html>");
		registered = new JCheckBox("Register");
		registered.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				regChoice = e.getStateChange()==1 ? true : false;
			}
		});
		
		JPanel regPanel = new JPanel();
		regPanel.setLayout(new BoxLayout(regPanel, BoxLayout.X_AXIS));
		regPanel.add(rLabel);
		regPanel.add(Box.createRigidArea(new Dimension(5,0)));
		regPanel.add(registered);
		
		
		confirmButton = new JButton("Confirm");
		
		JLabel title = new JLabel("Sign Up Form");
		title.setFont(new Font("Arial", Font.BOLD, 20));
		
		add(title);
		add(Box.createRigidArea(new Dimension(0,35)));
		add(firstName);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(lastName);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(ePanel);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(userPanel);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(creditPanel);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(regPanel);
		add(Box.createRigidArea(new Dimension(0,25)));
		add(confirmButton);
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		confirmButton.setAlignmentX(CENTER_ALIGNMENT);
		firstName.setAlignmentX(CENTER_ALIGNMENT);
		lastName.setAlignmentX(CENTER_ALIGNMENT);
		ePanel.setAlignmentX(CENTER_ALIGNMENT);
		userPanel.setAlignmentX(CENTER_ALIGNMENT);
		creditPanel.setAlignmentX(CENTER_ALIGNMENT);
		regPanel.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}
	
	public String getFName() {
		return fName.getText().strip();
	}
	
	public String getLName() {
		return lName.getText().strip();
	}
	
	public String getEmail() {
		return email.getText().strip();
	}
	
	public void setUserName(String userName) {
		this.userName.setText(userName);
	}

	public String getUserName() { return userName.getText().strip();}
	
	public String getCreditNum() {
		return creditNum.getText().strip();
	}
	
	public boolean getRegChoice() {
		return regChoice;
	}
	
	public void addMenuListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
	}
	
	public void addActionListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
	}
}
