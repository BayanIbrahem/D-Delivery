package com.example.qubit.d_delivery;

import static android.content.ContentValues.TAG;

import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

//this class changes every thing from the attributes to the final url, then every steps until JSONObject
public  class STR_TO_JSON {
    private static JSONObject jsonObject;


    private static URL generateUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            return url;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, "GENERATING URL... invalid url, connection stopped");
            return null;
        }
    }
    private static InputStream makeConnection(URL url) {
        HttpURLConnection connection = null;
        try {connection = (HttpURLConnection) url.openConnection();}
        catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "makeConnection: Failed to start connection.. connection canceled ");
            return null;
        }
        /********/
        try {connection.setRequestMethod("GET");}
        catch (ProtocolException e) {
            e.printStackTrace();
            Log.e(TAG, "makeConnection: Failed to setRequest method, \'protocol exception\'.. connection canceled");
            return null;
        }
        /********/
        connection.setConnectTimeout(15000/*milliseconds*/);
        connection.setReadTimeout(10000/*milliseconds*/);
        try {connection.connect();}
        catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "mackConnection: Failed to connect with server.. connection canceled");
            return null;
        }
        /********/
        int code = 404;
        try {code = connection.getResponseCode();}
        catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "mackConnection: Failed to get response code for connection.. connection canceled");
            return null;
        }
        /********/
        if(code < 299 && code >= 200) {
            try {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
            catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "makeConnection: Failed to get input stream from the connection.. connection canceled");
                return null;
            }
            /********/
        }
        else {
            Log.e(TAG, "makeConnection, unknown error response code: "+ code + ".. connection canceled");
            return null;
        }
    }
    private static String readInputStream(InputStream inputStream){
        StringBuilder content = new StringBuilder();
        if(content == null){
            Log.e(TAG, "read inputStream, Failed to generate String builder to get the string value.. getting data canceled");
            return null;
        }
        InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String line;
        try {
            line = bufferedReader.readLine();
            while(line != null){
                content.append(line);
                line = bufferedReader.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "read inputStream, Failed to read line from the buffer reader.. getting data canceled");
            return null;
        }
        return content.toString();
    }
    private static JSONObject parseJSONObject(String strJsonObject) {
        try {
            return new JSONObject(strJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "parse json object, Failed to create json object form the key.. getting data failed");
            return null;
        }
    }

    public static JSONObject getJsonObject(String strUrl) {
        URL url = generateUrl(strUrl);
        return getJsonObject(url);

    }
    public static JSONObject getJsonObject(URL url){
        if(url == null)
            return null;
        InputStream inputStream = makeConnection(url);
        if(inputStream == null)
            return null;
        String strJsonObject = readInputStream(inputStream);
        if(strJsonObject == null)
            return null;
        return parseJSONObject(strJsonObject);
    }
}
