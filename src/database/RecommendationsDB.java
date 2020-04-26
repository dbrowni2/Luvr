package database;

import beans.DateBean;
import beans.User;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecommendationsDB implements Serializable {

    static ArrayList<DateBean> recommendations;
    static ArrayList<String> tags;
    static final String DB_DRVR = "com.mysql.cj.jdbc.Driver";
    static final String DB_URI  = "jdbc:mysql://mysql-luvr.thedanielhead.com/mysql_luvr";
    static final String DB_USER = "std_db_user";
    static final String DB_PASS = "Lu-vr49er!";

    public RecommendationsDB() {
        // TODO: implement RecommendationsDB.RecommendationsDB()
    }

    public static ArrayList<DateBean> getDates(String location, String rad, String tag) {
        try {
            URL uri = new URL("https://api.yelp.com/v3/businesses/search?location=" + location + "&radius=" + rad +
                    "&term=" + tag);
            DatesDB.makeList(uri, recommendations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendations;
    }

    public static ArrayList<String> getUserTags(User user) {
        String uEmail = user.getuEmail();
        try {
            tags = new ArrayList<String>();
            Class.forName(DB_DRVR).getDeclaredConstructor().newInstance();
            Connection con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE userEmail = ?");
            ps.setString(1, user.getuEmail());
            ResultSet rs = ps.executeQuery();
            StringBuilder tagsString = new StringBuilder();
            while (rs.next()) {
                tagsString.append(rs.getString("tags"));
            }
            String[] tagsList = tagsString.toString().split(",");
            tags.addAll(Arrays.asList(tagsList));
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return tags;
    }

    public static void addDate() {
        // TODO: implement RecommendationsDB.addDate()
    }

    public static void getDateName(String id) {
        // TODO: implement RecommendationsDB.getDateName()
    }
}
