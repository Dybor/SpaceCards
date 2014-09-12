package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import controler.Controllable;
import controler.IControler;
import controler.Observable;
import model.drawable.IDrawableCard;
import model.drawable.IDrawablePlayer;
import model.game.IGameCard;
import model.game.IGameHand;
import model.game.IGamePlayer;
import model.game.GameData;

public class GameModel implements Observable, Controllable {

	// Attributes
	private long idGame;
	private GameData gameData;
	private IControler controler;
	private Player player;
	
	// Builder
	public GameModel(String n, Player p) {
		// Création de la partie et ajout du joueur principal
		player =p;
		idGame =0;
	}

	// Implémentation du pattern Observable pour la vue
	@Override
	public int getPlayerViewOwnerIndex() {
		return 0;
	}

	@Override
	public ArrayList<IDrawablePlayer> getPlayers() {
		ArrayList<IDrawablePlayer> newList =new ArrayList<>();
		for (IGamePlayer p:gameData.getPlayers())
			newList.add((IDrawablePlayer)p);
		return newList;
	}

	@Override
	public ArrayList<IDrawableCard> getRemainingCards() {
		ArrayList<IDrawableCard> newList =new ArrayList<>();
		for (IGameCard c:gameData.getRemainingCards())
			newList.add((IDrawableCard)c);
		return newList;
	}

	@Override
	public int getRemainingVP() {
		return gameData.getRemainingVP();
	}

	// Implémentation du pattern Controllable pour le controleur
	@Override
	public void treatSelectedCard(int id) {
		player.selectCard(id);
		controler.notifyView();
	}

	@Override
	public void setController(IControler c) {
		controler =c;
	}

	@Override
	public void validateAction() {
		player.setReady(true);
	}
	
	// Méthodes de la partie
	@Override
	public void launchGame() {
		gameData =new GameData("Nouvelle partie de "+player.getName()+" ("+idGame+")", player);
		readCards();
		setup();

		// Tours de jeu
		/*while (boardsNotComplete() && pvPool > 0) {
			rounds += 1;
			launchRounds();
		}*/

		// Fin de la partie
	}
	
	private void readCards() {
		BufferedReader is;
		String line = null;
		IGameCard c = null;
		ArrayList<IGameCard> cards =new ArrayList<>();
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
			gameData.setCards(cards);
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
		gameData.initializeParameters();

		// Main, plateau mondes de départ
		int c = 0;
		for (IGamePlayer p : gameData.getPlayers()) {
			p.setHand(new Hand());
			p.setBoard(new Board());
			for (int ic = c; ic < gameData.getCards().size(); ic++) {
				IGameCard card = gameData.getCards().get(ic);
				if (card.getHomeWorldId() >= 0) {
					p.getBoard().addCard(card);
					gameData.getCards().remove(card);
					c = ic;
					break;
				}
			}
		}

		// Distribution des cartes (toutes les cartes sont jouables)
		for (IGamePlayer p : gameData.getPlayers()) {
			draw(p, 6);
			p.setCardsToBeSelectedNumber(2);
			IGameHand h =p.getHand();
			for (int i=0 ; i<h.size() ; i++) {
				h.getCard(i).setPlayable(true);
			}
		}
		controler.notifyView();
	}
	
	// Actions de jeu
	private void draw(IGamePlayer p, int n) {
		for (int i = 0; i < n; i++) {
			IGameCard card = gameData.getCards().get(0);
			p.getHand().addCard(card);
			gameData.getCards().remove(card);
		}
	}
}