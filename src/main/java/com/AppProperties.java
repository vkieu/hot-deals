package com;

import java.util.Properties;

public class AppProperties {


	private static AppProperties instance = new AppProperties();
	private Properties prop;

	private AppProperties() {
		try {
			prop = EncryptorUtil.getPropertiesEncryptor();
			//load the app.properties
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties.enc"));
			//override with system properties via -D
			prop.putAll(System.getProperties());
			//prop.list(System.out);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static AppProperties getInstance() {
		return instance;
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	public boolean getPropertyBoolean(String key) {
		return Boolean.valueOf(getProperty(key));
	}
	public int getPropertyInt(String key) {
		return Integer.parseInt(getProperty(key));
	}
	public double getPropertyDouble(String key) {
		return Double.parseDouble(getProperty(key));
	}

	public Properties getProperties() {
		return prop;
	}
}
