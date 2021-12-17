/**
 * The TicketView class represents a GUI page that shows the ticket objects that a user has previously booked,
 * and provides information about the account balance.
 */
package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.Ticket;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TicketView extends JPanel{
	private JComboBox ticketSelector;
	private JButton refundButton;
	private JButton backButton;
	private JTextField balance;
	private JPanel centerPanel;
	
	public TicketView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		
		JLabel title = new JLabel("Tickets");
		title.setFont(new Font("Arial", Font.BOLD, 24));
		
		JPanel topPanel = new JPanel();
		topPanel.add(title);
		title.setAlignmentX(CENTER_ALIGNMENT);
		

		ticketSelector = new JComboBox();
		ticketSelector.setPreferredSize(new Dimension(500,30));
		
		refundButton = new JButton("Refund");
		refundButton.setPreferredSize(new Dimension(80,30));
		
		JPanel innerPanel = new JPanel(new FlowLayout());
		innerPanel.add(ticketSelector);
		innerPanel.add(Box.createRigidArea(new Dimension(25,0)));
		innerPanel.add(refundButton);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(Box.createRigidArea(new Dimension(0,180)));
		centerPanel.add(innerPanel);
		
		backButton = new JButton("Back");
		
		JLabel bLabel = new JLabel("Account Balance: ");
		balance = new JTextField(7);
		balance.setEditable(false);
		
		JPanel botRight = new JPanel(new FlowLayout());
		botRight.add(bLabel);
		botRight.add(balance);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(backButton, BorderLayout.WEST);
		bottomPanel.add(botRight, BorderLayout.EAST);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public void addTickets(ArrayList<Ticket> tickets){
		ticketSelector.removeAllItems();
		for (int i =0; i < tickets.size(); i++){
			ticketSelector.addItem(tickets.get(i));
		}
	}

	public void cantRefund(){
		JOptionPane.showMessageDialog(centerPanel, "Can't refund within 72 hours of showing.");
	}

	public Ticket getSelectedTicket(){
		return (Ticket) ticketSelector.getSelectedItem();
	}

	
	public JButton getRefundButton() {
		return refundButton;
	}
	
	public JButton getBackButton() {
		return backButton;
	}
	
	public void addMenuListener(ActionListener listener) {
		backButton.addActionListener(listener);
	}
	
	public void addActionListener(ActionListener listener) {
		refundButton.addActionListener(listener);
	}

	public void setBalance(int balance) {
		String balanceStr = String.valueOf(balance);
		this.balance.setText(balanceStr);
	}
	
	public String getTicket() {
		return (String) ticketSelector.getSelectedItem();
	}
}
