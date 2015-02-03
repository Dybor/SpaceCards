package com.geekteam.ui.fx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RaceForTheGalaxy extends Application {

	private Stage stage_;
	private final BorderPane board_;
	private final BorderPane mainMenu_;
	private static RaceForTheGalaxy __instance;

	public RaceForTheGalaxy() throws IOException {
		mainMenu_ = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
		board_ = FXMLLoader.load(getClass().getResource("/Board.fxml"));
	}

	@Override
	public void start(final Stage stage) {
		__instance = this;
		initStage(stage);
		stage_.setScene(new Scene(mainMenu_));
		stage_.show();
	}

	public static RaceForTheGalaxy getInstance() {
		return __instance;
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
		stage_.setScene(new Scene(board_));
	}

}
