/**
 * The Seat class represents a seat object that can be reserved by a user.
 */
package Model;

public class Seat {

    private int seatNumber;
    private boolean isTaken = false;

    public Seat(int seatNumber) {
        setSeatNumber(seatNumber);
    }

    // boolean returns true if seat is taken
    public boolean isTaken() {
        return isTaken;
    }

    //sets seat taken to true
    public void setTaken() {
        isTaken = true;
    }


    public void setSeatNotTaken() {
        isTaken = false;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    // sets seat number
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Seat number: " + getSeatNumber();
    }
}
