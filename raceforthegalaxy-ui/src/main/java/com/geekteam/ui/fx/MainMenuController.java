package com.geekteam.ui.fx;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenuController {
	@FXML
	public void joinGame() {
		RaceForTheGalaxy.getInstance().joinGame();
	}

	@FXML
	public void exit() {
		Platform.exit();
	}

	@FXML
	public void startGame() {
		RaceForTheGalaxy.getInstance().startGame();
	}
}
