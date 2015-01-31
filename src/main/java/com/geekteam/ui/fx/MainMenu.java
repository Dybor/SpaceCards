package com.geekteam.ui.fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import com.geekteam.ui.Main;

public class MainMenu extends BorderPane {
	public MainMenu() {
		getStyleClass().add("pane");
		setTop(newLogo());
		setCenter(newButtons());
	}

	private VBox newButtons() {

		VBox buttons = new VBox();
		buttons.getStyleClass().add("buttons");

		Button newGame = new Button("Nouvelle Partie".toUpperCase());
		newGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				Main.main(null);
			}
		});
		buttons.getChildren().add(newGame);

		Button exit = new Button("Quitter".toUpperCase());
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				Platform.exit();
			}
		});
		buttons.getChildren().add(exit);
		return buttons;
	}

	private ImageView newLogo() {
		ImageView logo = new ImageView();
		logo.getStyleClass().add("logo");
		return logo;
	}

}
