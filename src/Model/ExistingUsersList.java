package Model;
/**
/* The ExistingUsersList class represents a list of users that exist in the database
 */
import Controller.DBController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExistingUsersList {
    private ArrayList<User> existingUsers;
    private DBController databaseController;

    public ExistingUsersList() {
        this.databaseController = new DBController();
        existingUsers = new ArrayList<User>();
        loadExistingUsers(this.databaseController.readAllTables("USERS"));
    }

    public void loadExistingUsers(ResultSet res) {
        try {
            while(res.next()) {
                existingUsers.add(new User (
                res.getString("Username"),
                res.getString("FName"),
                res.getString("LName"),
                res.getString("Email"),
                res.getBoolean("IsRegistered"),
                res.getString("CcNum"),
                res.getInt("AccountBalance")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(User user) {
        existingUsers.add(user);
    }

    public ArrayList<User> getExistingUsers() {
        return existingUsers;
    }

}
