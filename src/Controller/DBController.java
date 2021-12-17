/**
 * The DBController class provides functionality for the application to read and write from the MySQL DataBase.
 * Updating, setting, or retrieving data to be used in combination with user input is performed in this class.
 */
package Controller;

import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBController implements DBLoginDetails {
    private Connection con;
    private ResultSet res;
    protected PreparedStatement theStatement;
    private ExistingUsersList existingUsersList;


    public DBController() {
        establishConnection();
    }

    // establishes connection to database
    public void establishConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            con = DriverManager.getConnection(theURL, theUserName, thePassword);
        } catch (SQLException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }
    // closes the connection
    public void close() {
        try {
            con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    // selects all for the given table parameter
    public ResultSet readAllTables(String theTable) {
        try {
            String query = "SELECT * FROM " + theTable + ";";
            theStatement = con.prepareStatement(query);
            res = theStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    // selects all movies where based on given movieId parameter
    public ResultSet readMovies(int movieId) {
        try {
            String query = "SELECT * FROM MOVIE WHERE MovieID = " + movieId + ";";
            theStatement = con.prepareStatement(query);
            res = theStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ResultSet readAllTickets(){
        try {
            String query = "SELECT ShowingID, Username, Seat FROM TICKET";
            Statement stmt = con.createStatement();
            res = stmt.executeQuery(query);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }
    // selects all tickets based on given username parameter
    public ResultSet readTickets(User user) {
        String username = user.getUsername();
        try {
            String query = "SELECT * FROM TICKET WHERE Username = ?";
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setString(1, username);
            res = pStat.executeQuery();

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return res;

    }
    // saves/inserts given user to the database
    public ResultSet saveUserToDB(User user) {
        String username = user.getUsername();
        String fname = user.getFirstName();
        String lname = user.getlName();
        String theEmail = user.getEmail();
        String theCreditCard = user.getCreditNum();
        boolean isRegistered = user.isRegistered();
        int accountBalance = user.getAccountBalance();

        try {

            String query = "INSERT INTO USERS (Username, FName, LName, Email, IsRegistered, CcNum, AccountBalance) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setString(1, username);
            pStat.setString(2, fname);
            pStat.setString(3, lname);
            pStat.setString(4, theEmail);
            pStat.setBoolean(5, isRegistered);
            pStat.setString(6, theCreditCard);
            pStat.setInt(7, accountBalance);
            int rowCount = pStat.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    // saves/inserts given ticket object to the database
    public void saveTicketToDB(Ticket ticket) {
        int showingId = ticket.getShowing().getShowingID();
        String username = ticket.getUser().getUsername();
        int seatNumber = ticket.getSeatNumber();

        try {
            String query = "INSERT INTO TICKET (ShowingID, Username, Seat) VALUES (?,?,?)";
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setInt(1, showingId);
            pStat.setString(2, username);
            pStat.setInt(3, seatNumber);
            pStat.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }
    // deletes the ticket from the database
    public void refundTicket(Ticket ticket){
        int showingId = ticket.getShowing().getShowingID();
        int seatNumber = ticket.getSeatNumber();

        try{
            String query = "DELETE FROM TICKET WHERE ShowingID = ? AND Seat = ?";
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setInt(1, showingId);
            pStat.setInt(2, seatNumber);
            pStat.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    // updates user table and sets registered user to false
    public void setToNonRegistered(User user) {
        try {
            String query = "UPDATE USERS SET IsRegistered=False WHERE Username = ?" ;
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setString(1,user.getUsername());
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // updates account balance for given user in user table
    public void updateDataBaseBalance(User user) {
        int accountBalance = user.getAccountBalance();
        try {
            String query = "UPDATE USERS SET AccountBalance=? WHERE Username = ?" ;
            PreparedStatement pStat = con.prepareStatement(query);
            pStat.setInt(1, accountBalance);
            pStat.setString(2,user.getUsername());
            pStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
