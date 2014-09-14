package model;

import io.FileReaderManager;
import io.Messenger;

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
import model.game.IGameData;
import model.game.IGameHand;
import model.game.IGamePlayer;
import model.game.GameData;

public class GameModel implements Observable, Controllable {

	// Attributes
	private FileReaderManager reader =new FileReaderManager();
	private Messenger msg;
	
	private long idGame;
	private GameData gameData;
	private IControler controler;
	private Player player;
	
	// Builder
	public GameModel(String n, Player p) {
		msg =new Messenger("GameModel");
		player =p;
		idGame =0;
	}

	// Implémentation du pattern Observable pour la vue
	@Override
	public int getPlayerViewOwnerIndex() {
		return gameData.getPlayers().indexOf((IGamePlayer)player);
	}

	@Override
	public ArrayList<IDrawablePlayer> getPlayers() {
		ArrayList<IDrawablePlayer> newList =new ArrayList<>();
		for (IGamePlayer p:gameData.getPlayers())
			newList.add((IDrawablePlayer)p);
		return newList;
	}

	@Override
	public ArrayList<IDrawableCard> getStack() {
		ArrayList<IDrawableCard> newList =new ArrayList<>();
		for (IGameCard c:gameData.getStack())
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
		player.setValidate(player.getSelectedCardsNumber() <=player.getCardsToBeSelectedNumber()); 
		controler.notifyView();
	}

	@Override
	public void setController(IControler c) {
		controler =c;
	}

	@Override
	public void validateAction() {
		// Le joueur est prêt
		player.setReady(true);
		
		// Traitement de l'action du joueur
		if (gameData.getPhase() ==IGameData.SETUP_PHASE) {
			setupValidation();
		} else if (gameData.getPhase() ==IGameData.EXPLORE_5_PHASE) {
			explore();
		} else if (gameData.getPhase() ==IGameData.EXPLORE_1_1_PHASE) {
			explore();
		} else if (gameData.getPhase() ==IGameData.DEVELOP_PHASE) {
			develop();
		} else if (gameData.getPhase() ==IGameData.SETTLE_PHASE) {
			settle();
		} else if (gameData.getPhase() ==IGameData.CONSUME_vp2_PHASE) {
			consume();
		} else if (gameData.getPhase() ==IGameData.SELL_PHASE) {
			consume();
		} else if (gameData.getPhase() ==IGameData.PRODUCE_PHASE) {
			produce();
		}
		
		// Mise à jour de la vue
		controler.notifyView();
	}
	
	// Méthodes de la partie
	@Override
	public void launchGame() {
		gameData =new GameData("Nouvelle partie de "+player.getName()+" ("+idGame+")", player);
		reader.open("./data/cardfiles/cards.txt");
		gameData.setCards(reader.readCards());
		setup();

		// Tours de jeu
		/*while (boardsNotComplete() && pvPool > 0) {
			rounds += 1;
			launchRounds();
		}*/

		// Fin de la partie
	}
	
	private void setup() {
		// Points initiaux et mélange des cartes
		gameData.initializeParameters();

		// Main, plateau mondes de départ
		int c = 0;
		for (IGamePlayer p : gameData.getPlayers()) {
			p.setHand(new Hand());
			p.setBoard(new Board());
			for (int ic = c; ic < gameData.getStack().size(); ic++) {
				IGameCard card = gameData.getStack().get(ic);
				if (card.getHomeWorldId() >= 0) {
					p.getBoard().addCard(card);
					gameData.getStack().remove(card);
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
		for (int i = 0; i < n; i++)
			p.getHand().addCard(gameData.getFirstCard());
	}
	
	private void setupValidation() {
		IGameHand h =player.getHand();
		ArrayList<IGameCard> dCards =new ArrayList<IGameCard>();
		for (int i =0 ; i<h.size() ; i++) {
			IGameCard c =h.getCard(i);
			if (c.isSelected()) dCards.add(c);
		}
		for (IGameCard c:dCards) {
			h.discardsCard(c);
			gameData.addDiscardedCard(c);
		}
	}
	
	private void explore() {
		
	}
	
	private void develop() {
		
	}
	
	private void settle() {
		
	}
	
	private void consume() {
		
	}
	
	private void produce() {
		
	}
}