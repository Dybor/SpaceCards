package com.geekteam.core.model;

import com.geekteam.core.model.drawable.IDrawableCard;
import com.geekteam.core.model.game.IGameCard;

public class Card implements IGameCard, IDrawableCard {

	// Attributes
	private final int id;
	private final int type;
	private final int subType;
	private final int cost;
	private final int victoryPoints;
	private final String name;
	private final int goodColor;
	private final int homeWorld;

	private final int[] powerIds;
	private IGameCard good;
	private boolean hasGood;

	private final String path;

	// Etats de la carte
	private boolean playable = false;
	private boolean selected = false;
	private boolean discarded = false;
	private boolean drawed = false;

	// Builder
	public Card(final int i, final int t, final int st, final int co,
			final int v, final String n, final int c, final int h) {
		id = i;
		type = t;
		subType = st;
		cost = co;
		victoryPoints = v;
		name = n;
		goodColor = c;
		homeWorld = h;
		path = "/images/cards/base/card_" + id + ".jpg";

		powerIds = new int[] { 0, 0, 0, 0, 0, 0 };
		good = null;
		hasGood = false;
	}

	// GameCard implementation
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public int getSubType() {
		return subType;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public int getScoreValue() {
		return victoryPoints;
	}

	@Override
	public int getHomeWorldId() {
		return homeWorld;
	}

	@Override
	public int getGoodType() {
		return goodColor;
	}

	@Override
	public void produceGood(final IGameCard c) {
		good = c;
		hasGood = true;
	}

	@Override
	public IGameCard consumeGood() {
		IGameCard oldGood = good;
		good = null;
		hasGood = false;
		return oldGood;
	}

	@Override
	public IGameCard getGood() {
		return good;
	}

	@Override
	public int getId() {
		return id;
	}

	// DrawableCard implementation
	@Override
	public int getImageId() {
		return id;
	}

	@Override
	public int getPowerId(final int i) {
		return powerIds[i];
	}

	@Override
	public int getGoodColor() {
		return getGoodType();
	}

	@Override
	public String getImagePath() {
		return path;
	}

	@Override
	public boolean hasGood() {
		return hasGood;
	}

	// Public methods
	@Override
	public String toString() {
		return getName() + "(" + getCost() + "," + getScoreValue() + ","
				+ getHomeWorldId() + ")";
	}

	// Actions
	@Override
	public void setPlayable(final boolean p) {
		playable = p;
	}

	@Override
	public void setSelected(final boolean s) {
		selected = s;
	}

	@Override
	public void setDiscarded(final boolean d) {
		discarded = d;
	}

	@Override
	public void setDrawed(final boolean d) {
		drawed = d;
	}

	@Override
	public boolean isDiscarded() {
		return discarded;
	}

	@Override
	public boolean isPlayable() {
		return playable;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean isDrawed() {
		return drawed;
	}
}
