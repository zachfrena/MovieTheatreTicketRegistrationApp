/**
 * The MainMenuController class allows information on the main page of the GUI to be updated, set, and retrieved.
 */

package Controller;

import Model.Showing;
import Model.Ticket;
import Model.User;
import view.MainMenuView;
import Controller.DBController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Date;

public class MainMenuController {
    private User user;
    private Showing showing;
    private Ticket ticket;
    private MainMenuView mainMenuView;
    private DBController dataBaseController;


    public MainMenuController(DBController d){
        this.dataBaseController = d;
    }

    public void setActionListener(){
        mainMenuView.addActionListener(new MainMenuListener());
    }

    public void setMainMenuView(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    public void setIsRegistered(boolean isRegistered){
        mainMenuView.setIsRegistered(isRegistered);
    }

    public void setUser(User user){
        this.user = user;
        mainMenuView.setUser(user);
    }

    public User getUser() {
        return this.user;
    }

    public void setGreeting(){
        mainMenuView.setGreeting();
    }

    public boolean checkBalance() {
        int currentBalance = user.getAccountBalance();
        return (currentBalance >= 20);
    }

    public void addBalance(int amount) {
        user.updateAccountBalance(amount);
        updateDataBaseBalance();
        displayAccountBalance();
    }

    public void displayAccountBalance(){
        mainMenuView.setBalance(user.getAccountBalance());
    }

    public void updateDataBaseBalance(){
        dataBaseController.updateDataBaseBalance(this.user);
    }

    public void cancelRegistration() {
        user.setRegistered(false);
        mainMenuView.setIsRegistered(user.isRegistered());
        mainMenuView.setGreeting();
        dataBaseController.setToNonRegistered(user);
    }

    class MainMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == mainMenuView.getAddButton()){

                addBalance(mainMenuView.getAmount());
                mainMenuView.chargeCreditCard(mainMenuView.getAmount());
                mainMenuView.setAmount("");
            } else if (e.getSource() == mainMenuView.getUnregisterButton()){
                cancelRegistration();
            } else if (e.getSource() == mainMenuView.getMovieButton()) {
            	if(!checkBalance()) {
            		mainMenuView.limitedFunds();
            	}
            }
        }
    }

}
