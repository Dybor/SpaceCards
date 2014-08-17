package model;

import java.util.ArrayList;

import model.game.IGameCard;
import model.game.IGameHand;

public class Hand implements IGameHand {

	// Attributes
	private ArrayList<IGameCard> cards;
	
	// Builder
	public Hand() {
		cards =new ArrayList<>();
	}

	// GameHand implementation
	@Override
	public ArrayList<IGameCard> getCards() {
		return cards;
	}

	@Override
	public int size() {
		return cards.size();
	}

	@Override
	public void addCard(IGameCard card) {
		cards.add(card);
	}

	@Override
	public void discardCard(IGameCard card) {
		cards.remove(card);
	}

	@Override
	public boolean isFull() {
		if (cards.size() > 10)
			return true;
		else
			return false;
	}

}
