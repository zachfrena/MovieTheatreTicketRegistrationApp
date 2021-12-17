package Model;

/**
 * The User class represents a user in the TicketApp.
 *
 */
public class User {
    private String username;
    private String fName;
    private String lName;
    private String email;
    private String creditNum;
    private boolean isRegistered;
    private int accountBalance;

    public User(String username, String fName, String lName, String email, boolean isRegistered, String creditNum, int accountBalance) {
        this.username = username;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.isRegistered = isRegistered;
        this.creditNum = creditNum;
        this.accountBalance = accountBalance;
    }

    public String getCreditNum() {
        return creditNum;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {return fName;}

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    // sets the account balance
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void updateAccountBalance(int transaction){ this.accountBalance += transaction;}

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    @Override
   public String toString() {
        return (this.fName+" "+this.lName);
    }
 }
