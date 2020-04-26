package database;

import beans.User;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UserDB implements Serializable {

    static ArrayList<User> users; // TODO: get rid of this, query the DB instead (if it'll work)
    static final String DB_DRVR = "com.mysql.cj.jdbc.Driver";
    static final String DB_URI  = "jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr";
    static final String DB_USER = "std_db_user";
    static final String DB_PASS = "Lu-vr49er!";

    public static ArrayList<User> getUsers() {
        try {
            users = new ArrayList<>(2);
            Class.forName(DB_DRVR).getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                users.add(new User(rs.getString("userName"), rs.getString("userEmail"), rs.getString("userPass")));

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

//        // Create two SwapOffers: one from the first user, the other from the second
//        createSwapOffer(users.get(0), users.get(1), ItemDB.getItemById("a1"), ItemDB.getItemById("a4"));
//        createSwapOffer(users.get(1), users.get(0), ItemDB.getItemById("a2"), ItemDB.getItemById("a5"));
//
//        // Add the two users to a list and return the list of users

        return users;
    }

    public static boolean addUser(User user) {

        try {
            Class.forName(DB_DRVR).getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (userName, userEmail, userPass) VALUES(?,?,?)");
            ps.setString(1,user.getuName());
            ps.setString(2,user.getuEmail());
            ps.setString(3,user.getuPass());

            if (ps.executeUpdate() == 1) {
                users.add(user);
                return true;
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUserByEmail(String email) {

        for(User u : UserDB.getUsers()) {
            if(u.getuEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
}
