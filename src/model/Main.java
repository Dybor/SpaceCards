package model;

import java.util.ArrayList;

import model.game.AbstractGameModel;
import model.game.IGameCard;
import model.game.IGamePlayer;
import view.Board;
import controler.GameControler;

public class Main {

	public static void main(String[] args) {
		// Instanciation du modèle modèle
		ArrayList<IGameCard> cards =getCards();
		IGamePlayer mainPlayer =new Player("Nicolas");
		AbstractGameModel game =new Game("Nouvelle partie", mainPlayer, cards);
		
		// Création du contrôleur
		GameControler controler = new GameControler(game);
		// Création de notre fenêtre avec le contrôleur en paramètre
		Board board = new Board(controler);
		// Ajout de la fenêtre comme observer de notre modèle
		game.setObserver(board);
	}
	
	public static ArrayList<IGameCard> getCards() {
		ArrayList<IGameCard> cards =new ArrayList<>();
		return cards;
	}
}
