package org.iii.see.utility;

import java.util.ResourceBundle;

public class SystemProperties {

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("system");

	public static String getFileDirectory() {
		return resourceBundle.getString("FileDirectory");
	}
	
}

