package de.erik._2048.model;

import java.awt.Color;
import java.awt.Graphics2D;

import de.erik._2048.utils.PropertiesLoader;

public class NumberEntity {

	private int x;
	private int y;
	private int sx;
	private int sy;

	private int value;
	private boolean alive;

	public NumberEntity(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.alive = true;
		this.sx = this.x * 121;
		this.sy = this.y * 121;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return this.alive;
	}

	public void paint(Graphics2D g) {

		g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("color_background_gameField")));

		Color background;
		Color font;

		switch (this.value) {
			case 2:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("2"));
				break;
			case 4:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("4"));
				break;
			case 8:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("8"));
				break;
			case 16:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("16"));
				break;
			case 32:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("32"));
				break;
			case 64:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("64"));
				break;
			case 128:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("128"));
				break;
			case 256:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("256"));
				break;
			case 512:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("512"));
				break;
			case 1024:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("1024"));
				break;
			case 2048:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("2048"));
				break;
			default:
				background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("0"));
				break;
		}
		g.setColor(background);
		g.fillRoundRect(this.sx + 16, this.sy + 16, 105, 105, 10, 10);
		if (this.value <= 4) {
			g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
					.getProperty("font_color_1")));
		} else {
			g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
					.getProperty("font_color_2")));
		}
		g.drawString(String.valueOf(this.value), this.sx + 20, this.sy + 20);
	}

	public boolean updatePosition() {
		boolean moved = false;

		if (this.sx < (this.x * 121)) {
			++this.sx;
			moved = true;
		}
		if (this.sx > (this.x * 121)) {
			--this.sx;
			moved = true;
		}
		if (this.sy < (this.y * 121)) {
			++this.sy;
			moved = true;
		}
		if (this.sy > (this.y * 121)) {
			--this.sy;
			moved = true;
		}

		return moved;
	}
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSx() {
		return this.sx;
	}

	public void setSx(int sx) {
		this.sx = sx;
	}

	public int getSy() {
		return this.sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
