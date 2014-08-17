package model;

import java.util.ArrayList;

import model.game.AbstractGameModel;
import model.game.IGameCard;
import model.game.IGamePlayer;
import view.Board;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation du mod�le mod�le
		ArrayList<IGameCard> cards =getCards();
		IGamePlayer mainPlayer =new Player("Nicolas");
		AbstractGameModel game =new Game("Nouvelle partie", mainPlayer, cards);
		
		// Cr�ation du contr�leur
		GameControler controler = new GameControler(game);
		// Cr�ation de notre fen�tre avec le contr�leur en param�tre
		Board board = new Board(controler);
		// Ajout de la fen�tre comme observer de notre mod�le
		game.setObserver(board);
	}
	
	public static ArrayList<IGameCard> getCards() {
		ArrayList<IGameCard> cards =new ArrayList<>();
		return cards;
	}
}
