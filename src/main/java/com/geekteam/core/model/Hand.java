package com.geekteam.core.model;

import java.util.ArrayList;

import com.geekteam.core.model.drawable.IDrawableCard;
import com.geekteam.core.model.drawable.IDrawableHand;
import com.geekteam.core.model.game.IGameCard;
import com.geekteam.core.model.game.IGameHand;

public class Hand implements IGameHand, IDrawableHand {

	// Attributes
	private ArrayList<IGameCard> cards;
	
	// Builder
	public Hand() {
		cards =new ArrayList<>();
	}

	// GameHand implementation : Getters and setters
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
	public void discardsCard(IGameCard card) {
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

	// GameCard implementation : actions
	@Override
	public void selectCard(int id) {
		// Recherche de la carte
		for (IGameCard c:cards) {
			if (c.getId() ==id) {
				c.setSelected(!c.isSelected());
				break;
			}
		}
	}
}
