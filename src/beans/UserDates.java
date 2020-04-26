package beans;

import java.sql.Date;

public class UserDates {

    int numRating;
    String userEmail, dateID, rating, name;
    Date dateVisited;

    public UserDates(int numRating, String userEmail, String dateID, String rating, Date dateVisited, String name) {
        this.numRating = numRating;
        this.userEmail = userEmail;
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

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
