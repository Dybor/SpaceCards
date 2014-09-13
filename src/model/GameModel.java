package model;

import io.FileReaderManager;

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
	private FileReaderManager reader =new FileReaderManager();
	
	private long idGame;
	private GameData gameData;
	private IControler controler;
	private Player player;
	
	// Builder
	public GameModel(String n, Player p) {
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
		player.setReady(true);
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
}