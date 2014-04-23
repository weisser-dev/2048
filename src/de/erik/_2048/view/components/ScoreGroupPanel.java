package de.erik._2048.view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class ScoreGroupPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 54065589811309381L;

	private ScorePanel labelCurrentScore;
	private ScorePanel labelHighScore;

	public ScoreGroupPanel() {
		this.init();
		this.build();
		this.config();
	}

	private void init() {
		this.labelCurrentScore = new ScorePanel();
		this.labelHighScore = new ScorePanel();

	}

	private void build() {
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 0, 0, 3);
		this.add(this.labelCurrentScore, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 3, 0, 0);
		this.add(this.labelHighScore, gbc);

	}

	private void config() {

		this.labelCurrentScore.setTitle("SCORE");
		this.labelHighScore.setTitle("BEST");
		this.labelHighScore.setScore(0);
		this.labelCurrentScore.setScore(0);

		this.setOpaque(false);

	}

	public ScorePanel getLabelCurrentScore() {
		return this.labelCurrentScore;
	}

	public void setLabelCurrentScore(ScorePanel labelCurrentScore) {
		this.labelCurrentScore = labelCurrentScore;
	}

	public ScorePanel getLabelHighScore() {
		return this.labelHighScore;
	}

	public void setLabelHighScore(ScorePanel labelHighScore) {
		this.labelHighScore = labelHighScore;
	}

}
