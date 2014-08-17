package model;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;
import model.game.IGameCard;
import model.game.IGamePlayer;
import model.game.RunnableGame;

public class GameModel implements Observable {

	// Attributes
	private String name;
	private Observer observer;
	private ArrayList<RunnableGame> games;
	private RunnableGame currentGame;
	private IGamePlayer player;
	private ArrayList<IGameCard> cards;
	private int pvPool;

	// Builder
	public GameModel(String n, IGamePlayer p, ArrayList<IGameCard> cs) {
		// Cr�ation de la partie et ajout du joueur principal
		name = n;
		cards = cs;
		player =p;
	}

	// Impl�mentation du pattern observer
	@Override
	public void setObserver(Observer obs) {
		observer = obs;
	}

	@Override
	public void notifyObserver(String msg) {
		if (msg.equals(RunnableGame.SETUP_OKAY)) {
			// Permettre aux joueux de d�fausser deux cartes
		}
	}

	// Mod�le de la partie
	public void launchGame() {
		// Cr�ation d'une partie
		currentGame =new RunnableGame(this, games.size(), "Nouvelle partie de "+player.getName()+" ("+games.size()+")", player, cards);
		games.add(currentGame);
		
		// Lancement de la partie
		currentGame.run();
	}
}