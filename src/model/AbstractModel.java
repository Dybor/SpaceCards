package model;

import java.util.ArrayList;

import model.game.GamePlayer;
import observer.Observable;
import observer.Observer;

public abstract class AbstractModel implements Observable {

	protected ArrayList<Observer> listObserver = new ArrayList<>();
	protected ArrayList<GamePlayer> listPlayer = new ArrayList<>();

	public abstract void updatePV();

	public void addPlayer(Player p) {
		listPlayer.add(p);
	}

	public void removePlayer(Player p) {
		listPlayer.remove(p);
	}

	// Implémentation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyObserver(int i) {

	}

	public void removeObserver() {
		listObserver = new ArrayList<>();
	}
}