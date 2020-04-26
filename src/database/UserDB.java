package database;
import beans.User;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;


public class UserDB implements Serializable {
    private static ArrayList<User> users;

    public static ArrayList<User> getUsers()  {
        ResultSet rs = null;
        try {
            users = new ArrayList<>(2);
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("select * from users");

            rs = ps.executeQuery();

            while(rs.next()){
                users.add(new User(rs.getInt("ID"), rs.getString("userName"), rs.getString("userEmail"), rs.getString("userPass")));
            }
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


//        // Create two SwapOffers: one from the first user, the other from the second
//        createSwapOffer(users.get(0), users.get(1), ItemDB.getItemById("a1"), ItemDB.getItemById("a4"));
//        createSwapOffer(users.get(1), users.get(0), ItemDB.getItemById("a2"), ItemDB.getItemById("a5"));
//
//        // Add the two users to a list and return the list of users

        return users;
    }

    public static boolean addUser(String userName, String userEmail, String userPass) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (userName, userEmail, userPass) VALUES(?,?,?)");
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPass);


            if (ps.executeUpdate() != 1) {
                return false;
            } else {
                return true;
            }


        } catch (SQLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Error e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean addUser(User user) {
        users.add(user);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users ( userName, userEmail, userPass) VALUES(?,?,?)");

            ps.setString(1, user.getuName());
            ps.setString(2, user.getuEmail());
            ps.setString(3, user.getuPass());
            if (ps.executeUpdate() != 1) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static User getUserByID(int id){
        for(User use: users){
            if(use.getID() == id){
                return use;
            }
        }
        return null;
    }

}
