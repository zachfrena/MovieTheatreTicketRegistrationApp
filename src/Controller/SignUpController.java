/**
 * The SignUpController class provides functionality for the application to read user-inputted data and generate a new
 * user object.
 */
package Controller;

import Model.User;
import view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController{

	private DBController databaseController;
	private User user;
	private SignUpView signUpView;


	public SignUpController(DBController databaseController) {
		this.databaseController = databaseController;
	}

	public void setSignUpView(SignUpView signUpView){
		this.signUpView = signUpView;
	}

	public void setActionListener() {signUpView.addActionListener(new SignUpListener());}
	
	public void createUser() {
		String username = signUpView.getUserName();
		String fName = signUpView.getFName();
		String lName = signUpView.getLName();
		String email = signUpView.getEmail();
		Boolean isRegistered = signUpView.getRegChoice();
		String creditNum = signUpView.getCreditNum();
		user = new User(username, fName, lName, email, isRegistered, creditNum, 0);
		if (isRegistered)
			payAnnualFee();
		this.databaseController.saveUserToDB(user);
	}
	
	public void payAnnualFee() {
		user.updateAccountBalance(-20);
	}

	public User getUser(){
		return user;
	}

	public class SignUpListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == signUpView.getConfirmButton()) {
				createUser();
			}
		}
	}


}
