package database;

import beans.DateBean;
import beans.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class RecommendationsDB implements Serializable {
    private static ArrayList<DateBean> recommendations;
    private static ArrayList<String> tags;

    public RecommendationsDB() {

    }

    public static ArrayList<String> getUserTags(User user) {
        //tags = new ArrayList<String>(); // list for dates
        ResultSet rs = null;
        int uId = user.getID();
        try {
            tags = new ArrayList<String>();
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr", "std_db_user", "Lu-vr49er!");
            PreparedStatement ps = con.prepareStatement("select * from users where ID = ?");
            ps.setString(1, Integer.toString(user.getID()));
            rs = ps.executeQuery();
            String tagsString = "";
            while (rs.next()) {
                tagsString += rs.getString("tags");
            }
            String[] tagsList = tagsString.split(",");
            for (int i = 0; i < tagsList.length; i++) {
                tags.add(tagsList[i]);
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

        return tags;
    }

    public static ArrayList<DateBean> getDates(String location, String rad, ArrayList<String> tags) {

        recommendations = new ArrayList<>(); // list for dates
        for (int k = 0; k < tags.size(); k++) {
            try {
                URL uri = new URL("https://api.yelp.com/v3/businesses/search?location=" + location + "&radius=" + rad + "&term=" + tags.get(k));
                HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
                conn.setDoInput(true);

                conn.setRequestMethod("GET");

                conn.setRequestProperty("Authorization", "Bearer MSTNVPev1fJLQUOBG-Ck-sBgnRomemMu6urbfvbzNF_S_RdD1CIs4W0sruIstSMNw3A1d749wVs4QFoSibdJwXC6mjwIZ2MhZVshXKtIJejLRIVm3XKXTzcfzCdYXnYx");
                //Get the results and read them
                InputStream results = conn.getInputStream();
                JSONTokener tokener = new JSONTokener(results);
                //Create JSON object
                JSONObject root = new JSONObject(tokener);
                //Extract the JSON array known as "businesses" from the object
                JSONArray stri = root.getJSONArray("businesses");
                //Get the first object from the array of objects
                DateBean date; //arbitrary date object DatesDB

                //loop through JSON array and create new date bean objects to put them in dates arraylist
                for (int i = 0; i < stri.length(); i++) {
                    date = new DateBean(stri.getJSONObject(i).getString("name"), stri.getJSONObject(i).getFloat("rating"), stri.getJSONObject(i).getString("phone"), (String) stri.getJSONObject(i).getString("price"), stri.getJSONObject(i).getJSONObject("location").getString("address1") + ", " + stri.getJSONObject(i).getJSONObject("location").getString("city") + ", " + stri.getJSONObject(i).getJSONObject("location").getString("state") + ", " + stri.getJSONObject(i).getJSONObject("location").getString("zip_code"), stri.getJSONObject(i).getString("id"), stri.getJSONObject(i).getString("url"), stri.getJSONObject(i).getInt("review_count"), stri.getJSONObject(i).getString("image_url"));
                    recommendations.add(date);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return recommendations;
    }

    public static void addDate() {

    }

    public static void getDateName(String id) {

    }
}
