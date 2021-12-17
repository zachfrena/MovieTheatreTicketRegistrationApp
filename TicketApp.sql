DROP DATABASE IF EXISTS TicketApp;
CREATE DATABASE TicketApp;
USE TicketApp;

DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
	MovieID		integer not null,
    Title		varchar(50) not null,
    primary key(MovieID)
);

INSERT INTO MOVIE(MovieID, Title)
VALUES
(1, "BladeRunner 2049"),
(2, "Dune"),
(3, "Interstellar"),
(4, "Coherence");

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS(
    Username varchar(15) not null,
    FName varchar(15) not null,
    LName varchar(15) not null,
    Email varchar(30) not null,
    IsRegistered boolean not null,
    AccountBalance integer not null,
    CcNum     varchar(25) not null,
    primary key (Username)
);

INSERT INTO USERS(Username, FName, LName, Email, IsRegistered, AccountBalance, CcNum)
VALUES
("username1", "Zach", "Frena", "zachtest@gmail.com", True, 180, 123456789),
("username2", "Ron", "Smith", "RS@aol.com", False, 40, 987654321),
("u123", "David", "Gordon", "d.cook@yahoo.com", True, 120, 456789123),
("user2", "Greg", "Gregory", "greg@greg.greg", False, 66, 741456963);

DROP TABLE IF EXISTS SHOWING;
CREATE TABLE SHOWING (
	ShowingID		integer not null auto_increment,
    MovieID			integer,
    TheDate			date,
    ShowingTime		time,
    Theatre			varchar(30),
    primary key (ShowingID),
    foreign key (MovieID) references MOVIE(MovieID)
    );

INSERT INTO SHOWING(MovieID, TheDate, ShowingTime, Theatre)
VALUES
(1, "2021-12-22", "14:00:00", "Theatre 1"), 
(1, "2021-12-23", "18:00:00", "Theatre 1"), 
(1, "2022-01-06", "12:00:00", "Theatre 2"), 
(2, "2021-12-10", "10:00:00", "Theatre 3"), 
(2, "2021-12-15", "20:00:00", "Theatre 3"), 
(2, "2021-12-24", "10:00:00", "Theatre 4"), 
(2, "2021-12-17", "15:00:00", "Theatre 4"), 
(3, "2021-12-9", "9:00:00", "Theatre 5"), 
(3, "2021-12-9", "12:00:00", "Theatre 5"), 
(3, "2021-12-9", "15:00:00", "Theatre 5"), 
(3, "2021-12-9", "18:00:00", "Theatre 5"), 
(4, "2021-12-25", "19:00:00", "Theatre 6"); 

    
DROP TABLE IF EXISTS TICKET;
CREATE TABLE TICKET (
	TicketID		integer auto_increment not null ,
    ShowingID		integer not null,
    Username		varchar(15) not null,
    Seat			integer not null,
    primary key(TicketID),
    foreign key (ShowingID) references SHOWING(ShowingID),
    foreign key (Username) references USERS(Username)
);

INSERT INTO TICKET(ShowingID, Username, Seat)
VALUES
(1, "username1", 7),
(2, "username2", 7), 
(1, "u123", 10), 
(4, "user2", 16), 
(4, "user2", 8), 
(4, "username1", 5), 
(4, "u123", 12);

    