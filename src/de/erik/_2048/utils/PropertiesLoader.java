package de.erik._2048.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	private static PropertiesLoader instance;
	public final Properties VIEW_PROPERTIES = new Properties();
	public final Properties GAME_PROPERTIES = new Properties();

	public static PropertiesLoader getInstance() {

		if (PropertiesLoader.instance == null) {
			PropertiesLoader.instance = new PropertiesLoader();
		}
		return PropertiesLoader.instance;
	}

	private PropertiesLoader() {

		try {
			this.VIEW_PROPERTIES.load(PropertiesLoader.class
					.getResourceAsStream("../resources/view.properties"));
			this.GAME_PROPERTIES.load(PropertiesLoader.class
					.getResourceAsStream("../resources/game.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
