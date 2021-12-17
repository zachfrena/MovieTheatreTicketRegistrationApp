/**
 * The DBController class provides functionality for the application to read and write from the MySQL DataBase.
 * Updating, setting, or retrieving data to be used in combination with user input is performed in this class.
 */
package Controller;

import Model.*;
import view.MovieView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;


public class MovieController {

    private ArrayList<Showing> showings;
    private ArrayList<Movies> movies;
    private MovieView movieView;
    private Showing showing;
    private DBController databaseController;
    private SeatController seatController;
    private User user;
    private ArrayList<Showing> availableShowings;
    private ExistingUsersList existingUsersList;

    public MovieController(DBController databaseController) {
        this.databaseController = databaseController;
        existingUsersList = new ExistingUsersList();
        showings = loadShowings();
        reserveSeats();


    }

    public void reserveSeats(){
        ResultSet rs = databaseController.readAllTickets();

        try {
            while (rs.next()) {
                int showingId = rs.getInt("ShowingID");
                String userName = rs.getString("Username");
                int seat = rs.getInt("Seat");
                for (int i = 0; i < showings.size(); i++){
                    if (showings.get(i).getShowingID() == showingId){
                        showings.get(i).getAllSeats().get(seat).setTaken();
                        for (int j = 0; j < existingUsersList.getExistingUsers().size(); j++){
                            if (existingUsersList.getExistingUsers().get(j).getUsername().equals(userName)){
                                if(existingUsersList.getExistingUsers().get(j).isRegistered()){
                                    showings.get(i).incrementRegisteredSeats();
                                }
                            }
                        }

                    }
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setMovies(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, day);
        long todayMillis = cal.getTimeInMillis();
        availableShowings = new ArrayList<Showing>();
        availableShowings.clear();
        for (int i = 0; i < showings.size(); i++){
            if ((showings.get(i).getDate().getTime() - todayMillis) < 1209600000) {
                Showing showing = showings.get(i);
                    availableShowings.add(showing);

            } else if ((showings.get(i).getDate().getTime() - todayMillis) >= 1209600000){
                if ((showings.get(i).getDate().getTime() - todayMillis) > 1814400000){
                    continue;
                } else{
                    if (user.isRegistered() && showings.get(i).getRegisteredSeats() < 2){
                        Showing showing = showings.get(i);
                        availableShowings.add(showing);
                    } else {
                        continue;
                    }
                }
            }
        }

        ArrayList<Movies> availableMovies = new ArrayList<Movies>();
        for (int i = 0; i < availableShowings.size(); i++){
            if(availableMovies.indexOf(availableShowings.get(i).getMovie()) == -1) {
                availableMovies.add(availableShowings.get(i).getMovie());
            }
        }
        movieView.addMovies(availableMovies);
    }

    public void setShowings(ArrayList<Showing> showings){
        this.showings = showings;
    }

    public ArrayList<Showing> loadShowings(){
        readAllMovies();
        return readAllShowings();
    }

    public ArrayList<Showing> getShowings(){
        return showings;
    }

    public ArrayList<Showing> readAllShowings() {
        ArrayList<Showing> showingList = new ArrayList<Showing>();
        ResultSet res = databaseController.readAllTables("SHOWING");

        try {
            while(res.next()) {
                int showingId = res.getInt("ShowingID");
                int movieId  = res.getInt("MovieID");
                Date theDate = res.getDate("TheDate");
                LocalTime showingTime = convertLocalTime(res.getTime("ShowingTime"));
                String theatreName = res.getString("Theatre");
                movies = readAllMovies();
                Movies theMovie = movies.get(movieId - 1);
                Showing theShowing = new Showing(showingId, theMovie, theDate, showingTime, theatreName);
                showingList.add(theShowing);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return showingList;
    }

    public ArrayList<Movies> readAllMovies() {
        ResultSet res = databaseController.readAllTables("Movie");
        movies = new ArrayList<Movies>();

        try {
            while(res.next()) {
                int movieId = res.getInt("MovieID");
                String title = res.getString("Title");

                Movies theMovie = new Movies(movieId, title);
                movies.add(theMovie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }



    public LocalTime convertLocalTime(Time time) {
        if(time != null) {
            return time.toLocalTime();
        }
        return null;
    }

    public void setMovieView(MovieView movieView){
        this.movieView = movieView;
    }

    public void setSeatController(SeatController seatController){
        this.seatController = seatController;
    }

    public void setTheatres(ArrayList<String> theatres){
        movieView.addTheatres(theatres);
    }

    public void setTimes(ArrayList<String> times){
        movieView.addTimes(times);
    }

    public void setShowing(String movie, String theatre, String time){
        for (int i = 0; i < showings.size(); i++){
            if (showings.get(i).getMovie().getTitle().equals(movie) && showings.get(i).getTheatre().equals(theatre) && showings.get(i).getTime().toString().equals(time)){
                showing = showings.get(i);
                System.out.println("FOUND SHOWING");
                break;
            }
        }
        System.out.println(showing.toString());
        seatController.setShowing(showing);
        seatController.setShowings(showings);
    }


    public ArrayList<String> getTheatres(String movie){
        ArrayList<String> theatres = new ArrayList<String>();
        for (int i = 0; i < availableShowings.size(); i++){
            if (availableShowings.get(i).getMovie().getTitle().equals(movie)){
            	String theatre = availableShowings.get(i).getTheatre();
            	if (theatres.indexOf(theatre) == -1)
            		theatres.add(theatre);
            }
        }
        Collections.sort(theatres);
        return theatres;
    }

    public ArrayList<String> getTimes(String movie, String theatre){
        ArrayList<String> times = new ArrayList<String>();
        for (int i = 0; i < availableShowings.size(); i++){
            if (availableShowings.get(i).getMovie().getTitle().equals(movie) && availableShowings.get(i).getTheatre().equals(theatre)){
                times.add(availableShowings.get(i).getDate().toString() + " " + availableShowings.get(i).getTime().toString());
            }
        }
        Collections.sort(times);
        return times;
    }


    public void setActionListener() {
        movieView.addActionListener(new MovieListener());
    }

    public class MovieListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == movieView.getMovieButton()){
                String movie = movieView.getSelectedMovie();
                setTheatres(getTheatres(movie));
                movieView.enableTheatres();
            } else if (e.getSource() == movieView.getTheatreButton()){
                String movie = movieView.getSelectedMovie();
                String theatre = movieView.getSelectedTheatre();
                setTimes(getTimes(movie, theatre));
                movieView.enableTimes();
            } else if (e.getSource() == movieView.getMovieSelector()){
                movieView.disableTheatres();
                movieView.disableTimes();
            }else if (e.getSource() == movieView.getTheatreSelector()){
                movieView.disableTimes();
            }else if (e.getSource() == movieView.getConfirmButton()){
                String movie = movieView.getSelectedMovie();
                String theatre = movieView.getSelectedTheatre();
                String dateTime = movieView.getSelectedTime();
                String[] parts = dateTime.split(" ");
                String time = parts[1];
                setShowing(movie, theatre, time);
                seatController.populateGUI();
            }
        }

    }


}
