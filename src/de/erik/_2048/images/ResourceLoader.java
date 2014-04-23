/**
 * 
 */
package de.erik._2048.images;

import javax.swing.ImageIcon;

/**
 * @author weissere
 */
public class ResourceLoader {

	private static final ResourceLoader INSTANCE = new ResourceLoader();

	/**
	 * 
	 */
	private ResourceLoader() {}

	/**
	 * @return the instance
	 */
	public static ResourceLoader getInstance() {
		return ResourceLoader.INSTANCE;
	}

	/**
	 * 
	 */
	public static ImageIcon getImage(String name) {
		try {
			return new ImageIcon(ResourceLoader.class.getResource("resources/" + name));
		} catch (Exception e) {}
		return new ImageIcon();
	}
}
