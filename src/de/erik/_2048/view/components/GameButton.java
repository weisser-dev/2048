package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.erik._2048.utils.PropertiesLoader;

public class GameButton extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 825688356307799280L;

	public GameButton() {
		this.config();
	}

	private void config() {
		this.setText("New Game");
		Dimension size = new Dimension(130, 40);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setOpaque(false);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);

		this.setForeground(Color.decode(PropertiesLoader.getInstance().PROPERTIES
				.getProperty("color_gameButton_text")));

	}
	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(Color.decode(PropertiesLoader.getInstance().PROPERTIES
				.getProperty("color_background_gameButton")));
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 6, 6);

		super.paint(g);
	}

	@Override
	public void setText(String text) {
		text = "<html><body style=\"font-size:14px\">" + text + "</html>";
		super.setText(text);
	}
}
