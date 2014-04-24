package de.erik._2048.view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import de.erik._2048.model.GameConstants;
import de.erik._2048.model.NumberEntity;
import de.erik._2048.threading.UpdateThread;

public class GamePanel extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -278589946005556435L;

	private List<NumberEntity> listEntities;
	private List<NumberEntity> emptyEntities;
	private ScoreGroupPanel panelScore;
	private GameOverPanel gameOverPanel;
	private int score;
	private int elementsChanged = 0;

	public GamePanel(ScoreGroupPanel panelScore) {
		this.panelScore = panelScore;

		this.init();
		this.build();
		this.config();
	}

	private void init() {

		this.generateField();
		this.gameOverPanel = new GameOverPanel(this);
	}

	private void generateField() {

		this.listEntities = new CopyOnWriteArrayList<NumberEntity>();
		this.listEntities.add(this.generateNewEntity());
		this.listEntities.add(this.generateNewEntity());

		this.emptyEntities = new ArrayList<>();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				this.emptyEntities.add(new NumberEntity(x, y, 0));
			}
		}

		new UpdateThread(this, null);
	}

	public void build() {
		this.setLayout(null);
		this.add(this.gameOverPanel);

	}

	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(GameConstants.COLOR_gamePanel);
		g.fillRoundRect(0, 0, 499, 499, 15, 15);

		for (NumberEntity numberEntity : this.emptyEntities) {
			numberEntity.paint(g);
		}

		for (NumberEntity numberEntity : this.listEntities) {
			numberEntity.paint(g);
		}

		this.panelScore.getLabelCurrentScore().setScore(this.score);
		super.paintChildren(g);

	}
	private void config() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		this.setOpaque(false);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.gameOverPanel.setVisible(false);
	}

	public List<NumberEntity> getListEntities() {
		return this.listEntities;
	}

	/**
	 * 
	 */
	private NumberEntity getEntityAt(int x, int y) {
		for (NumberEntity entity : this.listEntities) {
			if ((entity.getX() == x) && (entity.getY() == y)) {
				return entity;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	public void addNewEntities() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}

		if (this.elementsChanged > 0) {
			NumberEntity temp = this.generateNewEntity();

			if (temp != null) {
				this.listEntities.add(this.generateNewEntity());

			}
			if (this.isGameOver()) {
				this.setGameOver(true);

			}
		}

		new UpdateThread(this, null).start();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)
				|| (e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {

			this.elementsChanged = 0;
			int tempScore = this.score;
			this.moveEntity(e, this.elementsChanged);
			this.doAddition(e);
			if (this.score > tempScore) {
				this.elementsChanged++;
			}
			this.moveEntity(e, this.elementsChanged);

			new UpdateThread(this, new Runnable() {

				@Override
				public void run() {
					GamePanel.this.addNewEntities();
				}

			}).start();

		}

	}

	private void moveEntity(KeyEvent e, int moveCount) {
		boolean moved = false;

		do {
			moved = false;
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				moved = this.moveAction(0, -1);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				moved = this.moveAction(0, 1);
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moved = this.moveAction(-1, 0);
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moved = this.moveAction(1, 0);
			}
		} while (moved);
	}

	/**
	 * @param moved
	 * @return
	 */
	private boolean moveAction(int x, int y) {
		boolean moved = false;
		for (NumberEntity numberEntity : this.listEntities) {
			while ((this.getEntityAt(numberEntity.getX() + x, numberEntity.getY() + y) == null)
					&& (((x > 0) && (numberEntity.getX() < 3))
							|| ((x < 0) && (numberEntity.getX() > 0))
							|| ((y > 0) && (numberEntity.getY() < 3)) || ((y < 0) && (numberEntity
							.getY() > 0)))) {
				numberEntity.setX(numberEntity.getX() + x);
				numberEntity.setY(numberEntity.getY() + y);
				this.elementsChanged++;
				moved = true;
			}
		}
		return moved;
	}

	public boolean isGameOver() {
		boolean isGameOver = false;
		if (this.listEntities.size() >= 16) {
			for (int x = 0; x <= 3; x++) {
				for (int y = 0; y < 3; y++) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x, y + 1);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() != currentEntity.getValue())) {
						isGameOver = true;
					} else {
						isGameOver = false;
					}
				}
			}

			for (int x = 0; x <= 3; x++) {
				for (int y = 3; y > 0; y--) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x, y - 1);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() != currentEntity.getValue()) && isGameOver) {
						isGameOver = true;
					} else {
						isGameOver = false;
					}
				}
			}

			for (int y = 0; y <= 3; y++) {
				for (int x = 0; x < 3; x++) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x + 1, y);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() != currentEntity.getValue()) && isGameOver) {
						isGameOver = true;
					} else {
						isGameOver = false;
					}
				}
			}

			for (int y = 0; y <= 3; y++) {
				for (int x = 3; x > 0; x--) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x - 1, y);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() != currentEntity.getValue()) && isGameOver) {
						isGameOver = true;
					} else {
						isGameOver = false;
					}
				}

			}
		}
		return isGameOver;
	}
	/**
	 * @param e
	 */
	private void doAddition(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			for (int x = 0; x <= 3; x++) {
				for (int y = 0; y < 3; y++) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x, y + 1);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() == currentEntity.getValue())) {
						currentEntity.setAlive(false);
						belowEntity.updateValue(currentEntity.getValue() * 2);
						belowEntity.setY(belowEntity.getY() - 1);
						this.score += belowEntity.getNewValue();
					}
				}
			}

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			for (int x = 0; x <= 3; x++) {
				for (int y = 3; y > 0; y--) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x, y - 1);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() == currentEntity.getValue())) {
						currentEntity.setAlive(false);
						belowEntity.updateValue(currentEntity.getValue() * 2);
						belowEntity.setY(belowEntity.getY() + 1);
						this.score += belowEntity.getNewValue();
					}
				}
			}

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			for (int y = 0; y <= 3; y++) {
				for (int x = 0; x < 3; x++) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x + 1, y);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() == currentEntity.getValue())) {
						currentEntity.setAlive(false);
						belowEntity.updateValue(currentEntity.getValue() * 2);
						belowEntity.setX(belowEntity.getX() - 1);
						this.score += belowEntity.getNewValue();
					}
				}
			}

		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			for (int y = 0; y <= 3; y++) {
				for (int x = 3; x > 0; x--) {
					NumberEntity currentEntity = this.getEntityAt(x, y);
					NumberEntity belowEntity = this.getEntityAt(x - 1, y);
					if ((currentEntity != null) && (belowEntity != null)
							&& (belowEntity.getValue() == currentEntity.getValue())) {
						currentEntity.setAlive(false);
						belowEntity.updateValue(currentEntity.getValue() * 2);
						belowEntity.setX(belowEntity.getX() + 1);
						this.score += belowEntity.getNewValue();
					}
				}
			}
		}
	}

	/**
	 * 
	 */
	public NumberEntity generateNewEntity() {
		if (this.listEntities.size() < 16) {

			Random generator = new Random();
			int value;
			int x = 0;
			int y = 0;
			if (generator.nextInt(100) < 75) {
				value = 2;
			} else {
				value = 4;
			}

			do {
				x = generator.nextInt(4);
				y = generator.nextInt(4);
			} while ((this.getEntityAt(x, y) != null));
			return new NumberEntity(x, y, value);
		} else {
			return null;
		}

	}

	public void setGameOver(boolean gameOver) {
		if (gameOver) {
			this.saveHighscore();
			this.gameOverPanel.setVisible(true);
		}
	}

	/**
	 * 
	 */
	private void saveHighscore() {
		if (this.panelScore.getLabelHighScore().getScore() < this.score) {
			this.panelScore.getLabelHighScore().setScore(this.score);
		}
	}
	public void restart() {
		this.saveHighscore();
		this.score = 0;
		this.generateField();
		this.gameOverPanel.setVisible(false);
		this.repaint();

	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * @return
	 */
	public ScoreGroupPanel getScoreGroupPanel() {
		return this.panelScore;
	}

}
