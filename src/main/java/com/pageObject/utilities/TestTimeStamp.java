package com.pageObject.utilities;

import java.util.Date;

public class TestTimeStamp {
	
	public static String getDate() {	
	Date d = new Date();
	String ScreenShotName= d.toString().replace(":", "_").replace(" ", "_")+".jpg";
	
	return ScreenShotName;

}
}