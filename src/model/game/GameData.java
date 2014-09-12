package model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.Card;
import model.network.INetworkData;
import controler.IControler;

public class GameData implements IGameData, INetworkData {

	// Attributs privés
	private String name;

	private ArrayList<IGamePlayer> players = new ArrayList<>();
	private ArrayList<IGameCard> cards = new ArrayList<>();
	private int pvPool;
	private int rounds;

	// Builder
	public GameData(String n, IGamePlayer p) {
		name = n;
		players.add(p);
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
	
	@Override
	public boolean playersAreReady() {
		boolean ready = true;
		for (IGamePlayer p : players) ready = ready && p.isReady();
		return ready;
	}

	@Override
	public boolean boardsNotComplete() {
		for (IGamePlayer p : players)
			if (p.getBoard().size() >= 12)
				return true;
		return false;
	}

	@Override
	public ArrayList<IGameCard> getCards() {
		return cards;
	}

	// IGameData implementation : Setters
	@Override
	public void setPlayersUnready() {
		for (IGamePlayer p : players) p.setReady(false);
	}
	
	@Override
	public void initializeParameters() {
		pvPool = 12*players.size();
		Collections.shuffle(cards);
		rounds =0;
	}
	
	@Override
	public void setCards(ArrayList<IGameCard> cs) {
		cards =cs;
	}
}
