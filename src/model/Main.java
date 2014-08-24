package model;

import java.util.ArrayList;

import view.Board;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation du modèle modèle
		Player mainPlayer =new Player("Nicolas");
		GameModel gameModel =new GameModel("Nouvelle partie", mainPlayer);
		gameModel.launchGame();
		
		// Création du contrôleur
		GameControler controler = new GameControler(gameModel);
		// Création de notre fenêtre avec le contrôleur en paramètre
		Board board = new Board(controler, gameModel);
		controler.setView(board);
	}
}
