package com.sebastian.main.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpServices {
	
	public String getStringResponse(String urlBase) throws MalformedURLException, IOException, Exception {
        String response = null;
       
        URL url = new URL(urlBase);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("content-type", "application/json; charset=UTF-8");
       

        response = "";
        if (conn.getResponseCode() != 200) {
            if (conn.getErrorStream() != null) {
                response = getResponse(conn.getErrorStream());
            }

            
            if (response.equals("")) {
                response = "{\"message\": \"error\" , \"state\": \"error\", \"nroErr\" ;\"" + "0" + "\" }]";
            }
            
        } else {
            response = getResponse(conn.getInputStream());
        }

        conn.disconnect();
        return response;
    }

    public String getResponse(InputStream i) throws IOException {
        String res = "";
        InputStreamReader in = new InputStreamReader(i);
        BufferedReader br = new BufferedReader(in);
        String output;
        while ((output = br.readLine()) != null) {
            res += (output);
        }

        return res;
    }

}
