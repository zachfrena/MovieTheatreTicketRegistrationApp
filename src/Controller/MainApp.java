/**
 * The MainApp class provides functionality for running the application from the start.
 */
package Controller;

import com.sun.tools.javac.Main;
import view.MainFrame;
import Model.Ticket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    MainFrame frame;
    LoginController loginController;

    DBController databaseController;
    SignUpController signUpController;
    MainMenuController mainMenuController;
    TicketController ticketController;
    MovieController movieController;
    SeatController seatController;

    public MainApp(){
        run();
    }

    public void run() {
        buildDatabase();
        buildMainFrame();
        buildLogin();
        buildSignUp();
        buildMainMenu();
        buildMovie();
        buildSeats();
        buildTicket();


    }

    private void buildMainFrame(){
        frame = new MainFrame();
        frame.setCard(0);
        MenuListener mlistener = new MenuListener();
        frame.getLoginView().addMenuListener(mlistener);
        frame.getSignUpView().addMenuListener(mlistener);
        frame.getMainMenuView().addMenuListener(mlistener);
        frame.getTicketView().addMenuListener(mlistener);
        frame.getMovieView().addMenuListener(mlistener);
        frame.getSeatView().addMenuListener(mlistener);
    }

    private void buildDatabase(){
        databaseController = new DBController();
    }

    private void buildLogin(){
        loginController = new LoginController();
        loginController.setLoginView(frame.getLoginView());
        loginController.setActionListener();
    }

    private void buildSignUp(){
        signUpController = new SignUpController(databaseController);
        signUpController.setSignUpView(frame.getSignUpView());
        signUpController.setActionListener();
    }

    private void buildMainMenu(){
        mainMenuController = new MainMenuController(databaseController);
        mainMenuController.setMainMenuView(frame.getMainMenuView());
        mainMenuController.setActionListener();
    }

    private void buildMovie(){
        movieController = new MovieController(databaseController);
        movieController.setMovieView(frame.getMovieView());
        movieController.setActionListener();
    }

    private void buildSeats(){
        seatController = new SeatController(databaseController);
        seatController.setSeatView(frame.getSeatView());
        seatController.setActionListener();
        movieController.setSeatController(seatController);
        seatController.setMovieController(movieController);
    }

    private void buildTicket(){
        ticketController = new TicketController(databaseController);
        ticketController.setMovieController(movieController);
        ticketController.setTicketView(frame.getTicketView());
        ticketController.setActionListener();
    }



    class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == frame.getLoginView().getLoginButton() || e.getSource() == frame.getLoginView().getInput()) {
                if (frame.getLoginView().getStatus()) {
                    mainMenuController.setUser(loginController.getLoggedInUser());
                    mainMenuController.setIsRegistered(loginController.getLoggedInUser().isRegistered());
                    mainMenuController.displayAccountBalance();
                    mainMenuController.setGreeting();
                    frame.setCard(1);
                } else {
                    frame.getSignUpView().setUserName(frame.getLoginView().getUserName());
                    frame.setCard(2);
                }
            } else if (e.getSource() == frame.getSignUpView().getConfirmButton()) {
                mainMenuController.setUser(signUpController.getUser());
                mainMenuController.setIsRegistered(signUpController.getUser().isRegistered());
                mainMenuController.displayAccountBalance();
                mainMenuController.setGreeting();
                frame.setCard(1);
            } else if (e.getSource() == frame.getMainMenuView().getTicketButton()) {
                ticketController.setUser(mainMenuController.getUser());
                ticketController.displayAccountBalance();
                ticketController.initializeTickets();
                frame.setCard(3);
            } else if (e.getSource() == frame.getMainMenuView().getMovieButton()) {
                if (mainMenuController.checkBalance()) {
                    movieController.setUser(mainMenuController.getUser());
                    movieController.setMovies();
                    frame.setCard(4);
                }
            } else if (e.getSource() == frame.getTicketView().getBackButton()) {
                mainMenuController.setUser(ticketController.getUser());
                mainMenuController.displayAccountBalance();
                frame.setCard(1);
            } else if (e.getSource() == frame.getMovieView().getBackButton()) {
                frame.setCard(1);
            } else if (e.getSource() == frame.getMovieView().getConfirmButton()) {
                seatController.setUser(mainMenuController.getUser());
                frame.setCard(5);
            } else if (e.getSource() == frame.getSeatView().getCancelButton()) {
                frame.setCard(1);
            } else if (e.getSource() == frame.getSeatView().getConfirmButton()) {
                ticketController.setUser(seatController.getUser());
                ticketController.displayAccountBalance();
                ticketController.initializeTickets();
                frame.setCard(3);
            }
        }
    }

    public static void main(String[] args) {
        new MainApp();
    }
}


