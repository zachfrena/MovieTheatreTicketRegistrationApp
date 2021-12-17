/**
 * The Showing Class represents the implementation of a user booking a movie and selecting a seat.
 * This class contains an array of seats that will be updated everytime a movie is booked or refunded so that future
 * users will have an updated selection of seats to view/select.
 */
package Model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Showing {

    private int showingID;
    private Date showingDate;
    private LocalTime showingTime;
    private String theatre;
    private Movies movie;
    private ArrayList<Seat> allSeats;
    private int registeredSeats;

    public Showing(int showingID, Movies movie, Date showingDate, LocalTime showingTime, String theatre) {
        this.showingID = showingID;
        this.showingDate = showingDate;
        this.showingTime = showingTime;
        this.theatre = theatre;
        this.movie = movie;
        allSeats = new ArrayList<Seat>();
        makeSeats();
        registeredSeats = 0; //this variable tracks the number of seats booked by a registered user during the "privileged period" (i.e. between 2-3 weeks prior to the showing date)
    }

    public void makeSeats() { //20 seats per showing is generated
        for(int i = 0; i < 20; i++) {
            allSeats.add(new Seat(i));
        }
    }

    public int getRegisteredSeats(){
        return registeredSeats;
    }

    // increments registered seats by 1
    public void incrementRegisteredSeats(){
        registeredSeats += 1;
    }
    
    public void decrementRegisteredSeats() {
    	registeredSeats -=1;
    }

    public Date getDate(){
        return showingDate;
    }

    public Movies getMovie() {
        return movie;
    }

    public void updateSeats(int seatNumber) { //takes in a seat number
        allSeats.get(seatNumber).setTaken(); //indexes at 0, and sets the seat in array to "isTaken = true"
    }

    public ArrayList<Seat> getAllSeats(){
        return allSeats;
    }

    public int getShowingID() {
        return showingID;
    }

    public int getMovieId() {
        return movie.getMovieId();
    }

    public LocalTime getTime() {
        return showingTime;
    }

    public String getTheatre(){
        return theatre;
    }

    @Override
    public String toString() {
        return "\nShowing: " + movie.getTitle() + "\nDate: " + showingDate + "\nTime: " + showingTime;
    }

}
