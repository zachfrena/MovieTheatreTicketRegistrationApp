# Movie Theater Ticket Reservation App 

### The application represents a system that can be used by two group of users: 
- Ordinary users that can search for a movie, select an specific theater, view available show times, view graphically available seats, select the desired seat, make payment by 
credit card, receive a copy of ticket and the receipt, via email. Users also should be able to cancel their ticket only up to 72 hours prior to show and receive a credit with %15 administration fee for future purchase up maximum of one-year expiration date. 
- Registered Users (RUs) have their information such as name, address, credit and/or debit card account must be saved on the system’s database. RUs must pay a $20.00 annual account fee, but they don't have to pay 15% admin fee for cancelling their tickets and will receive the movie news before public announcement. 

### Addition contraints: 
- Only 10% of the seats can be purchased by RUs on a first come first serve policy 
prior to public announcements.

--- 

## How to run:
- The `MainApp.java` classfile provides functionality for running the application from the start.
- Requires a driver for the intended database.
- Download the appropriate driver, and add .jar to classpath.
-This project used MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/

### Usernames already in Database (for testing purposes):
    * username1
    * username2
    * user2
    * u123
 
## sample application images: 

### Sign-up page:
![sign up page](https://github.com/zachfrena/MovieTheatreTicketRegistrationApp/blob/main/SamplePhotos/SignUpFormPage.JPG)

### Main Menu page:
![main menu](https://github.com/zachfrena/MovieTheatreTicketRegistrationApp/blob/main/SamplePhotos/MainMenuPage.JPG)

### Ticket Selection page:
![ticket selection](https://github.com/zachfrena/MovieTheatreTicketRegistrationApp/blob/main/SamplePhotos/TicketSelectionPage.JPG)

### Seat Selection page:
![seat selection](https://github.com/zachfrena/MovieTheatreTicketRegistrationApp/blob/main/SamplePhotos/SeatSelectionPage.JPG)
