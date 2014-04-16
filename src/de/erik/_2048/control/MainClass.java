package de.erik._2048.control;

import de.erik._2048.view.GameView;

public class MainClass {

	public static void main(String[] args) {

		new MainClass();

	}

	public MainClass() {

		this.initView();
	}

	private void initView() {

		GameView view = new GameView();
		view.setVisible(true);

	}

}
