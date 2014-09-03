package model;

import view.Board;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation du mod�le mod�le
		Player mainPlayer =new Player("Nicolas");
		GameModel gameModel =new GameModel("Nouvelle partie", mainPlayer);
		
		// Cr�ation du contr�leur
		GameControler controler = new GameControler(gameModel);
		// Cr�ation de notre fen�tre avec le contr�leur en param�tre
		Board board = new Board(controler, gameModel);
		controler.setView(board);
	}
}
