package com.pes.catrun;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 23/10/2016.
 */

public class CtlUsuaris {

    public void init() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public String getUser(String alies)
    {
        String dirURL = "http://10.4.41.158/getUserAlies.php?alies="+alies;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(dirURL);

        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }




    public String getLogin(String alies, String pwd)
    {
        String dirURL = "http://10.4.41.158/getLogin.php?alies="+alies+"&pwd="+pwd;
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(dirURL);

        try {
            //Log.d("TRY","TRY");
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            //Log.d("C","CATCH");
            e.printStackTrace();
            return e.toString();
        }
    }

    public void postRegister(String alies, String pwd, String email, String faccio) throws JSONException {

        HttpClient httpClient = new DefaultHttpClient();
        String URL = "http://10.4.41.158/postRegister.php?alies="+alies+"&mail="+email+"&pwd="+pwd+"&lvl="+"1"
                +"&exp="+"0"+"&facc="+faccio+"&token="+"null";
        HttpPost httpPost = new HttpPost(URL);

        try {
            HttpResponse response = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
    }
}
