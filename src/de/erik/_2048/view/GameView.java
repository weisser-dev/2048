package de.erik._2048.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.erik._2048.utils.PropertiesLoader;
import de.erik._2048.view.components.GameButton;
import de.erik._2048.view.components.GamePanel;
import de.erik._2048.view.components.ScoreGroupPanel;

public class GameView extends javax.swing.JFrame {

	/**
	 * Generated Serial
	 */
	private static final long serialVersionUID = 5727718184259554250L;

	private JLabel labelTitle;
	private JLabel labelDescription;

	private GameButton buttonNewGame;

	private JPanel panelHeadTop;
	private ScoreGroupPanel panelScore;
	private GamePanel panelGame;

	private JPanel panelHeadBottom;

	public GameView() {

		this.init();
		this.build();
		this.config();
	}

	private void init() {

		this.labelTitle = new JLabel();
		this.labelDescription = new JLabel();

		this.buttonNewGame = new GameButton("New Game");

		this.panelHeadTop = new JPanel();
		this.panelHeadBottom = new JPanel();
		this.panelScore = new ScoreGroupPanel();
		this.panelGame = new GamePanel(this.panelScore);

	}

	private void build() {
		this.panelHeadTop.setLayout(new GridBagLayout());
		{
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(-10, 0, -15, 0);
			this.panelHeadTop.add(this.labelTitle, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.insets = new Insets(0, 0, 0, 0);
			this.panelHeadTop.add(new Spacer(), gbc);

			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(0, 0, 0, 0);
			this.panelScore.setMinimumSize(new Dimension(280, 0));
			this.panelScore.setPreferredSize(new Dimension(280, 0));
			this.panelHeadTop.add(this.panelScore, gbc);
		}

		this.panelHeadBottom.setLayout(new GridBagLayout());
		{
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 0;
			gbc.insets = new Insets(3, 0, 0, 3);
			this.panelHeadBottom.add(this.labelDescription, gbc);

			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(3, 3, 0, 0);
			this.panelHeadBottom.add(this.buttonNewGame, gbc);
		}

		this.setLayout(new GridBagLayout());
		{
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;

			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(0, 0, 5, 0);
			this.add(this.panelHeadTop, gbc);

			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(5, 0, 5, 0);
			this.add(this.panelHeadBottom, gbc);

			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.weightx = 0;
			gbc.weighty = 1;
			gbc.insets = new Insets(25, 0, 0, 0);
			this.add(this.panelGame, gbc);
		}

	}

	private void config() {
		this.setTitle("2048_-_V1.0a");
		this.configJLabel();

		this.getContentPane().setBackground(
				Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("color_background_gameView")));

		this.setPreferredSize(new Dimension(700, 700));
		this.setMinimumSize(new Dimension(550, 650));

		this.panelHeadBottom.setOpaque(false);
		this.panelHeadTop.setOpaque(false);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

	}
	private void configJLabel() {
		this.labelTitle.setText("2048");
		this.labelDescription
				.setText("<html>Join the numbers and get to the <b>2048 tile!</b></html>");

		this.labelDescription
				.setForeground(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
						.getProperty("color_jlabel_text")));
		this.labelTitle.setForeground(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("color_jlabel_text")));

		this.labelTitle.setFont(new Font(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("default_font"), Font.BOLD, 80));
		this.labelDescription.setFont(new Font(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("default_font"), Font.PLAIN, 18));

	}

}
