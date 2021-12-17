package view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import Model.Movies;

/**
 * The MovieView class defines the layout of the Movie view where users can pick from a selection
 * of movies, choose from available theatres, and then choose from available times to pull up the
 * SeatView of the movie they are intending to purchase a ticket for.
 */


public class MovieView extends JPanel{
	private JComboBox movieSelector;
	private JComboBox theatreSelector;
	private JComboBox timeSelector;
	private JButton movieButton;
	private JButton theatreButton;
	private JButton backButton;
	private JButton confirmButton;

	
	public MovieView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		
		JLabel title = new JLabel("Choose a Movie, Theatre, and Time");
		title.setFont(new Font("Arial", Font.BOLD, 24));
		
		
		JPanel topPanel = new JPanel();
		topPanel.add(Box.createRigidArea(new Dimension(0, 160)));
		topPanel.add(title);
		title.setAlignmentX(CENTER_ALIGNMENT);
		

		
		movieSelector = new JComboBox();
		movieSelector.setPreferredSize(new Dimension(300,25));
		movieButton = new JButton("Select");
		
		JPanel moviePanel = new JPanel(new FlowLayout());
		moviePanel.add(movieSelector);
		moviePanel.add(Box.createRigidArea(new Dimension(10,0)));
		moviePanel.add(movieButton);
		
		theatreSelector = new JComboBox();
		theatreSelector.setPreferredSize(new Dimension(300,25));
		theatreSelector.setEnabled(false);
		theatreButton = new JButton("Select");
		theatreButton.setEnabled(false);
		
		JPanel theatrePanel = new JPanel(new FlowLayout());
		theatrePanel.add(theatreSelector);
		theatrePanel.add(Box.createRigidArea(new Dimension(10,0)));
		theatrePanel.add(theatreButton);
		
		
		timeSelector = new JComboBox();
		timeSelector.setPreferredSize(new Dimension(300,25));
		timeSelector.setEnabled(false);
		
		JPanel timePanel = new JPanel(new FlowLayout());
		timePanel.add(timeSelector);
		timePanel.add(Box.createRigidArea(new Dimension(10,0)));
		
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(moviePanel);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		centerPanel.add(theatrePanel);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		centerPanel.add(timePanel);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		
		backButton = new JButton("Back");
		confirmButton = new JButton("Confirm");
		confirmButton.setEnabled(false);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(backButton, BorderLayout.WEST);
		bottomPanel.add(confirmButton, BorderLayout.EAST);
		
		add(topPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		
		
	}


	public void addMovies(ArrayList<Movies> movies){
		movieSelector.removeAllItems();
		for (int i = 0; i < movies.size(); i++){
			String title = movies.get(i).getTitle();
			movieSelector.addItem(title);
		}
	}

	public void addTheatres(ArrayList<String> theatres){
		theatreSelector.removeAllItems();
		for (int i = 0; i < theatres.size(); i++){
			theatreSelector.addItem(theatres.get(i));
		}
	}

	public void addTimes(ArrayList<String> times){
		timeSelector.removeAllItems();
		for (int i = 0; i < times.size(); i++){
			timeSelector.addItem(times.get(i));
		}
	}

	public void disableTheatres(){
		theatreSelector.setEnabled(false);
		theatreButton.setEnabled(false);
	}

	public void enableTheatres(){
		theatreSelector.setEnabled(true);
		theatreButton.setEnabled(true);
	}

	public void disableTimes(){
		timeSelector.setEnabled(false);
		confirmButton.setEnabled(false);
	}

	public void enableTimes(){
		timeSelector.setEnabled(true);
		confirmButton.setEnabled(true);
	}
	
	public JButton getBackButton() {
		return backButton;
	}
	
	public JButton getConfirmButton() {
		return confirmButton;
	}

	public JButton getMovieButton() {
		return movieButton;
	}

	public JButton getTheatreButton(){
		return theatreButton;
	}

	public JComboBox getMovieSelector(){
		return movieSelector;
	}

	public JComboBox getTheatreSelector(){
		return theatreSelector;
	}

	public void addMenuListener(ActionListener listener) {
		backButton.addActionListener(listener);
		confirmButton.addActionListener(listener);
	}
	
	public void addActionListener(ActionListener listener) {
		confirmButton.addActionListener(listener);
		movieButton.addActionListener(listener);
		theatreButton.addActionListener(listener);
		movieSelector.addActionListener(listener);
		theatreSelector.addActionListener(listener);
	}
	
	public String getSelectedMovie() {
		return (String)movieSelector.getSelectedItem();
	}
	
	public String getSelectedTheatre() {
		return (String)theatreSelector.getSelectedItem();
	}
	
	public String getSelectedTime() {
		return (String)timeSelector.getSelectedItem();
	}
}
