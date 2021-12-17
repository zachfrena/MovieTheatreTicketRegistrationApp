/**
 * The SeatView is a class that is used to display the seats of a showing to the application user
 */

package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Model.Seat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The SeatView class defines the layout of the Seat view where users can graphically
 * choose what seat they want to reserve for a movie.
 */

public class SeatView extends JPanel{
	private ArrayList<JButton> seatButtons;
	private int selectedSeat;
	private JPanel seatPanel;
	private SeatButtonListener seatButtonListener;
	private JButton cancelButton;
	private JButton confirmButton;
	
	public SeatView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		seatButtonListener = new SeatButtonListener();
		
		
		JLabel title = new JLabel ("Choose a seat to book");
		title.setFont(new Font("Arial", Font.BOLD, 22));
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(title);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		
		JLabel aLabel = new JLabel("Available: ");
		JButton available = new JButton();
		available.setPreferredSize(new Dimension(20,20));
		
		
		JLabel sLabel = new JLabel("Selected: ");
		JButton selected = new JButton();
		selected.setPreferredSize(new Dimension(20,20));
		selected.setEnabled(false);
		selected.setBackground(Color.green);
		selected.setBorderPainted(false);
		
		JLabel rLabel = new JLabel("Reserved: ");
		JButton reserved = new JButton();
		reserved.setPreferredSize(new Dimension(20,20));
		reserved.setEnabled(false);
		reserved.setBackground(Color.red);
		reserved.setBorderPainted(false);
		
		JPanel legendPanel = new JPanel(new FlowLayout());
		legendPanel.add(aLabel);
		legendPanel.add(available);
		legendPanel.add(Box.createRigidArea(new Dimension(20,0)));
		legendPanel.add(sLabel);
		legendPanel.add(selected);
		legendPanel.add(Box.createRigidArea(new Dimension(20,0)));
		legendPanel.add(rLabel);
		legendPanel.add(reserved);
		
		JLabel screen = new JLabel("Screen", JLabel.CENTER);
		screen.setOpaque(true);
		screen.setBackground(Color.DARK_GRAY);
		screen.setPreferredSize(new Dimension(600, 15));
		screen.setForeground(Color.white);
		
		JPanel screenPanel = new JPanel();
		screenPanel.add(screen);
		screen.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(titlePanel, BorderLayout.NORTH);
		topPanel.add(legendPanel, BorderLayout.CENTER);
		topPanel.add(screenPanel, BorderLayout.SOUTH);
		
		seatPanel = new JPanel(new GridLayout(0, 10, 0,10));

		
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(seatPanel, BorderLayout.CENTER);
		centerPanel.add(Box.createRigidArea(new Dimension(0,80)), BorderLayout.NORTH);
		centerPanel.add(Box.createRigidArea(new Dimension(0,100)), BorderLayout.SOUTH);
		
		cancelButton = new JButton("Cancel");
		confirmButton = new JButton("Confirm");
		confirmButton.setEnabled(false);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(cancelButton, BorderLayout.WEST);
		bottomPanel.add(confirmButton, BorderLayout.EAST);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public void disableConfirm(){
		confirmButton.setEnabled(false);
	}
	
	public void populateSeats(ArrayList<Seat> seats) { //Should be called by button on movie screen
		selectedSeat = -1;
		seatButtons = new ArrayList<JButton>();
		seatPanel.removeAll();
		for (Seat seat : seats) {
			JButton seatButton = new JButton("" + seat.getSeatNumber());
			seatButton.setFont(new Font("Arial", Font.BOLD, 16));
			if(seat.isTaken()) {
				seatButton.setEnabled(false);
				seatButton.setBackground(Color.red);
				seatButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			}
			seatButton.addActionListener(seatButtonListener);
			seatButtons.add(seatButton);
			seatPanel.add(seatButton);
		}
	}

	
	public JButton getCancelButton() {
		return cancelButton;
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}
	
	public int getSelectedSeat() {
		return selectedSeat;
	}

	public void resetSelectedSeat(){
		selectedSeat = -1;
	}
	
	public void addMenuListener(ActionListener listener) {
		cancelButton.addActionListener(listener);
		confirmButton.addActionListener(listener);
	}
	
	public void addActionListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
		cancelButton.addActionListener(listener);
	}
	
	class SeatButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton seatButton = (JButton)e.getSource();
			int i = seatButtons.indexOf(seatButton);
			if(i == selectedSeat) {
				seatButton.setBackground(confirmButton.getBackground());
				seatButton.setBorderPainted(true);
				confirmButton.setEnabled(false);
				selectedSeat = -1;
			} else {
				if (selectedSeat > -1) {
					JButton oldSelection = seatButtons.get(selectedSeat);
					oldSelection.setBackground(confirmButton.getBackground());
					oldSelection.setBorderPainted(true);
				} else confirmButton.setEnabled(true);
				
				seatButton.setBackground(Color.green);
				seatButton.setBorderPainted(false);
				selectedSeat = i;
			}
		}
	}
}
