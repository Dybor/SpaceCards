package model.game;

import java.util.ArrayList;
import java.util.Collections;

import observer.Observable;
import model.Board;
import model.Hand;

public class RunnableGame implements Runnable {

	// Attriuts statiques
	public static final String SETUP_OKAY ="La phase de mise en place est termin�e";
	
	// Attributs priv�s
	private Observable model;
	private int id;
	private String name;
	
	private ArrayList<IGamePlayer> players =new ArrayList<>();
	private ArrayList<IGameCard> cards =new ArrayList<>();
	private int pvPool;
	
	// Builder
	public RunnableGame(Observable m, int i, String n, IGamePlayer p, ArrayList<IGameCard> cs) {
		model =m;
		id =i;
		name =n;
		players.add(p);
		cards =cs;
	}
	
	
	// Lancement du thread
	@Override
	public void run() {
		// Mise en place 
		setup();
		
		// Attendre que les joueurs aient d�fauss� deux cartes
		boolean ready =false;
		//while (!ready) {
		//	ready ==
		//}
	}

	// M�thodes priv�es (phases de jeu)
	private void setup() {
		// Points initiaux et m�lange des cartes
		pvPool = 12 * players.size();
		Collections.shuffle(cards);

		// Main, plateau mondes de d�part
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
		
		// Notifier le mod�le
		model.notifyObserver(SETUP_OKAY);
	}
	
	// M�thodes priv�es (actions de jeu)
	private void draw(IGamePlayer p, int n) {
		for (int i = 0; i < n; i++) {
			IGameCard card = cards.get(0);
			p.getHand().addCard(card);
			cards.remove(cards);
		}
	}
}
