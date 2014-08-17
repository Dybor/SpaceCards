package model;

import java.util.ArrayList;
import java.util.Collections;

import observer.Observable;
import observer.Observer;
import model.game.IGameCard;
import model.game.IGamePlayer;

public class GameModel implements Observable {

	// Attributes
	private String name;
	private Observer observer;
	private ArrayList<IGamePlayer> players = new ArrayList<>();
	private ArrayList<IGameCard> cards;
	private int pvPool;

	// Builder
	public GameModel(String n, IGamePlayer p, ArrayList<IGameCard> cs) {
		// Création de la partie et ajout du joueur principal
		name = n;
		cards = cs;
		players.add(p);
	}

	// Implémentation du pattern observer
	public void setObserver(Observer obs) {
		observer = obs;
	}

	// Public methods
	public void addPlayer(Player p) {
		players.add(p);
	}

	public void removePlayer(Player p) {
		players.remove(p);
	}

	// Implémentation du modèle de jeu
	@Override
	public void notifyObserver(int i) {
		switch (i) {
		case 0: // UPDATE_PV
			ArrayList<Integer> pvs = new ArrayList<>();
			for (IGamePlayer p : players)
				pvs.add(p.getScore());
			observer.updatePV(pvs);
			break;
		}
	}

	// Modèle de la partie
	public void launchGame() {
		// Points initiaux et mélanges des cartes
		pvPool = 12 * players.size();
		Collections.shuffle(cards);

		// Main, plateau mondes de départ
		int c = 0;
		for (IGamePlayer p : players) {
			p.setHand(new Hand());
			p.setBoard(new Board());
			for (int ic = c; ic < cards.size(); ic++) {
				IGameCard card = cards.get(ic);
				if (card.isHomeWorld()) {
					p.getBoard().addCard(card);
					cards.remove(card);
					c = ic;
					break;
				}
			}
		}

		// Distribution des cartes
		for (IGamePlayer p : players)
			draw(p, 6);
	}

	public void draw(IGamePlayer p, int n) {
		for (int i = 0; i < n; i++) {
			IGameCard card = cards.get(0);
			p.getHand().addCard(card);
			cards.remove(cards);
		}
	}
}