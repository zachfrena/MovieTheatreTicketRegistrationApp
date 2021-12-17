/**
 * The MainMenuView class sets the user-facing GUI for the main menu
 */
package view;


import Model.User;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainMenuView extends JPanel {
	private JButton movieButton;
	private JButton ticketButton;
	private JTextField amount;
	private JButton addButton;
	private JButton unregisterButton;
	private JTextField balance;
	private JLabel greeting;
	private JPanel centerPanel;
	private User user;
	private boolean isRegistered;
	
	public MainMenuView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		
		JLabel title = new JLabel("Main Menu");
		title.setFont(new Font("Arial", Font.BOLD, 24));
		greeting = new JLabel("Signed in as: " + getUserName());
		greeting.setFont(new Font("Arial", Font.BOLD, 16));
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(title);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel greetingPanel = new JPanel();
		greetingPanel.add(greeting);
		greeting.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(titlePanel, BorderLayout.NORTH);
		topPanel.add(greetingPanel, BorderLayout.CENTER);
		topPanel.add(Box.createRigidArea(new Dimension(0,50)), BorderLayout.SOUTH);
		
		movieButton = new JButton("Book Ticket");
		movieButton.setPreferredSize(new Dimension(100,100));
		
		ticketButton = new JButton("View Tickets");
		ticketButton.setPreferredSize(new Dimension(100,100));
		
		centerPanel = new JPanel(new BorderLayout(0, 40));
		centerPanel.add(movieButton, BorderLayout.NORTH);
		centerPanel.add(ticketButton, BorderLayout.CENTER);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 40)), BorderLayout.SOUTH);
		
		amount = new JTextField(8);
		addButton = new JButton("Add");
		unregisterButton = new JButton("Unregister");

		
		JPanel botLeft = new JPanel(new FlowLayout());
		botLeft.setPreferredSize(new Dimension(160,80));
		botLeft.add(amount);
		botLeft.add(addButton);
		botLeft.add(unregisterButton);
		
		JLabel bLabel = new JLabel("Account Balance: ");
		balance = new JTextField(7);
		balance.setEditable(false);
		
		JPanel botRight = new JPanel(new FlowLayout());
		botRight.add(bLabel);
		botRight.add(balance);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(botLeft, BorderLayout.WEST);
		bottomPanel.add(botRight, BorderLayout.EAST);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		add(Box.createRigidArea(new Dimension(150,0)), BorderLayout.WEST);
		add(Box.createRigidArea(new Dimension(150,0)), BorderLayout.EAST);
	}

	public String getUserName(){
		if (user != null){
			return user.getUsername();
		} else {
			return "User";
		}
	}

	public void chargeCreditCard(int amount){
		JOptionPane.showMessageDialog(centerPanel, "$" + amount + " was charged to credit card " + user.getCreditNum());
	}
	
	public void limitedFunds() {
		JOptionPane.showMessageDialog(centerPanel, "You need at least $20 to purchase a ticket. Please add more money to your account.");
	}

	public void setGreeting(){
		greeting.setText("Signed in as "+ (user.isRegistered() ? "Registered User: " : "User: " ) + getUserName());
	}

	public void setUser(User user){
		this.user = user;
	}

	public JButton getMovieButton() {
		return movieButton;
	}
	
	public JButton getTicketButton() {
		return ticketButton;
	}
	
	public JButton getAddButton() {
		return addButton;
	}
	
	public JButton getUnregisterButton() {
		return unregisterButton;
	}
	
	public int getAmount() {
		int n;
		try {
			n = Integer.parseInt(amount.getText().strip());
		} catch (NumberFormatException e) {
			n = 0;
		}
		return n;
	}
	public void setAmount(String str){
		amount.setText(str);
	}
	
	public void setBalance(int balance) {
		String balanceStr = String.valueOf(balance);
		this.balance.setText(balanceStr);
	}
	
	public void setIsRegistered(boolean status) {
		isRegistered = status;
		if (!isRegistered)
			unregisterButton.setEnabled(false);
	}
	
	public void addMenuListener(ActionListener listener) {
		movieButton.addActionListener(listener);
		ticketButton.addActionListener(listener);
	}
	
	public void addActionListener(ActionListener listener) {
		addButton.addActionListener(listener);
		unregisterButton.addActionListener(listener);
		movieButton.addActionListener(listener);
	}
	

}
