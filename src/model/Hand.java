package model;

import java.util.ArrayList;

import model.game.GameCard;
import model.game.GameHand;

public class Hand implements GameHand {

	// Attributes
	private ArrayList<GameCard> cards;
	
	// Builder
	public Hand() {
		cards =new ArrayList<>();
	}

	// GameHand implementation
	@Override
	public ArrayList<GameCard> getCards() {
		return cards;
	}

	@Override
	public int size() {
		return cards.size();
	}

	@Override
	public void addCard(GameCard card) {
		cards.add(card);
	}

	@Override
	public void discardCard(GameCard card) {
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
