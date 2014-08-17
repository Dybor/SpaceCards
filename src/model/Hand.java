package model;

import java.util.ArrayList;

import model.drawable.IDrawableCard;
import model.drawable.IDrawableHand;
import model.game.IGameCard;
import model.game.IGameHand;

public class Hand implements IGameHand, IDrawableHand {

	// Attributes
	private ArrayList<IGameCard> cards;
	
	// Builder
	public Hand() {
		cards =new ArrayList<>();
	}

	// GameHand implementation
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

	// DrawableHand implmentation
	@Override
	public ArrayList<IDrawableCard> getCards() {
		ArrayList<IDrawableCard> cs =new ArrayList<>();
		for (IGameCard c:cards)
			cs.add((IDrawableCard)c);
		return cs;
	}

}
