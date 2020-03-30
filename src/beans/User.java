package beans;
import database.UserDB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable {
    private String ID;
    private String uName;
    private String uEmail;
    private String uPass;

    public User() {
        this.ID = null;
        this.uName = null;
        this.uEmail = null;
        this.uPass = null;
    }


    public User(String ID, String uName, String uEmail, String uPass) {
        this.ID = ID;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPass = uPass;
    }
    public User(String uName, String uEmail, String uPass){
        Random r = new  Random();

        int rawID = r.nextInt(10000);
        String userID = rawID + "";
        ArrayList<User> users = UserDB.getUsers();
        for(User user: users){
            if (userID.equals(user.getID()))
            {
                rawID = r.nextInt(10000);
                userID = rawID + "";
            }
        }
        this.ID = userID;
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPass = uPass;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPass() {
        return uPass;
    }

    public void setuPass(String uPass) {
        this.uPass = uPass;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
