/**
 * 
 */
package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.erik._2048.model.GameConstants;
import de.erik._2048.view.Spacer;

/**
 * @author weissere
 */
public class GameOverPanel extends JPanel {

	private GamePanel gamePanel;
	private GameButton buttyTryAgain;
	private JLabel labelText;

	/**
	 * 
	 */
	public GameOverPanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		this.init();
		this.build();
		this.config();
	}

	/**
	 * 
	 */
	private void init() {
		this.buttyTryAgain = new GameButton("Try again");
		this.labelText = new JLabel();

	}

	/**
	 * 
	 */
	private void build() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(105, 5, 5, 5);
		this.add(this.labelText, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
		this.add(this.buttyTryAgain, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(new Spacer(), gbc);

	}

	/**
	 * 
	 */
	private void config() {
		this.buttyTryAgain.setGamePanel(this.gamePanel);

		this.labelText.setText("Game over!");
		this.labelText.setForeground(GameConstants.COLOR_JLABEL_FONT);

		this.labelText.setFont(new Font(GameConstants.FONT_default, Font.BOLD, 60));

		this.setBackground(new Color(238, 228, 218, 185));
		this.setBounds(0, 0, 499, 499);

	}

}
