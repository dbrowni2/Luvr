package database;

import beans.DateBean;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class RecommendationsDB implements Serializable {

    static ArrayList<DateBean> recommendations;

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

    public static void addDate() {
        // TODO: implement RecommendationsDB.addDate()
    }

    public static void getDateName(String id) {
        // TODO: implement RecommendationsDB.getDateName()
    }
}
