package Controller;

//import Model.ExistingUsersList;
import Model.ExistingUsersList;
import Model.User;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The LoginController class provides functionality for the application to login a given user.
 */
public class LoginController {

    private User loggedInUser;
    private ExistingUsersList existingUsersList;
    private LoginView loginView;

    public LoginController(){
        existingUsersList = new ExistingUsersList();
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String username) {
        for (User theUser : existingUsersList.getExistingUsers()) { //loop through every existing user in DB
            if(theUser.getUsername().contentEquals(username)) {
                loggedInUser= theUser;
                loginView.setStatus(true);
                System.out.println(theUser.getUsername().toString());
                return;
            }
        }
    }


    public void setActionListener() {
        loginView.addActionListener(new LoginListener());
    }
    public User getLoggedInUser() { return loggedInUser; }

   class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == loginView.getLoginButton() || e.getSource() == loginView.getInput()){
                login(loginView.getUserName());
            }
        }
    }

}
