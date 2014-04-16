package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import de.erik._2048.utils.PropertiesLoader;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -278589946005556435L;

	public GamePanel() {

		this.init();
		this.build();
		this.config();
	}

	private void init() {

		// TODO Auto-generated method stub

	}

	private void build() {
		this.setLayout(null);
		int x = 16;
		int y = 16;

		for (int i = 0; i < 16; i++) {
			if (i % 4 == 0 && i != 0) {
				x = 16;
				y += 121;
			}
			this.add(new GameField(105, 105, x, y));
			x += 121;

		}

	}
	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(Color.decode(PropertiesLoader.getInstance().PROPERTIES
				.getProperty("color_background_gamePanel")));
		g.fillRoundRect(0, 0, 499, 499, 15, 15);

		super.paintComponents(g);
	}

	private void config() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		this.setOpaque(false);
	}

}
