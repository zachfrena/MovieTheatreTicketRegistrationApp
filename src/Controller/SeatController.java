/**
 * The SeatController class provides functionality for the application to read and write information regarding seats.
 */
package Controller;

import Model.Showing;
import Model.Ticket;
import Model.User;
import view.SeatView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Model.User;

public class SeatController{
	

	private SeatView seatView;
	private Showing showing;
	private MovieController movieController;
	private DBController dataBaseController;
	private ArrayList<Showing> showings;
	private User user;

	public SeatController(DBController dataBaseController){
		this.dataBaseController = dataBaseController;
	}

	public Ticket createTicket(int seatNum){
		return new Ticket(showing, user, seatNum);
	}

	public void setUser(User user){
		this.user = user;
	}

	public void setShowing(Showing showing){
		this.showing = showing;
	}
	public void populateGUI() {
		seatView.populateSeats(showing.getAllSeats());
	}

	public void setMovieController(MovieController movieController){
		this.movieController = movieController;
	}

	public void setSeatView(SeatView seatView) {
		this.seatView = seatView;
	}
	public void setShowings(ArrayList<Showing> showings){
		this.showings = showings;
	}

	public void setActionListener(){
		seatView.addActionListener(new SeatListener());
	}

	public void updateSeat(int seatNum){
		int i = showings.indexOf(showing);
		showings.get(i).updateSeats(seatNum);
		showings.get(i).incrementRegisteredSeats();
	}

	public User getUser(){
		return user;
	}

	public class SeatListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == seatView.getConfirmButton()){
				int seat = seatView.getSelectedSeat();
				updateSeat(seat);
				movieController.setShowings(showings);
				seatView.disableConfirm();
				Ticket ticket = createTicket(seat);
				dataBaseController.saveTicketToDB(ticket);
				user.updateAccountBalance(-20);
				dataBaseController.updateDataBaseBalance(user);

			} else if (e.getSource() == seatView.getCancelButton()){
				seatView.resetSelectedSeat();
			}
		}
	}

}

