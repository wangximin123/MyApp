package com.example.administrator.myapp.util;

import android.net.UrlQuerySanitizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {

    public static String sendRequest(String address){
        InputStream in=null;
        BufferedReader bufferedReader=null;
        StringBuilder builder=null;
        try {
            URL url=new URL(address);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            in=connection.getInputStream();
            bufferedReader=new BufferedReader(new InputStreamReader(in));
            builder=new StringBuilder();
            String s=null;
            while ((s=bufferedReader.readLine())!=null){
                builder.append(s);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }finally {
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
