package model;

import java.util.ArrayList;

import model.game.IGameBoard;
import model.game.IGameCard;

public class Board implements IGameBoard {

	// Attributes
	private ArrayList<IGameCard> cards;

	// Builder
	public Board() {
		cards = new ArrayList<>();
	}

	// GameBoard implementation
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
	public void removeCard(IGameCard card) {
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
