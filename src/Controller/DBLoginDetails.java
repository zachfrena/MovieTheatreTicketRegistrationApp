/**
 * The DBLoginDetails interface allows a application users to enter the login credentials for the MySQL Database
 */
package Controller;

public interface DBLoginDetails {
    static final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    static final String theURL = "jdbc:mysql://localhost/TicketApp";

    //  Database credentials
    static final String theUserName = "root";
    static final String thePassword = "Charlotte1";

}
