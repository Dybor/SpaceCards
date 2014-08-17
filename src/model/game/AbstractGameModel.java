package model.game;

import java.util.ArrayList;

import model.Player;
import observer.Observable;
import observer.Observer;

public abstract class AbstractGameModel implements Observable {

	// Attributes
	protected String name;
	protected Observer observer;
	protected ArrayList<GamePlayer> players = new ArrayList<>();
	protected ArrayList<GameCard> cards;
	protected int pvPool;

	// Implémentation du pattern observer
	public void setObserver(Observer obs) {
		observer = obs;
	}

	public void notifyObserver(int i) {
	}

	// Méthodes abstraites a implementer
	public abstract void launchGame();

	// Public methods
	public void addPlayer(Player p) {
		players.add(p);
	}

	public void removePlayer(Player p) {
		players.remove(p);
	}

}