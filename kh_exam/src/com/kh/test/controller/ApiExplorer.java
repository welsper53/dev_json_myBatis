package com.kh.test.controller;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
    	String serviceKey = "njtEabtqULoEXNYXk2CCSfqfVtb888N30E7O9uQrOtQ%2FApoUAFIogl7ubRKNCRD9R8x5Qt8cMbCV9odSx3YLDw%3D%3D";
    	
        StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/pathinfo/getLocationInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("stSrch","UTF-8") + "=" + URLEncoder.encode("KT광화문지사", "UTF-8")); /*검색어*/
        
        URL url = new URL(urlBuilder.toString());
        
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        rd.close();
        conn.disconnect();
        
        System.out.println(sb.toString());
    }
}