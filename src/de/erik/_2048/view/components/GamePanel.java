package de.erik._2048.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import de.erik._2048.model.NumberEntity;
import de.erik._2048.threading.MoveThread;
import de.erik._2048.utils.PropertiesLoader;

public class GamePanel extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -278589946005556435L;

	private List<NumberEntity> listEntities;
	private List<NumberEntity> emptyEntities;
	private ScoreGroupPanel panelScore;
	private int score;

	public GamePanel(ScoreGroupPanel panelScore) {
		this.panelScore = panelScore;

		this.init();
		this.build();
		this.config();
	}

	private void init() {

		this.generateField();
	}

	private void generateField() {

		this.listEntities = new CopyOnWriteArrayList<NumberEntity>();
		this.listEntities.add(new NumberEntity(0, 0, 2));
		this.listEntities.add(new NumberEntity(2, 1, 2));

		this.emptyEntities = new ArrayList<>();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				this.emptyEntities.add(new NumberEntity(x, y, 0));

			}
		}

	}
	public void build() {
		this.setLayout(null);
	}

	@Override
	public void paint(Graphics go) {
		Graphics2D g = (Graphics2D) go;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR);

		g.setColor(Color.decode(PropertiesLoader.getInstance().VIEW_PROPERTIES
				.getProperty("color_background_gamePanel")));
		g.fillRoundRect(0, 0, 499, 499, 15, 15);

		for (NumberEntity numberEntity : this.emptyEntities) {
			numberEntity.paint(g);
		}

		for (NumberEntity numberEntity : this.listEntities) {
			numberEntity.paint(g);
		}

		this.panelScore.getLabelCurrentScore().setScore(Integer.toString(this.score));

	}
	private void config() {
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 500));
		this.setOpaque(false);
		this.setFocusable(true);
		this.addKeyListener(this);
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

	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_RIGHT)
				|| (e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_DOWN)) {

			this.moveEntity(e);
			this.doAddition(e);
			this.moveEntity(e);
			new MoveThread(this).start();
		}

	}

	private void moveEntity(KeyEvent e) {
		boolean moved = false;

		do {
			moved = false;
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				moved = this.moveAction(0, -1);
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				moved = this.moveAction(0, 1);
				//				for (NumberEntity numberEntity : this.listEntities) {
				//					while ((this.getEntityAt(numberEntity.getX(), numberEntity.getY() + 1) == null)
				//							&& (numberEntity.getY() < 3)) {
				//						numberEntity.setY(numberEntity.getY() + 1);
				//						moved = true;
				//					}
				//				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				moved = this.moveAction(-1, 0);
				//				for (NumberEntity numberEntity : this.listEntities) {
				//					while ((this.getEntityAt(numberEntity.getX() - 1, numberEntity.getY()) == null)
				//							&& (numberEntity.getX() > 0)) {
				//						numberEntity.setX(numberEntity.getX() - 1);
				//						moved = true;
				//					}
				//				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moved = this.moveAction(1, 0);
				//				for (NumberEntity numberEntity : this.listEntities) {
				//					while ((this.getEntityAt(numberEntity.getX() + 1, numberEntity.getY()) == null)
				//							&& (numberEntity.getX() < 3)) {
				//						numberEntity.setX(numberEntity.getX() + 1);
				//						moved = true;
				//					}
				//				}
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
			while ((this.getEntityAt(numberEntity.getX() + x, numberEntity.getY() + y) == null)) {
				if (((x > 0) && (numberEntity.getX() < 3))
						|| ((x < 0) && (numberEntity.getX() > 0))) {
					numberEntity.setX(numberEntity.getX() + x);
					moved = true;
				} else if (((y < 0) && (numberEntity.getY() < 3))
						|| ((y > 0) && (numberEntity.getY() > 0))) {
					numberEntity.setY(numberEntity.getY() + y);
					moved = true;
				}
			}
		}
		return moved;
	}
	public boolean isAddition() {
		for (int x = 0; x <= 3; x++) {
			for (int y = 0; y < 3; y++) {
				NumberEntity currentEntity = this.getEntityAt(x, y);
				NumberEntity belowEntity = this.getEntityAt(x, y + 1);
				if ((currentEntity != null) && (belowEntity != null)
						&& (belowEntity.getValue() == currentEntity.getValue())) {
					return true;
				}
			}
		}

		for (int x = 0; x <= 3; x++) {
			for (int y = 3; y > 0; y--) {
				NumberEntity currentEntity = this.getEntityAt(x, y);
				NumberEntity belowEntity = this.getEntityAt(x, y - 1);
				if ((currentEntity != null) && (belowEntity != null)
						&& (belowEntity.getValue() == currentEntity.getValue())) {
					return true;
				}
			}
		}

		for (int y = 0; y <= 3; y++) {
			for (int x = 0; x < 3; x++) {
				NumberEntity currentEntity = this.getEntityAt(x, y);
				NumberEntity belowEntity = this.getEntityAt(x + 1, y);
				if ((currentEntity != null) && (belowEntity != null)
						&& (belowEntity.getValue() == currentEntity.getValue())) {
					return true;
				}
			}
		}

		for (int y = 0; y <= 3; y++) {
			for (int x = 3; x > 0; x--) {
				NumberEntity currentEntity = this.getEntityAt(x, y);
				NumberEntity belowEntity = this.getEntityAt(x - 1, y);
				if ((currentEntity != null) && (belowEntity != null)
						&& (belowEntity.getValue() == currentEntity.getValue())) {
					return true;
				}
			}

		}
		return false;
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
						this.score += belowEntity.getValue();
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
						this.score += belowEntity.getValue();
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
						this.score += belowEntity.getValue();
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
						this.score += belowEntity.getValue();
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
			int count = 0;
			if (generator.nextInt(100) < 66) {
				value = 2;
			} else {
				value = 4;
			}

			do {
				x = generator.nextInt(4);
				y = generator.nextInt(4);
				count++;
			} while ((this.getEntityAt(x, y) != null));
			return new NumberEntity(x, y, value);
		} else {
			return null;
		}

	}

	public void setGameOver(boolean gameOver) {
		if (gameOver) {
			if (this.panelScore.getLabelHighScore().getScore() < this.panelScore
					.getLabelCurrentScore().getScore()) {
				try {
					FileOutputStream fos = new FileOutputStream(
							"src/de/erik/_2048/resources/game.properties");

					PropertiesLoader.getInstance().GAME_PROPERTIES.put("highscore",
							String.valueOf(this.panelScore.getLabelCurrentScore().getScore()));
					PropertiesLoader.getInstance().GAME_PROPERTIES.store(fos, "Save Highscore");
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
