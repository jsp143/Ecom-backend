package com.pvrschcms.pvrcinemaschdulernew.utils.testingpurpose;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class ImageSaveToFile2 {

	public static void main(String[] args) {
//		OkHttpClient client = new OkHttpClient().newBuilder()
//				  .build();
//				Request request = new Request.Builder()
//				  .url("https://api1.pvrcinemas.com/PVRCinemasCMS/bqr?bookingid=VDEfJ%2Beqo2kbC2ZgBUeLAQ%3D%3D&cinemacode=CTWP&swidth=700")
//				  .method("GET", null)
//				  .build();
//				try {
//					Response response = client.newCall(request).execute();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		for(int i=0;i<6000;i++) {
			try {
			 URL url = new URL("https://cmsadmin1.pvrcinemas.com/PVRCinemasCMS/bqr?bookingid=VDEfJ%2Beqo2kbC2ZgBUeLAQ%3D%3D&cinemacode=CTWP&swidth=700");
			 InputStream in = new BufferedInputStream(url.openStream());
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			 byte[] buf = new byte[1024];
			 int n = 0;
			 while (-1!=(n=in.read(buf)))
			 {
			    out.write(buf, 0, n);
			 }
			 out.close();
			 in.close();
			 byte[] response = out.toByteArray();
			 FileOutputStream fos = new FileOutputStream("/home/orange/Desktop/qrimagebeta1/"+new Date().toString()+".png");
			 fos.write(response);
			 fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
