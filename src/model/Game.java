package model;

import java.util.ArrayList;
import java.util.Collections;

import model.game.AbstractGameModel;
import model.game.GameCard;
import model.game.GamePlayer;

public class Game extends AbstractGameModel {

	// Builder
	public Game(String n, GamePlayer p, ArrayList<GameCard> cs) {
		// Création de la partie et ajout du joueur principal
		name = n;
		cards = cs;
		players.add(p);
	}

	// Implémentation du modèle de jeu
	@Override
	public void notifyObserver(int i) {
		switch (i) {
		case 0: // UPDATE_PV
			ArrayList<Integer> pvs = new ArrayList<>();
			for (GamePlayer p : players)
				pvs.add(p.getScore());
			observer.updatePV(pvs);
			break;
		}
	}

	// Modèle de la partie
	@Override
	public void launchGame() {
		// Points initiaux et mélanges des cartes
		pvPool = 12 * players.size();
		Collections.shuffle(cards);

		// Main, plateau mondes de départ
		int c = 0;
		for (GamePlayer p : players) {
			p.setHand(new Hand());
			p.setBoard(new Board());
			for (int ic = c; ic < cards.size(); ic++) {
				GameCard card = cards.get(ic);
				if (card.isHomeWorld()) {
					p.getBoard().addCard(card);
					cards.remove(card);
					c =ic;
					break;
				}
			}
		}
		
		// Distribution des cartes
		for (GamePlayer p:players)
			draw(p, 6);
	}
	
	public void draw(GamePlayer p, int n) {
		for (int i =0 ; i<n ; i++) {
			GameCard card =cards.get(0);
			p.getHand().addCard(card);
			cards.remove(cards);
		}
	}
}