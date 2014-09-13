package model.game;

import java.util.ArrayList;
import java.util.Collections;

import model.network.INetworkData;

public class GameData implements IGameData, INetworkData {

	// Attributes
	private String name;

	private ArrayList<IGamePlayer> players = new ArrayList<>();
	private ArrayList<IGameCard> cards = new ArrayList<>();
	private int vpPool;
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
	public ArrayList<IGameCard> getStack() {
		return cards;
	}

	@Override
	public int getRemainingVP() {
		return vpPool;
	}
	
	@Override
	public boolean playersAreReady() {
		boolean ready = true;
		for (IGamePlayer p : players) ready = ready && p.isReady();
		return ready;
	}

	@Override
	public boolean boardsComplete() {
		for (IGamePlayer p : players)
			if (p.getBoard().size() >= 12)
				return true;
		return false;
	}

	@Override
	public IGameCard getFirstCard() {
		IGameCard first =cards.get(0);
		cards.remove(0);
		return first;
	}

	// IGameData implementation : Setters
	@Override
	public void initializeParameters() {
		vpPool = 12*players.size();
		Collections.shuffle(cards);
		rounds =0;
	}
	
	@Override
	public void setCards(ArrayList<IGameCard> cs) {
		cards =cs;
	}
	
	@Override
	public void setPlayersUnready() {
		for (IGamePlayer p : players) p.setReady(false);
	}
}
