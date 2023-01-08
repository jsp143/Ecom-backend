package com.pvrschcms.pvrcinemaschdulernew.constant;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Utility {
	@Autowired
    private ObjectMapper objectMapper;

    public <T> T convert(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public <T> T convertxml(String xml, TypeReference<T> typeReference) {
//        XmlMapper xmlMapper = new XmlMapper();
//        try {
//            return xmlMapper.readValue(xml, typeReference);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
	public static boolean comparePassword(String requestedPassword, String originalPassword) {
		return BCrypt.checkpw(requestedPassword, originalPassword);
	}

	// yyyy-MM-dd
	public static final SimpleDateFormat dateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	// dd/MM/yyyy
	public static final SimpleDateFormat dateFormatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

	// dd-MM-yyyy
	public static final SimpleDateFormat dateFormat_dd_MM_yyyy = new SimpleDateFormat("dd-MM-yyyy");
	// dd-MMM-yyyy
	public static final SimpleDateFormat dateFormat_dd_MMM_yyyy = new SimpleDateFormat("dd-MMM-yyyy");
	// yyyy
	public static final SimpleDateFormat dateFormat_yyyy = new SimpleDateFormat("yyyy");
	// MM
	public static final SimpleDateFormat dateFormat_MM = new SimpleDateFormat("MM");
	// DD
	public static final SimpleDateFormat dateFormat_dd = new SimpleDateFormat("dd");
	// Day
	public static final SimpleDateFormat dateFormat_Day = new SimpleDateFormat("EEE");

	// time
	public static SimpleDateFormat timeFormat_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// time
	public static SimpleDateFormat timeFormat_dd_MM_yyyy_HH_mm_ss = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static SimpleDateFormat timeFormat_dd_MM_yyyy_HH_mm = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	// time
	public static SimpleDateFormat timeFormat_dd_MMM_HH_mm = new SimpleDateFormat("dd-MMM HH:mm");

	// time
	public static SimpleDateFormat timeFormat_HH_mm_ss = new SimpleDateFormat("HH:mm aa");

	public static SimpleDateFormat timeFormat_HH_mm_ss1 = new SimpleDateFormat("HH:mm:ss");
	// time
	public static SimpleDateFormat timeFormat_HH_mm_aa = new SimpleDateFormat("HH:mm aa");

	public static final long oneday = 24 * 60 * 60 * 1000;

	public static String getCurrentDate() {
		String fromdate = dateFormat_yyyy_MM_dd.format(Calendar.getInstance().getTime());
		return fromdate;
	}

	public static String getNextDate(String strDate, int days) {
		Date dt = new Date();
		try {
			dt = dateFormat_yyyy_MM_dd.parse(strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		long nextDay = dt.getTime() + (oneday * days);
		Date nextDate = new Date(nextDay);
		String todate = dateFormat_yyyy_MM_dd.format(nextDate);
		return todate;
	}

	public static String getCurrentTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String currentTime() {
		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static Date stringtodate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date1;

	}

	public static Date string_dd_MM_yyyy_HH_mm_ss_todate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date1 = new Date();
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date1;

	}

	public static Date string_dd_MM_yyyy_todate1(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date2 = new Date();
		try {
			date2 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date2;

	}

	public Date stringToTime() throws ParseException {
		String timeS = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDate = dateFormat.parse(timeS);
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		System.out.println("timestamp..........." + timestamp);

		return timestamp;

	}

	public static int monthLastDay(int month, int year) {
		int days = 31;
		int incentiveMonth = Integer.parseInt(Utility.dateFormat_MM.format(new java.util.Date()));
		int incentiveyear = Integer.parseInt(Utility.dateFormat_yyyy.format(new java.util.Date()));
		if (incentiveMonth == month && incentiveyear == year) {
			days = Integer.parseInt(Utility.dateFormat_dd.format(new java.util.Date()));
		} else {

			if (month == 4 || month == 6 || month == 9 || month == 11)
				days = 30;
			else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
				days = 31;
			else if (month == 2 && year % 4 == 0)
				days = 29;
			else
				days = 28;
		}

		return days;
	}

	public static int monthLastDay2(int month, int year) {
		int days = 31;
		int incentiveMonth = Integer.parseInt(Utility.dateFormat_MM.format(new java.util.Date()));
		int incentiveyear = Integer.parseInt(Utility.dateFormat_yyyy.format(new java.util.Date()));
		if (month == 4 || month == 6 || month == 9 || month == 11)
			days = 30;
		else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			days = 31;
		else if (month == 2 && year % 4 == 0)
			days = 29;
		else
			days = 28;

		return days;
	}

	public static Date dateWithoutTime() {
		Date todayDate = null;
		String today = dateFormat_yyyy_MM_dd.format(Calendar.getInstance().getTime());
		try {
			todayDate = dateFormat_yyyy_MM_dd.parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return todayDate;
	}

	public static Date dateWithTime() {
		Date todayDate = null;
		String today = timeFormat_yyyy_MM_dd_HH_mm_ss.format(Calendar.getInstance().getTime());
		try {
			todayDate = timeFormat_yyyy_MM_dd_HH_mm_ss.parse(today);
		} catch (ParseException e) {
			// TODO Auto-generated catch blocktimeFormat_yyyy_MM_dd_HH_mm_ss
			e.printStackTrace();
		}
		return todayDate;
	}

	public static String QutarEndDate() {
		String strQutarDate = "";
		Date date = new Date();
		int intMonth = Integer.parseInt(dateFormat_MM.format(date));
		int intYear = Integer.parseInt(dateFormat_yyyy.format(date));
		if (intMonth == 1 || intMonth == 2 || intMonth == 3)
			strQutarDate = intYear + "-3-31";
		else if (intMonth == 4 || intMonth == 5 || intMonth == 6)
			strQutarDate = intYear + "-6-30";
		else if (intMonth == 7 || intMonth == 8 || intMonth == 9)
			strQutarDate = intYear + "-9-30";
		else if (intMonth == 10 || intMonth == 11 || intMonth == 12)
			strQutarDate = intYear + "-12-31";

		return strQutarDate;

	}

	public static String QuarterStartDate() {
		String strQuarterDate = "";
		Date date = new Date();
		int intMonth = Integer.parseInt(dateFormat_MM.format(date));
		int intYear = Integer.parseInt(dateFormat_yyyy.format(date));
		if (intMonth == 1 || intMonth == 2 || intMonth == 3)
			strQuarterDate = intYear + "-1-1";
		else if (intMonth == 4 || intMonth == 5 || intMonth == 6)
			strQuarterDate = intYear + "-4-1";
		else if (intMonth == 7 || intMonth == 8 || intMonth == 9)
			strQuarterDate = intYear + "-7-1";
		else if (intMonth == 10 || intMonth == 11 || intMonth == 12)
			strQuarterDate = intYear + "-10-1";

		return strQuarterDate;

	}

	public String spaceRemove(String strData) {
		String strRemoveData = "";
		strRemoveData = strData.replaceAll("  ", " ");
		return strRemoveData;

	}

	public String spaceCommaRemove(String strData) {
		String strRemoveData = "";
		if (strData != null) {

			strRemoveData = strData.replaceAll(", ,", ",");
			strRemoveData = strRemoveData.replaceAll(",,", ",");
			strRemoveData = strRemoveData.replaceAll(" ,", ",");
			strRemoveData = strRemoveData.replaceAll(",,", ",");
		}
		return strRemoveData;

	}

	public String spaceFirst(String strData) {
		String strRemoveData = "";
		int k = strData.length();
		for (int t = 0; t < k; t++) {
			char c = strData.charAt(t);
			if (c == ',')
				strRemoveData = strData.substring(1, k);

		}
		return strRemoveData;

	}
	public static String hifenRemove(String strData) {
		String strRemoveData = "";
		if (strData != null) {

			strRemoveData = strData.replaceAll("-", "");
//			strRemoveData = strRemoveData.replaceAll(",,", ",");
//			strRemoveData = strRemoveData.replaceAll(" ,", ",");
//			strRemoveData = strRemoveData.replaceAll(",,", ",");
		}
		return strRemoveData;

	}
	
	public static String getCurrentTimewithoutspace() {
        String timeStamp = new SimpleDateFormat("ddMMYYYYHHMMSS").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

}
