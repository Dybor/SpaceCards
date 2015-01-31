package com.geekteam.ui.fx;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class GameBoard extends BorderPane {
	public GameBoard() {
		getStyleClass().add("main-pane");
		setTop(newTop());
	}

	private Node newTop() {
		BorderPane top = new BorderPane();
		top.setLeft(newLogo());
		top.setCenter(newPhases());
		top.setRight(newRoundsCounter());
		return top;
	}

	private FlowPane newPhases() {
		FlowPane pane = new FlowPane();
		pane.getStyleClass().add("phases");
		pane.getChildren().add(newPhase("I"));
		pane.getChildren().add(newPhase("II"));
		pane.getChildren().add(newPhase("III"));
		pane.getChildren().add(newPhase("IV"));
		pane.getChildren().add(newPhase("V"));
		return pane;
	}

	private Label newPhase(final String phase) {
		Label counter = new Label(phase);
		counter.getStyleClass().add("phase");
		return counter;
	}

	private ImageView newLogo() {
		ImageView logo = new ImageView();
		logo.setFitWidth(100);
		logo.setPreserveRatio(true);
		logo.getStyleClass().add("logo");
		return logo;
	}

	private Label newRoundsCounter() {
		Label counter = new Label("round");
		counter.getStyleClass().add("counter");
		return counter;
	}

}
