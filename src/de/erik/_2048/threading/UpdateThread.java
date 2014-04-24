package de.erik._2048.threading;

import java.util.ArrayList;
import java.util.List;

import de.erik._2048.model.NumberEntity;
import de.erik._2048.view.components.GamePanel;

public class UpdateThread extends Thread {

	private GamePanel panel;
	private Runnable callback;

	public UpdateThread(GamePanel panel, Runnable callback) {
		this.panel = panel;
		this.callback = callback;
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
				Thread.sleep(4, 5);
			} catch (InterruptedException e) {}
		}
		this.panel.repaint();

		List<NumberEntity> deadEntities = new ArrayList<NumberEntity>();
		for (NumberEntity numberEntity : this.panel.getListEntities()) {
			if (!numberEntity.isAlive()) {
				deadEntities.add(numberEntity);
			} else {
				numberEntity.changeValue();
			}
		}
		this.panel.getListEntities().removeAll(deadEntities);
		this.panel.repaint();
		if (this.callback != null) {
			this.callback.run();
		}
	}
}
