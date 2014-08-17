package model;

import java.util.ArrayList;

import model.game.AbstractGameModel;
import model.game.GameCard;
import model.game.GamePlayer;
import view.Board;
import controler.AbstractControler;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation du mod�le mod�le
		ArrayList<GameCard> cards =getCards();
		GamePlayer mainPlayer =new Player("Nicolas");
		AbstractGameModel game =new Game("Nouvelle partie", mainPlayer, cards);
		
		// Cr�ation du contr�leur
		AbstractControler controler = new GameControler(game);
		// Cr�ation de notre fen�tre avec le contr�leur en param�tre
		Board board = new Board(controler);
		// Ajout de la fen�tre comme observer de notre mod�le
		game.setObserver(board);
	}
	
	public static ArrayList<GameCard> getCards() {
		ArrayList<GameCard> cards =new ArrayList<>();
		return cards;
	}
}
