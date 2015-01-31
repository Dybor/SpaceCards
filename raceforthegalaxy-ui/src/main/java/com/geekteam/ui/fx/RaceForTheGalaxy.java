package com.geekteam.ui.fx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RaceForTheGalaxy extends Application {

	private Stage stage_;
	private MainMenu mainMenu_;
	private final GameBoard board_;

	public RaceForTheGalaxy() {
		mainMenu_ = new MainMenu(this);
		board_ = new GameBoard();
	}

	@Override
	public void start(final Stage stage) {
		initStage(stage);
		mainMenu_ = new MainMenu(this);
		stage_.setScene(toScene(mainMenu_));
		stage_.show();
	}

	private Scene toScene(final Parent parent) {
		Scene scene = new Scene(parent);
		scene.getStylesheets().add("/application.css");
		return scene;
	}

	private void initStage(final Stage stage) {
		stage_ = stage;
		stage_.setTitle("Race For The Galaxy");
		stage_.getIcons().addAll(new Image("images/icon/16.png"),
				new Image("/images/icon/32.png"),
				new Image("/images/icon/64.png"),
				new Image("/images/icon/128.png"));
	}

	public static void main(final String[] args) {
		launch(args);
	}

	public void joinGame() {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		stage_.setScene(toScene(board_));
	}

}
