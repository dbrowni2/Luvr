package database;
import beans.User;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

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
                users.add(new User(rs.getString("userID"), rs.getString("userName"), rs.getString("userEmail"), rs.getString("userPass")));
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

    public static void addUser(String userName, String userEmail, String userPass) {
    Random r = new  Random();

        int rawID = r.nextInt(10000);
         String userID = rawID + "";
         users = getUsers();
        for(User user: users){
            if (userID.equals(user.getID()))
            {
                rawID = r.nextInt(10000);
                 userID = rawID + "";
            }
        }
        User user = new User(userID,userName,userEmail,userPass);
        users.add(user);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (userID, userName, userEmail, userPass) VALUES(?,?,?,?)");
            ps.setString(1,userID);
            ps.setString(2,userName);
            ps.setString(3,userEmail);
            ps.setString(4,userPass);

            ps.executeUpdate();


        } catch (SQLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
    public static void addUser(User user){
users.add(user);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (userID, userName, userEmail, userPass) VALUES(?,?,?,?)");
            ps.setString(1,user.getID());
            ps.setString(2,user.getuName());
            ps.setString(3,user.getuEmail());
            ps.setString(4,user.getuPass());
            ps.executeUpdate();


        } catch (SQLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static User getUserByID(String id){
        for(User use: users){
            if(use.getID().equalsIgnoreCase(id)){
                return use;
            }
        }
        return null;
    }
/**
    // This is in place of a getUserById() method
    public static User getUserByFirstName(String name) throws SQLException {
        User user = null;
        for (User u: UserDB.getUsers())
            if (u.getFirstName().equals(name))
                user = u;
        return user;
    }


    public static void addUser(String userID,String fName, String lName, String address, String city, String state, String country, String zip, String email, String username, String password){
        User user = new User(userID,fName, lName, address, city, state, zip, country, email, username, password);
        users.add(user);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try{
            String query= "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,userID);
            st.setString(2,fName);
            st.setString(3,lName);
            st.setString(4,email);
            st.setString(5,address);
            st.setString(6,city);
            st.setString(7,state);
            st.setString(8,zip);
            st.setString(9,country);
            st.setString(10,username);
            st.setString(11,password);

            st.executeUpdate();
        } catch (SQLException e){

        } finally{
            pool.freeConnection(connection);
        }
    }
    public static void addUser(User user){
        users.add(user);
    }

    public static void addLogin(String id, String name, String pass){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        try{
            String query="INSERT INTO LOGINS VALUES(?,?,?);";
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1,id);
            st.setString(2,name);
            st.setString(3,pass);
            st.executeUpdate();

        } catch (SQLException e){

        } finally{
            pool.freeConnection(connection);
        }
    }
 **/
}
