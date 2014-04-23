package de.erik._2048.view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import de.erik._2048.model.GameConstants;

public class GameButton extends JLabel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 825688356307799280L;
	private GamePanel panelGame;

	public GameButton(String title) {
		this.setText(title);
		this.config();
	}

	private void config() {
		this.addMouseListener(this);
		Dimension size = new Dimension(130, 40);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setOpaque(false);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setVerticalAlignment(SwingConstants.CENTER);

		this.setForeground(GameConstants.COLOR_GAMEBUTTON_FONT);

	}
	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(GameConstants.COLOR_background_gameButton);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 6, 6);

		super.paint(g);
	}

	@Override
	public void setText(String text) {
		text = "<html><body style=\"font-size:14px\">" + text + "</html>";
		super.setText(text);
	}

	/**
	 * @param panelGame
	 */
	public void setGamePanel(GamePanel panelGame) {
		this.panelGame = panelGame;

	}

	/**
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.panelGame.restart();
		}

	}

	/**
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
