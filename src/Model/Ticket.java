/**
 * The Ticket class represents a ticket object that ties the user, the showing, and the seat together.
 */
package Model;


public class Ticket {

    private Showing showing; //pass integer (showingID) into DB
    private User user; //pass String (username) into DB
    private int seatNumber;

    public Ticket(Showing showing, User user, int seat){
        this.showing=showing;
        this.user=user;
        this.seatNumber = seat;
    }

    public void emailTicket(){
        //non-functional method-- assume that the user would be emailed their ticket/ receipt here.
    }


    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
       return showing.getMovie().getTitle() + " || " + showing.getTheatre() + " || " + showing.getDate() + " || " + showing.getTime() + " || Seat: " + (seatNumber < 10 ? "0" + seatNumber : seatNumber);
    }
}

