package model;

import view.Board;
import controler.AbstractControler;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation de notre mod�le
		AbstractModel game = new Game();
		// Cr�ation du contr�leur
		AbstractControler controler = new GameControler(game);
		// Cr�ation de notre fen�tre avec le contr�leur en param�tre
		Board board = new Board(controler);
		// Ajout de la fen�tre comme observer de notre mod�le
		game.addObserver(board);
	}
}
