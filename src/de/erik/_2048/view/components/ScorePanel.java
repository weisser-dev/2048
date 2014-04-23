package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.erik._2048.utils.PropertiesLoader;

public class ScorePanel extends JPanel {

	private JLabel labelTitle;
	private JLabel labelScore;
	private int score;

	private static final long serialVersionUID = 7468367842801315073L;

	public ScorePanel() {
		this.init();
		this.build();
		this.config();
	}

	private void init() {
		this.labelTitle = new JLabel();
		this.labelScore = new JLabel();

	}

	private void build() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 0, 0, 0);
		this.add(this.labelTitle, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-5, 0, 0, 0);
		this.add(this.labelScore, gbc);

	}
	private void config() {
		this.labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.labelScore.setHorizontalAlignment(SwingConstants.CENTER);

		this.labelTitle.setForeground(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("color_scorePanel_textTitle")));
		this.labelScore.setForeground(Color.WHITE);
		//
		this.setOpaque(false);
	}

	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("color_background_scoreLabel")));
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 4, 4);

		super.paintComponents(g);
	}

	public void setTitle(String title) {
		this.labelTitle.setText("<html> <body style=\"font-size:10px; \"><b>" + title
				+ "</b></html>");
	}

	public void setScore(Integer integer) {
		this.score = Integer.valueOf(integer);
		this.labelScore.setText("<html> <body style=\"font-size:22px; margin-top: -3px \">"
				+ integer + "</html>");
	}

	public Integer getScore() {
		return this.score;
	}

}
