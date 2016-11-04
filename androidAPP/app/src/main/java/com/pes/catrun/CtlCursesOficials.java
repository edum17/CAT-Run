package com.pes.catrun;

import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Victor on 25/10/2016.
 */

public class CtlCursesOficials
{
    //    String dirURL = "http://10.4.41.158/OpenDataAPI.php";

    public static void init() {
        // Gets the URL from the UI's text field.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public static String getJSONCurses()
    {
        String dirURL = "http://10.4.41.158/getCursesOficials.php";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(dirURL);
        //httpGet.addHeader("accept", "application/json");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public static ArrayList<Cursa> getLlistaCurses(String JSON) {
        ArrayList<Cursa> result = new ArrayList<>();
        try {
            JSONArray dadesUser = new JSONArray(JSON);
            for (int i = 0; i < dadesUser.length(); i++) {
                JSONObject jsonUser = dadesUser.getJSONObject(i);
                Cursa c = new Cursa(jsonUser.getString("nom"), jsonUser.getString("data"));
                result.add(c);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
