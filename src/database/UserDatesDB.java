package database;


import beans.User;
import beans.UserDates;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class UserDatesDB implements Serializable {
    private static ArrayList<UserDates> userDates;


    public static ArrayList<UserDates> getUserDates(int userID) {
        ResultSet rs = null;
        try {
            userDates = new ArrayList<>();

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("select * from user_dates where userID = ?");
            ps.setInt(1, userID);

            rs = ps.executeQuery();

            while (rs.next()) {
                userDates.add(new UserDates(rs.getInt("rating_numerical"), rs.getInt("ID"), rs.getString("dateID"), rs.getString("rating"), rs.getDate("date_time"),rs.getString("name")));

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

        return userDates;

    }

    public static void addUserDate(int numRating, int userID, String dateID, String rating, Date dateVisited, String name) {
        UserDates userDate = new UserDates(numRating, userID, dateID, rating, dateVisited, name);
        JSONObject root = null;
        ResultSet rs = null;
        try{
        java.util.Date init = new SimpleDateFormat("yyyy-MM-dd").parse(dateVisited.toString());
        String dateString2 = new SimpleDateFormat("MM/dd/yyyy").format(init);



            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_dates (userID, dateID, rating, rating_numerical, date_time, name) VALUES (?,?,?,?, STR_TO_DATE( ?, '%m/%d/%Y'), ?)");
            ps.setInt(1, userID);
            ps.setString(2, dateID);
            ps.setString(3, rating);
            ps.setInt(4, numRating);
            ps.setString(5, dateString2);
            ps.setString(6,name);


            ps.executeUpdate();

            URL uri = new URL("https://api.yelp.com/v3/businesses/" + dateID);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setDoInput(true);

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx");
            //Get the results and read them
            InputStream results = conn.getInputStream();
            JSONTokener tokener = new JSONTokener(results);
            //Create JSON object
            root = new JSONObject(tokener);
            StringBuilder allTags = new StringBuilder();
            JSONArray tagRaw = root.getJSONArray("categories");
            ArrayList<String> tags = new ArrayList<>();
            for(int i = 0; i < tagRaw.length();i++){
                tags.add(tagRaw.getJSONObject(i).getString("title"));
            }

            ps = con.prepareStatement("SELECT tags FROM users where id = ?");
            ps.setInt(1,userID);
            rs = ps.executeQuery();

                if(rs.next()){
                if(rs.getString(1) != null) {


                    allTags.append(rs.getString(1));
                    String comma = ", ";
                    for (String s : tags) {
                        allTags.append(comma);
                        allTags.append(s);
                        comma = ", ";
                    }
                }
                    }
                else{
                    String comma="";
                    for(String s: tags){
                        allTags.append(comma);
                        allTags.append(s);
                        comma=", ";
                    }
                }

            String comma="";
            for(String s: tags){
                allTags.append(comma);
                allTags.append(s);
                comma=", ";
            }
            ps = con.prepareStatement("UPDATE users SET tags = ? where ID = ? ");
            ps.setString(1,allTags.toString());
            ps.setInt(2,userID);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static String getDetails(String id) {
        JSONObject root = null;
        try {
            URL uri = new URL("https://api.yelp.com/v3/businesses/" + id);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setDoInput(true);

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx");
            //Get the results and read them
            InputStream results = conn.getInputStream();
            JSONTokener tokener = new JSONTokener(results);
            //Create JSON object
           root = new JSONObject(tokener);


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root.getString("name");
    }
}
