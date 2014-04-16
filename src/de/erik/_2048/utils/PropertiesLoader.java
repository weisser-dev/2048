package de.erik._2048.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	private static PropertiesLoader instance;
	public final Properties PROPERTIES = new Properties();

	public static PropertiesLoader getInstance() {

		if (PropertiesLoader.instance == null) {
			PropertiesLoader.instance = new PropertiesLoader();
		}
		return PropertiesLoader.instance;
	}

	private PropertiesLoader() {

		try {
			PROPERTIES.load(PropertiesLoader.class
					.getResourceAsStream("../resources/view.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
