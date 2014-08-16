package model;

import view.Board;
import controler.AbstractControler;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation de notre modèle
		AbstractModel game = new Game();
		// Création du contrôleur
		AbstractControler controler = new GameControler(game);
		// Création de notre fenêtre avec le contrôleur en paramètre
		Board board = new Board(controler);
		// Ajout de la fenêtre comme observer de notre modèle
		game.addObserver(board);
	}
}
