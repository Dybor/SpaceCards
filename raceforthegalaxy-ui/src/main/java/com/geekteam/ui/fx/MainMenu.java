package com.geekteam.ui.fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainMenu extends BorderPane {
	public MainMenu(final RaceForTheGalaxy application) {
		getStyleClass().add("main-pane");
		setTop(newLogo());
		setCenter(newButtons(application));
	}

	private VBox newButtons(final RaceForTheGalaxy application) {

		VBox buttons = new VBox();
		buttons.getStyleClass().add("buttons");

		Button start = newButton("Nouvelle Partie",
				new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				application.startGame();
			}
		});
		buttons.getChildren().add(start);

		Button join = newButton("Rejoindre Partie",
				new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				application.joinGame();
			}
		});
		buttons.getChildren().add(join);

		Button exit = newButton("Quitter", new EventHandler<ActionEvent>() {

			@Override
			public void handle(final ActionEvent event) {
				Platform.exit();
			}
		});
		buttons.getChildren().add(exit);
		return buttons;
	}

	private Button newButton(final String text,
			final EventHandler<ActionEvent> handler) {
		Button button = new Button(text.toUpperCase());
		button.setOnAction(handler);
		button.getStyleClass().add("rich-blue");
		return button;
	}

	private ImageView newLogo() {
		ImageView logo = new ImageView();
		logo.getStyleClass().add("logo");
		return logo;
	}

}
