package com.pvrschcms.pvrcinemaschdulernew.utils.testingpurpose;

import org.springframework.util.StringUtils;


public class Test1 {

	public static void main(String[] args) {
		Double v = Double.parseDouble("146.17");
		Integer v2 = (int) Math.round(v*100);
		String cancelSeats = " E22".trim();
		//final ObjectMapper mapper = new ObjectMapper();
		//Map<String, String> map = mapper.readValue("{\"WEBSITE\":\"WWW\",\"WEBSITE-PB\":\"WWW\",\"ANDROID\":\"OTH:9\",\"ANDROID-PB\":\"OTH:9\",\"IOS\":\"OTH:9\",\"IOS-PB\":\"OTH:9\",\"DCSITE\":\"WWW\",\"KOTAK\":\"OTH:18\",\"THIRDPARTY\":\"WWW\",\"CMS\":\"WWW\",\"CHATBOT\":\"WWW\",\"\":\"WWW\",\"WEBSITE-SPI\":\"OTH:4\",\"IOS-SPI\":\"OTH:3\",\"ANDROID-SPI\":\"OTH:3\"}", new TypeReference<HashMap<String,String>>(){});
//		System.out.println(" pre :: "+cancelSeat.substring(0, 2).trim());
//		System.out.println(cancelSeat.length()+":: hi :: "+cancelSeat.substring(1, cancelSeat.length()).trim()); 
		//System.out.println(v2);
//		String originalSeats = "";
//		String finalSortedSeats = " E20, E21, E22";
//		String[] finalSortedSeatsArr = finalSortedSeats.split(",");
//		for (int j = 0; j < finalSortedSeatsArr.length; j++) {
//			if (!finalSortedSeatsArr[j].trim().equalsIgnoreCase(cancelSeats.trim())) {
//				originalSeats += finalSortedSeatsArr[j]+",";
//			}
//		}
//		originalSeats = removelastcharfromstring(originalSeats);
//		System.out.println(originalSeats);
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
//        try {
//			 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//			 Date dte = sdf.parse("13-Jan-2021");
//		        String releaseDate =sdf1.format(sdf.parse("13-Jan-2021"));
//		        System.out.println(releaseDate+dte);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		int taxes = 3400;
		int numseats = 2;
		int noofcancel = 1;
		System.out.println((taxes-((taxes/numseats)*noofcancel)));
		double newAmt=1300;
		double amtOld = 1400;
		int dona = 200;
		//System.out.println("dssssdd :: "+ Double.valueOf(((newAmt-amtOld>0?newAmt-amtOld:0)+dona) / 100).toString());
		
		String modifytype="SEAT_CHANGE";
		String oldBookingId="TEST0000167969";
		if((modifytype != null && !modifytype.equalsIgnoreCase("")) || (oldBookingId != null && !oldBookingId.equalsIgnoreCase(""))) {
			System.out.println("hi cond");
		}
		if (oldBookingId != null) {
			System.out.println("hi cond oldBookingId");
		}
        String abs = "";
		if(StringUtils.isEmpty(abs)) {
			System.out.println(abs);
		}
       
        
        int atp=12500;
        int discount = 10;
        int maxCap = 75;
        float disamt = atp*discount/(100*100);
		if(disamt>maxCap) {
			disamt = maxCap;
		}
		int dist = calcDistance("30.711399", "76.215795", "30.709210446973046", "76.71060048043728");
		//System.out.println(dist);
		String string ="TUBELIGHT (HINDI) (EXCLUSIVELY FOR WOMEN)";
		//System.out.println(string.replaceAll("\\(.*?\\)", ""));
		
		String mbobj ="ds";
		String TEST_CONFIG = "NO";
		if(!mbobj.equalsIgnoreCase("") && TEST_CONFIG.equalsIgnoreCase("NO")) {
			System.out.println("by search movie db :: ");
		}else {
			System.out.println("by search movie name :: ");
			
		}
		
//		StringBuffer cinemaCode = new StringBuffer();
//		for(int i=0;i<4;i++) {
//			cinemaCode.append("'Test',");
//		}
//		System.out.println(cinemaCode.toString().substring(0,cinemaCode.toString().length() - 1));
		//getcityname();
		
		//private String Address1 = "", Address2 = "", City = "", State = "", Country = "", County = "", PIN = "";
		
//		Geocoder gcd = new Geocoder(this, Locale.getDefault());
//		List<Address> addresses = null;
//		try {
//		    addresses = gcd.getFromLocation(lat, lng, 1);
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//		if (addresses != null && addresses.size() > 0) {
//		    String locality = addresses.get(0).getLocality();
//		}
	}
	
	private static String removelastcharfromstring(String str) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
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
	
//	public static void getcityname() {
//		double lat = 28.5363974;
//		double lng = 77.27057889999999;
//		try {
//		OkHttpClient client = new OkHttpClient().newBuilder()
//				.connectTimeout(20, TimeUnit.SECONDS)
//				.readTimeout(20, TimeUnit.SECONDS)
//				.writeTimeout(20, TimeUnit.SECONDS)
//				.build();
//		
//				MediaType mediaType = MediaType.parse("application/json");
//				Map<String, Object> objBody = new LinkedHashMap<String, Object>();
//				
//				
//				String reqBodyString = (new Gson()).toJson(objBody).replaceAll("\"", "\\\"");
//				RequestBody body = RequestBody.create(mediaType, reqBodyString);
//				Request request = new Request.Builder()
//				  .url("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + ","
//		                    + lng + "&sensor=true&key=YOUR_API_KEY")
//				  .method("POST", body)
//				  .addHeader("Content-Type", "application/json")
//				  .addHeader("Authorization", "Basic cmFrZXNoLm1pc2hyYUBwdnJjaW5lbWFzLmNvbTphZG1pbg==")
//				  .addHeader("Cookie", "JSESSIONID=A4059C56161BAAF049C367E83D100C13")
//				  .build();
//				try {
//					Response response = client.newCall(request).execute();
//					//System.out.println(new Gson().toJson(response).toString());
//					//responseOM = new Gson().fromJson(response.body().string(), POSCinemaResponseOM.class);
//					System.err.println("responseOM ###############");
//					//System.out.println(new Gson().toJson(responseOM).toString());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		//return responseOM;
//
//	}
	
//	public class getReverseGeoCoding {
//	    private String Address1 = "", Address2 = "", City = "", State = "", Country = "", County = "", PIN = "";
//	    double lat = 28.5363974;
//		double lng = 77.27057889999999;
//	    public void getAddress() {
//	        Address1 = "";
//	        Address2 = "";
//	        City = "";
//	        State = "";
//	        Country = "";
//	        County = "";
//	        PIN = "";
//	        
//	        try {
//	            
////	            JSONObject jsonObj = parser_Json.getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + Global.curLatitude + ","
////	                    + Global.curLongitude + "&sensor=true&key=YOUR_API_KEY");
//	        	
//	        	//JSONObject jsonObj = parser_Json.getJSONfromURL();
//	            String Status = jsonObj.getString("status");
//	            if (Status.equalsIgnoreCase("OK")) {
//	                JSONArray Results = jsonObj.getJSONArray("results");
//	                JSONObject zero = Results.getJSONObject(0);
//	                JSONArray address_components = zero.getJSONArray("address_components");
//	                
//	                for (int i = 0; i < address_components.length(); i++) {
//	                    JSONObject zero2 = address_components.getJSONObject(i);
//	                    String long_name = zero2.getString("long_name");
//	                    JSONArray mtypes = zero2.getJSONArray("types");
//	                    String Type = mtypes.getString(0);
//	                    
//	                    if (!long_name.equals(null) || long_name.length() > 0 || long_name != "") {
//	                        if (Type.equalsIgnoreCase("street_number")) {
//	                            Address1 = long_name + " ";
//	                        } else if (Type.equalsIgnoreCase("route")) {
//	                            Address1 = Address1 + long_name;
//	                        } else if (Type.equalsIgnoreCase("sublocality")) {
//	                            Address2 = long_name;
//	                        } else if (Type.equalsIgnoreCase("locality")) {
//	                            // Address2 = Address2 + long_name + ", ";
//	                            City = long_name;
//	                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
//	                            County = long_name;
//	                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
//	                            State = long_name;
//	                        } else if (Type.equalsIgnoreCase("country")) {
//	                            Country = long_name;
//	                        } else if (Type.equalsIgnoreCase("postal_code")) {
//	                            PIN = long_name;
//	                        }
//	                    }
//	                    
//	                    // JSONArray mtypes = zero2.getJSONArray("types");
//	                    // String Type = mtypes.getString(0);
//	                    // Log.e(Type,long_name);
//	                }
//	            }
//	            
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	        
//	    }
//	    
//	    public String getAddress1() {
//	        return Address1;
//	        
//	    }
//	    
//	    public String getAddress2() {
//	        return Address2;
//	        
//	    }
//	    
//	    public String getCity() {
//	        return City;
//	        
//	    }
//	    
//	    public String getState() {
//	        return State;
//	        
//	    }
//	    
//	    public String getCountry() {
//	        return Country;
//	        
//	    }
//	    
//	    public String getCounty() {
//	        return County;
//	        
//	    }
//	    
//	    public String getPIN() {
//	        return PIN;
//	        
//	    }
//	    
//	}

}
