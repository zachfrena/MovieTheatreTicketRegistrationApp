/**
 * The Movies class represents a single movie in the application. There is also comparison methods built in that
 * allows 2 movies to be compared to eachother.
 */
package Model;

import java.util.ArrayList;

public class Movies {

    private ArrayList<Showing> showingList;
    private int movieId;
    private String title;


    public Movies(int movieId, String title) {
        showingList = new ArrayList<Showing>();
        this.movieId = movieId;
        this.title = title;

    }
    // adds showing
    public void addShowing(Showing show) {
        showingList.add(show);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    // returns list of showings for the movie
    public ArrayList<Showing> getShowings() {
        return showingList;
    }

    @Override
    public String toString() {
        return "Movie: " + getTitle();
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            System.out.println("statement 1 o == this");
            return true;
        }

        if(!(o instanceof Movies)){
            System.out.println("statement 2 o not instance of this");
            return false;
        }

        Movies m = (Movies) o;
        return getTitle().equals(m.getTitle());
    }


}
