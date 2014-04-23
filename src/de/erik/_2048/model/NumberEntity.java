package de.erik._2048.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

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

		g.setColor(this.background);
		g.fillRoundRect(this.sx, this.sy, 105, 105, 10, 10);
		if (this.value <= 4) {
			g.setColor(GameConstants.COLOR_NUMBER_ENTITY_FONT1);
		} else {
			g.setColor(GameConstants.COLOR_NUMBER_ENTITY_FONT2);
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
				this.background = GameConstants.COLOR_VALUE_2;
				break;
			case 4:
				this.background = GameConstants.COLOR_VALUE_4;
				break;
			case 8:
				this.background = GameConstants.COLOR_VALUE_8;
				break;
			case 16:
				this.background = GameConstants.COLOR_VALUE_16;
				break;
			case 32:
				this.background = GameConstants.COLOR_VALUE_32;
				break;
			case 64:
				this.background = GameConstants.COLOR_VALUE_64;
				break;
			case 128:
				this.background = GameConstants.COLOR_VALUE_128;
				break;
			case 256:
				this.background = GameConstants.COLOR_VALUE_256;
				break;
			case 512:
				this.background = GameConstants.COLOR_VALUE_512;
				break;
			case 1024:
				this.background = GameConstants.COLOR_VALUE_1024;
				break;
			case 2048:
				this.background = GameConstants.COLOR_VALUE_2048;
				break;
			default:
				this.background = GameConstants.COLOR_VALUE_0;
				break;
		}
	}

}
