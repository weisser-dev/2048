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

	private static final long serialVersionUID = 7468367842801315073L;

	public ScorePanel() {
		this.init();
		this.build();
		this.config();
	}

	private void init() {
		labelTitle = new JLabel();
		labelScore = new JLabel();

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
		this.add(labelTitle, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(-5, 0, 0, 0);
		this.add(labelScore, gbc);

	}
	private void config() {
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelScore.setHorizontalAlignment(SwingConstants.CENTER);

		labelTitle.setForeground(Color.decode(PropertiesLoader.getInstance().PROPERTIES
				.getProperty("color_scorePanel_textTitle")));
		labelScore.setForeground(Color.WHITE);
		//
		this.setOpaque(false);
	}

	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(Color.decode(PropertiesLoader.getInstance().PROPERTIES
				.getProperty("color_background_scoreLabel")));
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 4, 4);

		super.paintComponents(g);
	}

	public void setTitle(String title) {
		this.labelTitle.setText("<html> <body style=\"font-size:10px; \"><b>" + title
				+ "</b></html>");
	}

	public void setScore(String score) {
		this.labelScore.setText("<html> <body style=\"font-size:22px; margin-top: -3px \">" + score
				+ "</html>");
	}

}
