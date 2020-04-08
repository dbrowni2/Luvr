package beans;

import java.sql.Date;
import java.time.LocalDate;

public class UserDates {
    private int numRating;
    private int userID;
    private String dateID;
    private String rating;
    private String name;
    private Date dateVisited;

    public UserDates() {
    }

    public UserDates(int numRating, int userID, String dateID, String rating, Date dateVisited, String name) {
        this.numRating = numRating;
        this.userID = userID;
        this.dateID = dateID;
        this.rating = rating;
        this.dateVisited = dateVisited;
        this.name = name;
    }


    public int getNumRating() {
        return numRating;
    }

    public void setNumRating(int numRating) {
        this.numRating = numRating;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDateID() {
        return dateID;
    }

    public void setDateID(String dateID) {
        this.dateID = dateID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(Date dateVisited) {
        this.dateVisited = dateVisited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
