package com.comcast.crm.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
public int getRandomNumber() {
	Random ran = new Random();
	int ranint = ran.nextInt(1000);
	return ranint;
}
public String getSystemDateYYYYDDMM() {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
	String actform = sdf.format(date);
	return actform;
}
public String getRequiredDateYYYYMMDD(int days) {
	Date dobj =new Date();
	SimpleDateFormat sim = new SimpleDateFormat("YYYY-MM-DD");
	Calendar cal = sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String enddate = sim.format(cal.getTime());
	return enddate;
}
}
