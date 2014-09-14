package model.game;

import java.util.ArrayList;
import java.util.Collections;

import model.network.INetworkData;

public class GameData implements IGameData, INetworkData {

	// Attributes
	private String name;

	private ArrayList<IGamePlayer> players = new ArrayList<>();
	private ArrayList<IGameCard> stack = new ArrayList<>();
	private ArrayList<IGameCard> discardedCards = new ArrayList<>();
	private int vpPool;
	private int rounds;
	private int currentPhase;

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
		return stack;
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
		IGameCard first =stack.get(0);
		stack.remove(0);
		return first;
	}

	// IGameData implementation : Setters
	@Override
	public void initializeParameters() {
		vpPool = 12*players.size();
		Collections.shuffle(stack);
		rounds =0;
	}
	
	@Override
	public void setCards(ArrayList<IGameCard> cs) {
		stack =cs;
	}
	
	@Override
	public void setPlayersUnready() {
		for (IGamePlayer p : players) p.setReady(false);
	}

	@Override
	public int getRounds() {
		return rounds;
	}

	@Override
	public int getPhase() {
		return currentPhase;
	}

	@Override
	public void addDiscardedCard(IGameCard c) {
		discardedCards.add(c);
	}
}
