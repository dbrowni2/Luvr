package database;

import beans.UserDates;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UserDatesDB implements Serializable {

    private static ArrayList<UserDates> userDates;
    static final String DB_DRVR = "com.mysql.cj.jdbc.Driver";
    static final String DB_URI  = "jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr";
    static final String DB_USER = "std_db_user";
    static final String DB_PASS = "Lu-vr49er!";
    static final String API_AUTH = "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx";

    public static ArrayList<UserDates> getUserDates(String userEmail) {
        try {
            userDates = new ArrayList<>();

            Class.forName(DB_DRVR).getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM user_dates WHERE userEmail = ?");
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
                userDates.add(new UserDates(rs.getInt("rating_numerical"), rs.getString("userEmail"),
                        rs.getString("dateID"), rs.getString("rating"), rs.getDate("date_time"),
                        rs.getString("name")));

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

//        // Create two SwapOffers: one from the first user, the other from the second
//        createSwapOffer(users.get(0), users.get(1), ItemDB.getItemById("a1"), ItemDB.getItemById("a4"));
//        createSwapOffer(users.get(1), users.get(0), ItemDB.getItemById("a2"), ItemDB.getItemById("a5"));
//
//        // Add the two users to a list and return the list of users

        return userDates;
    }

    public static void addUserDate(int numRating, String userEmail, String dateID, String rating, Date dateVisited,
                                   String name) {
        try{
            java.util.Date init = new SimpleDateFormat("yyyy-MM-dd").parse(dateVisited.toString());
            String dateString2 = new SimpleDateFormat("MM/dd/yyyy").format(init);

            Class.forName(DB_DRVR).getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("INSERT INTO user_dates (userEmail, dateID, rating," +
                    "rating_numerical, date_time, name) VALUES (?,?,?,?, STR_TO_DATE( ?, '%m/%d/%Y'), ?)");
            ps.setString(1, userEmail);
            ps.setString(2, dateID);
            ps.setString(3, rating);
            ps.setInt   (4, numRating);
            ps.setString(5, dateString2);
            ps.setString(6, name);
            ps.executeUpdate();

            //Create JSON object
            JSONObject root = genTokenerRoot(dateID);
            StringBuilder allTags = new StringBuilder();
            JSONArray tagRaw = root.getJSONArray("categories");
            ArrayList<String> tags = new ArrayList<>();
            for (int i = 0; i < tagRaw.length();i++) {
                tags.add(tagRaw.getJSONObject(i).getString("title"));
            }

            ps = con.prepareStatement("SELECT tags FROM users WHERE userEmail = ?");
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString(1) != null) {
                    allTags.append(rs.getString(1));
                    String comma = ", ";
                    for (String s : tags) {
                        allTags.append(comma);
                        allTags.append(s);
                        comma = ", ";
                    }
                }
            } else {
                String comma = "";
                for(String s : tags){
                    allTags.append(comma);
                    allTags.append(s);
                    comma = ", ";
                }
            }

            String comma = "";
            for (String s : tags){
                allTags.append(comma);
                allTags.append(s);
                comma = ", ";
            }

            ps = con.prepareStatement("UPDATE users SET tags = ? WHERE userEmail = ? ");
            ps.setString(1, allTags.toString());
            ps.setString(2, userEmail);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getDetails(String id) throws IOException {

        return genTokenerRoot(id).getString("name");
    }

    public static JSONObject genTokenerRoot(String id) throws IOException {

        URL uri = new URL("https://api.yelp.com/v3/businesses/" + id);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", API_AUTH);

        //Get the results and read them
        InputStream results = conn.getInputStream();
        JSONTokener tokener = new JSONTokener(results);

        //Create JSON object
        return new JSONObject(tokener);
    }
}
