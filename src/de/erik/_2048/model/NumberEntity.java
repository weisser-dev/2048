package de.erik._2048.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import de.erik._2048.utils.PropertiesLoader;

public class NumberEntity {

	public static final int FIELD_SIZE = 105;
	public static final int BORDER_SIZE = 16;
	public static final Font FONT = new Font("Segoe UI", Font.BOLD, 55);

	private int x;
	private int y;
	private int sx;
	private int sy;

	private int value;
	private boolean alive;

	private Color background;

	public NumberEntity(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.alive = true;
		this.sx = this.calculateX();
		this.sy = this.calculateY();

		this.updateValue(value);
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

		g.setColor(this.background);
		g.fillRoundRect(this.sx, this.sy, 105, 105, 10, 10);
		if (this.value <= 4) {
			g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
					.getProperty("font_color_1")));
		} else {
			g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
					.getProperty("font_color_2")));
		}

		if (this.value > 0) {
			g.setFont(NumberEntity.FONT);
			String text = String.valueOf(this.value);
			Rectangle2D textBounds = g.getFontMetrics().getStringBounds(text, g);
			g.drawString(text, (int) (this.sx + ((105 / 2) - textBounds.getCenterX())),
					(int) (this.sy + ((105 / 2) - textBounds.getCenterY())));
		}
	}
	public boolean updatePosition() {
		boolean moved = false;

		int targetX = this.calculateX();
		int targetY = this.calculateY();

		if (this.sx < targetX) {
			this.sx += 11;
			moved = true;
		}
		if (this.sx > targetX) {
			this.sx -= 11;
			moved = true;
		}
		if (this.sy < targetY) {
			this.sy += 11;
			moved = true;
		}
		if (this.sy > targetY) {
			this.sy -= 11;
			moved = true;
		}

		return moved;
	}

	/**
	 * @return
	 */
	private int calculateX() {
		return (this.x * (NumberEntity.FIELD_SIZE + NumberEntity.BORDER_SIZE))
				+ NumberEntity.BORDER_SIZE;
	}

	/**
	 * @return
	 */
	private int calculateY() {
		return (this.y * (NumberEntity.FIELD_SIZE + NumberEntity.BORDER_SIZE))
				+ NumberEntity.BORDER_SIZE;
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

	public int getValue() {
		return this.value;
	}

	public void updateValue(int value) {
		this.value = value;

		switch (this.value) {
			case 2:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("2"));
				break;
			case 4:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("4"));
				break;
			case 8:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("8"));
				break;
			case 16:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("16"));
				break;
			case 32:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("32"));
				break;
			case 64:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("64"));
				break;
			case 128:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("128"));
				break;
			case 256:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("256"));
				break;
			case 512:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("512"));
				break;
			case 1024:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("1024"));
				break;
			case 2048:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("2048"));
				break;
			default:
				this.background = Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("0"));
				break;
		}
	}

}
