/**
 * 
 */
package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.erik._2048.utils.PropertiesLoader;
import de.erik._2048.view.Spacer;

/**
 * @author weissere
 */
public class GameOverPanel extends JPanel implements MouseListener {

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
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(new Spacer(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets = new Insets(5, 5, 5, 5);
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
		this.buttyTryAgain.addMouseListener(this);

		this.labelText.setText("Game over!");
		this.labelText.setForeground(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("color_jlabel_text")));

		this.labelText.setFont(new Font(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("default_font"), Font.BOLD, 60));

		this.setBackground(new Color(238, 228, 218, 185));
		this.setBounds(0, 0, 499, 499);

	}

	/**
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.gamePanel.restart();
		}

	}

	/**
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
