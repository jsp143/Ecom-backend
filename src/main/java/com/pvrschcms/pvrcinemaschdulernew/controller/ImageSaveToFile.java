package com.pvrschcms.pvrcinemaschdulernew.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

public class ImageSaveToFile {

	public static void main(String[] args) {
		for(int i=0;i<6000;i++) {
			try {
				 URL url = new URL("https://api1.pvrcinemas.com/PVRCinemasCMS/bqr?bookingid=VDEfJ%2Beqo2kbC2ZgBUeLAQ%3D%3D&cinemacode=CTWP&swidth=700");
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
				 FileOutputStream fos = new FileOutputStream("/home/orange/Desktop/qrimage/"+new Date().toString()+".png");
				 fos.write(response);
				 fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
