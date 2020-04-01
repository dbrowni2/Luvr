package database;

import beans.DateBean;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class DatesDB {
    private static ArrayList<DateBean> dates;

    public DatesDB() {

    }

    public static ArrayList<DateBean> getDates(String location, String rad){
      dates = new ArrayList<>(); // list for dates
        try {
        URL uri = new URL("https://api.yelp.com/v3/businesses/search?location="+ location + "&radius=" + rad);
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
            DateBean date; //arbitrary date object

            //loop through JSON array and create new date bean objects to put them in dates arraylist
            for(int i = 0; i < stri.length(); i++){
                date = new DateBean(stri.getJSONObject(i).getString("name"),stri.getJSONObject(i).getFloat("rating"),stri.getJSONObject(i).getString("phone"),(String)stri.getJSONObject(i).getString("price"), stri.getJSONObject(i).getJSONObject("location").getString("address1") + ", " + stri.getJSONObject(i).getJSONObject("location").getString("city") + ", " + stri.getJSONObject(i).getJSONObject("location").getString("state") + ", " + stri.getJSONObject(i).getJSONObject("location").getString("zip_code"),stri.getJSONObject(i).getString("id"),stri.getJSONObject(i).getString("url"),stri.getJSONObject(i).getInt("review_count"),stri.getJSONObject(i).getString("image_url"));
                dates.add(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
        }
        public static void addDate(){

        }
}
