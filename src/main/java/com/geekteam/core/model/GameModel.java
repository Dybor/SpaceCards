package com.geekteam.core.model;

import java.util.ArrayList;

import com.geekteam.core.controler.Controllable;
import com.geekteam.core.controler.IControler;
import com.geekteam.core.controler.Observable;
import com.geekteam.core.io.FileReaderManager;
import com.geekteam.core.io.Messenger;
import com.geekteam.core.model.drawable.IDrawableCard;
import com.geekteam.core.model.drawable.IDrawablePlayer;
import com.geekteam.core.model.game.IGameCard;
import com.geekteam.core.model.game.IGameData;
import com.geekteam.core.model.game.IGameHand;
import com.geekteam.core.model.game.IGamePlayer;

public class GameModel implements Observable, Controllable {

	// Attributes
	private final FileReaderManager reader = new FileReaderManager();
	private final Messenger msg;

	private final long idGame;
	private GameData gameData;
	private IControler controler;
	private final Player player;

	// Builder
	public GameModel(final String n, final Player p) {
		msg = new Messenger("GameModel");
		player = p;
		idGame = 0;
	}

	// Impl�mentation du pattern Observable pour la vue
	@Override
	public int getPlayerViewOwnerIndex() {
		return gameData.getPlayers().indexOf(player);
	}

	@Override
	public ArrayList<IDrawablePlayer> getPlayers() {
		ArrayList<IDrawablePlayer> newList = new ArrayList<>();
		for (IGamePlayer p : gameData.getPlayers()) {
			newList.add((IDrawablePlayer) p);
		}
		return newList;
	}

	@Override
	public ArrayList<IDrawableCard> getStack() {
		ArrayList<IDrawableCard> newList = new ArrayList<>();
		for (IGameCard c : gameData.getStack()) {
			newList.add((IDrawableCard) c);
		}
		return newList;
	}

	@Override
	public int getRemainingVP() {
		return gameData.getRemainingVP();
	}

	@Override
	public boolean isActionChoicePhase() {
		return gameData.getPhase() == IGameData.ACTION_CHOICE_PHASE;
	}

	// Impl�mentation du pattern Controllable pour le controleur
	@Override
	public void setController(final IControler c) {
		controler = c;
	}

	@Override
	public void treatSelectedCard(final int id) {
		if (gameData.getPhase() != IGameData.ACTION_CHOICE_PHASE) {
			player.selectCard(id);
			player.setValidate(player.getSelectedCardsNumber() == player
					.getCardsToBeSelectedNumber());
			controler.notifyView();
		}
	}

	@Override
	public void validateAction(final int i) {
		// Traitement de l'action du joueur
		if (gameData.getPhase() == IGameData.SETUP_PHASE) {
			if (gameData.getPhase() == IGameData.SETUP_PHASE) {
				setupValidation();
			}
		} else {
			gameData.setPhase(i);
			if (gameData.getPhase() == IGameData.EXPLORE_5_PHASE) {
				explore();
			} else if (gameData.getPhase() == IGameData.EXPLORE_1_1_PHASE) {
				explore();
			} else if (gameData.getPhase() == IGameData.DEVELOP_PHASE) {
				develop();
			} else if (gameData.getPhase() == IGameData.SETTLE_PHASE) {
				settle();
			} else if (gameData.getPhase() == IGameData.CONSUME_vp2_PHASE) {
				consume();
			} else if (gameData.getPhase() == IGameData.SELL_PHASE) {
				consume();
			} else if (gameData.getPhase() == IGameData.PRODUCE_PHASE) {
				produce();
			}
		}

		// Mise � jour de la vue
		controler.notifyView();
	}

	// M�thodes de la partie
	@Override
	public void launchGame() {
		gameData = new GameData("Nouvelle partie de " + player.getName() + " ("
				+ idGame + ")", player);
		reader.open("./cardfiles/cards.txt");
		gameData.setCards(reader.readCards());
		setup();

		// Tours de jeu
		/*
		 * while (boardsNotComplete() && pvPool > 0) { rounds += 1;
		 * launchRounds(); }
		 */

		// Fin de la partie
	}

	private void setup() {
		// Points initiaux et m�lange des cartes
		gameData.initializeParameters();

		// Main, plateau mondes de d�part
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
			IGameHand h = p.getHand();
			for (int i = 0; i < h.size(); i++) {
				h.getCard(i).setPlayable(true);
			}
		}
		controler.notifyView();
	}

	// Actions de jeu
	private void draw(final IGamePlayer p, final int n) {
		for (int i = 0; i < n; i++) {
			p.getHand().addCard(gameData.getFirstCard());
		}
	}

	private void setupValidation() {
		IGameHand h = player.getHand();
		ArrayList<IGameCard> dCards = new ArrayList<IGameCard>();
		for (int i = 0; i < h.size(); i++) {
			IGameCard c = h.getCard(i);
			if (c.isSelected()) {
				dCards.add(c);
			}
		}
		for (IGameCard c : dCards) {
			h.discardsCard(c);
			gameData.addDiscardedCard(c);
		}
		gameData.setPhase(IGameData.ACTION_CHOICE_PHASE);
	}

	private void explore() {
		msg.sendMessage("Explorer");
	}

	private void develop() {
		msg.sendMessage("D�velopper");
	}

	private void settle() {
		msg.sendMessage("Coloniser");
	}

	private void consume() {
		msg.sendMessage("Consommer");
	}

	private void produce() {
		msg.sendMessage("Produire");
	}

}