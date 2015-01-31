package com.geekteam.ui.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RaceForTheGalaxy extends Application {

	@Override
	public void start(final Stage stage) {
		stage.setTitle("Race For The Galaxy");
		stage.getIcons().addAll(new Image("images/icon/16.png"),
				new Image("/images/icon/32.png"),
				new Image("/images/icon/64.png"),
				new Image("/images/icon/128.png"));
		Scene scene = new Scene(new MainMenu());
		scene.getStylesheets().add("/application.css");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(final String[] args) {
		launch(args);
	}

}
