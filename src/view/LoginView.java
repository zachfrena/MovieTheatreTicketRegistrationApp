package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The LoginView class defines the layout of the Login view where users can enter their user name
 * and either get taken to the MainMenuView, if their username is saved in the database, or the
 * SignUpView, if they are a new user.
 */

public class LoginView extends JPanel {
	private JTextField input;
	private JButton loginButton;
	private boolean status;
	
	public LoginView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(new Insets(50,50,0,50)));
		
		JLabel title = new JLabel("Login");
		title.setFont(new Font("Arial", Font.BOLD, 22));
		
		
		JLabel prompt1 = new JLabel("Enter your username to login");
		prompt1.setFont(new Font("Arial", Font.BOLD, 16));
		JLabel prompt2 = new JLabel("New users will be brought to a sign up page");
		prompt2.setFont(new Font("Arial", Font.BOLD, 16));
		
		
		JLabel iLabel = new JLabel("Username: ");
		iLabel.setFont(new Font("Arial", Font.BOLD, 14));
		input = new JTextField(15);
		input.setFont(new Font("Arial", Font.BOLD, 16));
		
		JPanel inputField = new JPanel();
		inputField.setLayout(new FlowLayout());

		inputField.add(iLabel);
		inputField.add(Box.createRigidArea(new Dimension(5,0)));
		inputField.add(input);

		

		loginButton = new JButton("Login");
		loginButton.setPreferredSize(new Dimension(1,50));
		
		add(title);
		add(Box.createRigidArea(new Dimension(0,70)));
		add(prompt1);
		add(prompt2);
		add(Box.createRigidArea(new Dimension(0,70)));
		add(inputField);
		add(loginButton);
		add(Box.createRigidArea(new Dimension(0,50)));
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		prompt1.setAlignmentX(CENTER_ALIGNMENT);
		prompt2.setAlignmentX(CENTER_ALIGNMENT);
		inputField.setAlignmentX(CENTER_ALIGNMENT);
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
	}
	
	public JButton getLoginButton() {
		return loginButton;
	}
	
	public JTextField getInput() {
		return input;
	}
	
	public String getUserName() {
		return input.getText().strip();
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void addMenuListener(ActionListener listener) {
		loginButton.addActionListener(listener);
		input.addActionListener(listener);
	}
	
	public void addActionListener(ActionListener listener) {
		loginButton.addActionListener(listener);
		input.addActionListener(listener);
	}
}
