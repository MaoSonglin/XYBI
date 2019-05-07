package com.xinyibi.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class DriverUtil {

	public static void loadDriverClass(String driverClassName,String driverFile) throws ClassNotFoundException, IOException{
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			if(driverFile != null){ 
				URL url2 = new URL(driverFile);
				ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
				try(URLClassLoader classLoader = new URLClassLoader(new URL[]{url2},contextClassLoader)){
					classLoader.loadClass(driverClassName);
				}
			}else{
				throw e;
			}
		}
	}
}
