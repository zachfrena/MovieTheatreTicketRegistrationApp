
package Controller;

import Model.Movies;
import Model.Showing;
import Model.Ticket;
import Model.User;
import view.TicketView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;

/**
 * The TicketController class provides functionality for the application to read user-inputted data and generate a new
 * Ticket object.
 */
public class TicketController {

    private TicketView ticketView;
    private User user;
    private DBController databaseController;
    private MovieController movieController;
    private ArrayList<Ticket> tickets;
    private ArrayList<Showing> showings;
    private long todayMillis;

    public TicketController(DBController databaseController){ //constructor
        this.databaseController = databaseController;
    }

    public void initializeTickets(){
        setShowings();
        loadTickets();
    }

    public void setMovieController(MovieController movieController){
        this.movieController = movieController;
    }

    public void setShowings(){
        this.showings = movieController.getShowings();
    }


    public void loadTickets(){
        ResultSet rs = databaseController.readTickets(user);
        tickets = new ArrayList<Ticket>();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, day);
        todayMillis = cal.getTimeInMillis();
        try {
            while (rs.next()) {
                int showingId = rs.getInt("ShowingID");
                int seat = rs.getInt("Seat");
                Showing showing = null;
                for (int i = 0; i < showings.size(); i++){
                    if (showings.get(i).getShowingID() == showingId) {
                        showing = showings.get(i);
                        break;
                    }
                }
                Ticket ticket = new Ticket(showing, user, seat);
                tickets.add(ticket);

            }
            Collections.sort(tickets, new Comparator<Ticket>() {
                @Override
                public int compare(Ticket o1, Ticket o2) {
                    if (o1.toString().compareTo(o2.toString()) > 0)
                        return 1;
                    else if (o1.toString().compareTo(o2.toString()) < 0)
                        return -1;
                    else
                        return 0;
                }
            });
            ticketView.addTickets(tickets);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void refundTicket(Ticket ticket){

        databaseController.refundTicket(ticket);

        if (user.isRegistered()){
            user.updateAccountBalance(20);
            databaseController.updateDataBaseBalance(user);
        } else {
            user.updateAccountBalance(17);
            databaseController.updateDataBaseBalance(user);
        }
        ticketView.setBalance(user.getAccountBalance());
        tickets.remove(ticket);
        ticketView.addTickets(tickets);
        int index = showings.indexOf(ticket.getShowing());
        showings.get(index).getAllSeats().get(ticket.getSeatNumber()).setSeatNotTaken();
        if (user.isRegistered())
        	showings.get(index).decrementRegisteredSeats();
        movieController.setShowings(showings);
    }

    public User getUser(){
        return user;
    }

    public void addTicket(Ticket ticket){
        tickets.add(ticket);
    }

    public void setTickets(ArrayList<Ticket> tickets){
        ticketView.addTickets(tickets);
    }

    public void displayAccountBalance(){
        ticketView.setBalance(user.getAccountBalance());
    }

    public void setTicketView(TicketView ticketView){
        this.ticketView = ticketView;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setActionListener(){
        ticketView.addActionListener(new TicketListener());
    }

    class TicketListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ticketView.getRefundButton()){
                Ticket ticket = ticketView.getSelectedTicket();
                if (ticket.getShowing().getDate().getTime() - todayMillis > 259200000)
                    refundTicket(ticket);
                else{
                    ticketView.cantRefund();
                }

            }
        }
    }
}
