package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import de.erik._2048.utils.PropertiesLoader;

public class GameField extends JPanel {

	private static final long serialVersionUID = -2251442717039809110L;

	public GameField(int width, int height, int x, int y) {
		this.setSize(width, height);
		this.setLocation(x, y);
	}

	@Override
	public void paint(Graphics go) {

		Graphics2D g = (Graphics2D) go;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(Color.decode(PropertiesLoader.getInstance().PROPERTIES
				.getProperty("color_background_gameField")));
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 3, 3);
	}

}
