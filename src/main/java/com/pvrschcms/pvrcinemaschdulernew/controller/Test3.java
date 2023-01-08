package com.pvrschcms.pvrcinemaschdulernew.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Test3 {

	public static void main(String[] args) {
		for(int i=0;i<200;i++) {
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					MediaType mediaType = MediaType.parse("text/plain");
					RequestBody body = RequestBody.create(mediaType, "");
					Request request = new Request.Builder()
					  .url("https://static1.pvrcinemas.com/PVRCinemasCMS/moviebuffsync")
					  .method("POST", body)
					  .build();
					try {
						Response response = client.newCall(request).execute();
						System.out.println(" :: "+i+" :: response ::"+new Gson().toJson(response).toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		}
		
	}
	
	public static int calcDistance(String latA1, String longA1, String latB2, String longB2) {
		double latA = Double.parseDouble(latA1);
		double longA = Double.parseDouble(longA1);
		double latB = Double.parseDouble(latB2);
		double longB = Double.parseDouble(longB2);

		double theDistance = (Math.sin(Math.toRadians(latA)) * Math.sin(Math.toRadians(latB))
				+ Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)) * Math.cos(Math.toRadians(longA - longB)));
		theDistance = new Double((Math.toDegrees(Math.acos(theDistance))) * 69.09 * 1.609344);
		return (int) (theDistance * 1000);
	}
	
	public static void getcityname() {
		double lat = 28.5363974;
		double lng = 77.27057889999999;
		try {
		OkHttpClient client = new OkHttpClient().newBuilder()
				.connectTimeout(20, TimeUnit.SECONDS)
				.readTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(20, TimeUnit.SECONDS)
				.build();
		
				MediaType mediaType = MediaType.parse("application/json");
				Map<String, Object> objBody = new LinkedHashMap<String, Object>();
				
				
				String reqBodyString = (new Gson()).toJson(objBody).replaceAll("\"", "\\\"");
				RequestBody body = RequestBody.create(mediaType, reqBodyString);
				Request request = new Request.Builder()
				  .url("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + ","
		                    + lng + "&sensor=true&key=YOUR_API_KEY")
				  .method("POST", body)
				  .addHeader("Content-Type", "application/json")
				  .addHeader("Authorization", "Basic cmFrZXNoLm1pc2hyYUBwdnJjaW5lbWFzLmNvbTphZG1pbg==")
				  .addHeader("Cookie", "JSESSIONID=A4059C56161BAAF049C367E83D100C13")
				  .build();
				try {
					Response response = client.newCall(request).execute();
					//System.out.println(new Gson().toJson(response).toString());
					//responseOM = new Gson().fromJson(response.body().string(), POSCinemaResponseOM.class);
					System.err.println("responseOM ###############");
					//System.out.println(new Gson().toJson(responseOM).toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//return responseOM;

	}


}
