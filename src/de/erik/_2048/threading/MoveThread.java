package de.erik._2048.threading;

import java.util.ArrayList;
import java.util.List;

import de.erik._2048.model.NumberEntity;
import de.erik._2048.view.components.GamePanel;

public class MoveThread extends Thread {

	private GamePanel panel;

	public MoveThread(GamePanel panel) {
		this.panel = panel;
	}

	@Override
	public void run() {
		while (true) {
			boolean moved = false;
			for (NumberEntity numberEntity : this.panel.getListEntities()) {
				boolean updatePosition = numberEntity.updatePosition();
				if (updatePosition) {
					moved = true;
				}
			}

			if (!moved) {
				break;
			}
			try {
				this.panel.repaint();
				Thread.sleep(2);
			} catch (InterruptedException e) {}
		}

		List<NumberEntity> deadEntities = new ArrayList<NumberEntity>();
		for (NumberEntity numberEntity : this.panel.getListEntities()) {
			if (!numberEntity.isAlive()) {
				deadEntities.add(numberEntity);
			}
		}

		this.panel.getListEntities().removeAll(deadEntities);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		if (this.panel.getMoveCount() > 0) {

			NumberEntity temp = this.panel.generateNewEntity();
			if (temp != null) {
				this.panel.getListEntities().add(this.panel.generateNewEntity());
				if (this.panel.isGameOver()) {
					this.panel.setGameOver(true);
				}
			} else if (this.panel.isGameOver()) {
				this.panel.setGameOver(true);
			}
		}
		this.panel.repaint();
	}
}
