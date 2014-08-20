package model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.Board;
import model.Card;
import model.Hand;

public class RunnableGame implements Runnable {

	// Attriuts statiques
	public static final String SETUP_OKAY ="La phase de mise en place est terminée";
	
	// Attributs privés
	private String name;
	
	private ArrayList<IGamePlayer> players =new ArrayList<>();
	private ArrayList<IGameCard> cards =new ArrayList<>();
	private int pvPool;
	
	// Builder
	public RunnableGame(String n, IGamePlayer p) {
		name =n;
		players.add(p);
		createCards();
	}
	
	
	// Lancement du thread
	@Override
	public void run() {
		// Mise en place 
		//setup();
		
		// Attendre que les joueurs aient défaussé deux cartes
		//boolean ready =false;
		//while (!ready) {
		//	ready ==
		//}
	}

	// Méthodes privées (phases de jeu)
	private void createCards() {
		BufferedReader is;
		String line=null;
		IGameCard c=null;
		try {
			is =new BufferedReader(new FileReader(new File("./data/cardfiles/cards.txt")));
			while ((line =is.readLine()) !=null) {
				String[] data =line.split("\t");
				int id =Integer.parseInt(data[0]);
				int type =Integer.parseInt(data[1]);
				int subtype =Integer.parseInt(data[2]);
				int cost =Integer.parseInt(data[3]);
				int vp =Integer.parseInt(data[4]);
				String name =data[5];
				int color =Integer.parseInt(data[6]);
				int homeworld =Integer.parseInt(data[7]);
				int n =Integer.parseInt(data[8]);
				for (int i=0 ; i<n ; i++)
					c =new Card(id, type, subtype, cost, vp, name, color, homeworld);
				System.out.println(c.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("A card is not defined correctly :\n"+line);
		}
	}
	
	private void setup() {
		// Points initiaux et mélange des cartes
		pvPool = 12 * players.size();
		Collections.shuffle(cards);

		// Main, plateau mondes de départ
		int c = 0;
		for (IGamePlayer p : players) {
			p.setHand(new Hand());
			p.setBoard(new Board());
			for (int ic = c; ic < cards.size(); ic++) {
				IGameCard card = cards.get(ic);
				if (card.getHomeWorldId() >=0) {
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
		
		// Notifier le modèle
		//model.notifyObserver(SETUP_OKAY);
	}
	
	// Méthodes privées (actions de jeu)
	private void draw(IGamePlayer p, int n) {
		for (int i = 0; i < n; i++) {
			IGameCard card = cards.get(0);
			p.getHand().addCard(card);
			cards.remove(card);
		}
	}
}
