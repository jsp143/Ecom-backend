package com.pvrschcms.pvrcinemaschdulernew.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cron {
	
//	@Scheduled(cron = "0/10 * * * * ?")
//	public void check() {
//		System.out.println("check cron call "+new Date());
//	}
	
	
//	@Scheduled(cron = "0 0/3 * * * ?")
//	public void runtemptomain() {
//		System.out.println(" cron call "+new Date());
//		OkHttpClient client = new OkHttpClient().newBuilder()
//				.connectTimeout(40, TimeUnit.SECONDS)
//				.readTimeout(40, TimeUnit.SECONDS)
//				.writeTimeout(40, TimeUnit.SECONDS)
//				 .build();
//		
//				MediaType mediaType = MediaType.parse("text/plain");
//				RequestBody body = RequestBody.create(mediaType, "");
//				Request request = new Request.Builder()
//				  .url("https://static1.pvrcinemas.com/PVRCinemasCMS/temp-trans-status-define-paid")
//				  .method("POST", body)
//				  .build();
//				try {
//					Response response = client.newCall(request).execute();
//					System.out.println(" cron call "+new Date()+" :: resp :: "+new Gson().toJson(response).toString());
//					response.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	}
}
