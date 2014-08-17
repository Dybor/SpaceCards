package model;

import java.util.ArrayList;

import model.game.GameBoard;
import model.game.GameCard;

public class Board implements GameBoard {

	// Attributes
	private ArrayList<GameCard> cards;

	// Builder
	public Board() {
		cards = new ArrayList<>();
	}

	// GameBoard implementation
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
	public void removeCard(GameCard card) {
		cards.remove(cards);
	}

	@Override
	public boolean isFull() {
		if (cards.size() >= 12)
			return true;
		else
			return false;
	}
}
