package view;

import java.awt.CardLayout;
import javax.swing.*;

/**
 * The MainFrame class acts as a central hub and frame for all of the different views,
 * defining the base JFrame and facilitating the switching of views via setting cards.
 */

public class MainFrame extends JFrame implements Views {
	private LoginView loginView;
	private MainMenuView mainMenuView;
	private MovieView movieView;
	private SeatView seatView;
	private SignUpView signUpView;
	private TicketView ticketView;
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	public MainFrame() {
		super("Ticket Reservation App");
		loginView = new LoginView();
		mainMenuView = new MainMenuView();
		movieView = new MovieView();
		seatView = new SeatView();
		signUpView = new SignUpView();
		ticketView = new TicketView();
		cardLayout = new CardLayout();
		
		cardPanel = new JPanel(cardLayout);
		cardPanel.add(loginView, LOGIN_STRING);
		cardPanel.add(mainMenuView, MAIN_MENU_STRING);
		cardPanel.add(movieView, MOVIE_STRING);
		cardPanel.add(seatView, SEAT_STRING);
		cardPanel.add(signUpView, SIGNUP_STRING);
		cardPanel.add(ticketView, TICKET_STRING);
		this.add(cardPanel);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setCard(int i) {
		switch(i) {
		case 0:
			cardLayout.show(cardPanel, LOGIN_STRING);
			break;
		case 1:
			cardLayout.show(cardPanel, MAIN_MENU_STRING);
			break;
		case 2:
			cardLayout.show(cardPanel, SIGNUP_STRING);
			break;
		case 3:
			cardLayout.show(cardPanel, TICKET_STRING);
			break;
		case 4:
			cardLayout.show(cardPanel, MOVIE_STRING);
			break;
		case 5:
			cardLayout.show(cardPanel, SEAT_STRING);
			break;
		}
	}
	
	public LoginView getLoginView() {
		return loginView;
	}
	
	public MainMenuView getMainMenuView() {
		return mainMenuView;
	}
	
	public MovieView getMovieView() {
		return movieView;
	}
	
	public SeatView getSeatView() {
		return seatView;
	}
	
	public SignUpView getSignUpView() {
		return signUpView;
	}
	
	public TicketView getTicketView() {
		return ticketView;
	}
}
