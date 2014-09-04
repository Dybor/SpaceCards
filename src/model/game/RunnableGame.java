package model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import controler.IControler;
import model.Board;
import model.Card;
import model.Hand;
import model.network.INetworkData;

public class RunnableGame implements Runnable, IGameData, INetworkData {

	// Attributs privés
	private String name;

	private IControler controler;
	private ArrayList<IGamePlayer> players = new ArrayList<>();
	private ArrayList<IGameCard> cards = new ArrayList<>();
	private int pvPool;
	private int rounds;

	private ArrayList<IGamePlayer>[] actions = new ArrayList[5];

	// Builder
	public RunnableGame(String n, IGamePlayer p) {
		name = n;
		players.add(p);
		readCards();
	}

	// IGameData implementation : Getters
	@Override
	public ArrayList<IGamePlayer> getPlayers() {
		return players;
	}

	@Override
	public ArrayList<IGameCard> getRemainingCards() {
		return cards;
	}

	@Override
	public int getRemainingVP() {
		return pvPool;
	}

	// Lancement du thread
	@Override
	public void run() {
		// Mise en place
		setup();

		// Tours de jeu
		/*while (boardsNotComplete() && pvPool > 0) {
			rounds += 1;
			launchRounds();
		}*/

		// Fin de la partie
	}

	public void setController(IControler c) {
		controler =c;
	}
	
	// Méthodes privées (phases de jeu)
	private void readCards() {
		BufferedReader is;
		String line = null;
		IGameCard c = null;
		try {
			is = new BufferedReader(new FileReader(new File(
					"./data/cardfiles/cards.txt")));
			while ((line = is.readLine()) != null) {
				String[] data = line.split("\t");
				int id = Integer.parseInt(data[0]);
				int type = Integer.parseInt(data[1]);
				int subtype = Integer.parseInt(data[2]);
				int cost = Integer.parseInt(data[3]);
				int vp = Integer.parseInt(data[4]);
				String name = data[5];
				int color = Integer.parseInt(data[6]);
				int homeworld = Integer.parseInt(data[7]);
				int n = Integer.parseInt(data[8]);
				for (int i = 0; i < n; i++) {
					c = new Card(id, type, subtype, cost, vp, name, color,
							homeworld);
					cards.add(c);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("A card is not correctly defined: \n" + line);
		}
	}

	private void setup() {
		// Points initiaux et mélange des cartes
		pvPool = 12 * players.size();
		rounds = 0;
		Collections.shuffle(cards);

		// Main, plateau mondes de départ
		int c = 0;
		for (IGamePlayer p : players) {
			p.setHand(new Hand());
			p.setBoard(new Board());
			for (int ic = c; ic < cards.size(); ic++) {
				IGameCard card = cards.get(ic);
				if (card.getHomeWorldId() >= 0) {
					p.getBoard().addCard(card);
					cards.remove(card);
					c = ic;
					break;
				}
			}
		}

		// Distribution des cartes (toutes les cartes sont jouables)
		for (IGamePlayer p : players) {
			draw(p, 6);
			p.setCardsToBeSelectedNumber(2);
			IGameHand h =p.getHand();
			for (int i=0 ; i<h.size() ; i++) {
				h.getCard(i).setPlayable(true);
			}
		}
		controler.notifyView();

		// Les joueur se défaussent de deux cartes chacun
		//while (!playersAreReady())
		//	wait(500, "SETUP : Les joueurs se défaussent de deux cartes.");
		//setPlayersUnready();
	}

	private void launchRounds() {
		// Les joueurs choisissent leurs actions
		while (!playersAreReady())
			wait(500, "LAUNCH_ROUND : Les joueurs choisissent leurs actions.");
		setPlayersUnready();

		// Traitement des actions
		for (int a = 0; a < actions.length; a++) {
			if (actions[a].size() > 0) {
				switch (a) {
				case 0:
					exploreAction();
					break;
				case 1:
					developAction();
					break;
				case 2:
					colonizeAction();
					break;
				case 3:
					consumeAction();
					break;
				case 4:
					produceAction();
				}
			}
		}
	}

	// Méthodes privées
	private void wait(long timeout, String msg) {
		try {
			wait(500);
		} catch (InterruptedException e) {
			System.out.println(msg);
			e.printStackTrace();
		}
	}

	private boolean playersAreReady() {
		boolean ready = true;
		for (IGamePlayer p : players) {
			ready = ready && p.isReady();
		}
		return ready;
	}

	private boolean boardsNotComplete() {
		for (IGamePlayer p : players) {
			if (p.getBoard().size() >= 12)
				return true;
		}
		return false;
	}

	private void setPlayersUnready() {
		for (IGamePlayer p : players) {
			p.setReady(false);
		}
	}

	private void draw(IGamePlayer p, int n) {
		for (int i = 0; i < n; i++) {
			IGameCard card = cards.get(0);
			p.getHand().addCard(card);
			cards.remove(card);
		}
	}

	// Phases de jeu
	private void exploreAction() {
	}

	private void developAction() {
	}

	private void colonizeAction() {
	}

	private void consumeAction() {
	}

	private void produceAction() {
	}

	// IGameData implementation : Fonctions implémentant les actions utilisateurs
	@Override
	public void treatSelectedCard(IGamePlayer p, int id) {
		p.selectCard(id);
		if (p.getSelectedCardsNumber() <p.getCardsToBeSelectedNumber())
			controler.sendMessage("Encore "+(p.getCardsToBeSelectedNumber()-p.getSelectedCardsNumber())+" carte(s) à sélectionner");
		else controler.sendMessage("Deux cartes ont déjà été sélectionnées");
		controler.notifyView();
	}
}
