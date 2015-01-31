package com.geekteam.core.model;

import java.util.ArrayList;
import java.util.Collections;

import com.geekteam.core.model.game.IGameCard;
import com.geekteam.core.model.game.IGameData;
import com.geekteam.core.model.game.IGamePlayer;
import com.geekteam.core.model.network.INetworkData;

public class GameData implements IGameData, INetworkData {

	// Attributes
	private final String name;

	private final ArrayList<IGamePlayer> players = new ArrayList<>();
	private ArrayList<IGameCard> stack = new ArrayList<>();
	private final ArrayList<IGameCard> discardedCards = new ArrayList<>();
	private int vpPool;
	private int rounds;
	private int currentPhase;

	// Builder
	public GameData(final String n, final IGamePlayer p) {
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
		for (IGamePlayer p : players) {
			ready = ready && p.isReady();
		}
		return ready;
	}

	@Override
	public boolean boardsComplete() {
		for (IGamePlayer p : players) {
			if (p.getBoard().size() >= 12) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IGameCard getFirstCard() {
		IGameCard first = stack.get(0);
		stack.remove(0);
		return first;
	}

	// IGameData implementation : Setters
	@Override
	public void initializeParameters() {
		vpPool = 12 * players.size();
		Collections.shuffle(stack);
		currentPhase = IGameData.SETUP_PHASE;
		rounds = 0;
	}

	@Override
	public void setCards(final ArrayList<IGameCard> cs) {
		stack = cs;
	}

	@Override
	public void setPlayersUnready() {
		for (IGamePlayer p : players) {
			p.setReady(false);
		}
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
	public void addDiscardedCard(final IGameCard c) {
		discardedCards.add(c);
	}

	@Override
	public void setPhase(final int phase) {
		currentPhase = phase;
	}
}
