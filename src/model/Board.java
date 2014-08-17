package model;

import java.util.ArrayList;

import model.drawable.IDrawableBoard;
import model.drawable.IDrawableCard;
import model.game.IGameBoard;
import model.game.IGameCard;

public class Board implements IGameBoard, IDrawableBoard {

	// Attributes
	private ArrayList<IGameCard> cards;

	// Builder
	public Board() {
		cards = new ArrayList<>();
	}

	// GameBoard implementation
	@Override
	public IGameCard getCard(int i) {
		return cards.get(i);
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

	// IDrawableBoard implementation
	@Override
	public ArrayList<IDrawableCard> getCards() {
		ArrayList<IDrawableCard> cs =new ArrayList<>();
		for (IGameCard c:cards)
			cs.add((IDrawableCard)c);
		return cs;
	}
}
