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
		// Instanciation du modèle modèle
		ArrayList<GameCard> cards =getCards();
		GamePlayer mainPlayer =new Player("Nicolas");
		AbstractGameModel game =new Game("Nouvelle partie", mainPlayer, cards);
		
		// Création du contrôleur
		AbstractControler controler = new GameControler(game);
		// Création de notre fenêtre avec le contrôleur en paramètre
		Board board = new Board(controler);
		// Ajout de la fenêtre comme observer de notre modèle
		game.setObserver(board);
	}
	
	public static ArrayList<GameCard> getCards() {
		ArrayList<GameCard> cards =new ArrayList<>();
		return cards;
	}
}
