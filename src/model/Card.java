package model;

import model.drawable.IDrawableCard;
import model.game.IGameCard;

public class Card implements IGameCard, IDrawableCard {

	// Attributes
	private int id;
	private int type;
	private int subType;
	private int cost;
	private int victoryPoints;
	private String name;
	private int goodColor;
	private int homeWorld;
	
	private int[] powerIds;
	private IGameCard good;
	private boolean hasGood;
	
	private boolean playable;

	private String path;
	
	// Builder
	public Card(int i, int t, int st, int co, int v, String n, int c, int h) {
		id =i;
		type =t;
		subType =st;
		cost =co;
		victoryPoints =v;
		name =n;
		goodColor =c;
		homeWorld =h;
		path ="./data/images/cards/base/card_"+id+".jpg";
		
		powerIds = new int[] { 0, 0, 0, 0, 0, 0 };
		good =null;
		hasGood =false;
		
		playable =false;
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
	public void produceGood(IGameCard c) {
		good = c;
		hasGood =true;
	}
	
	@Override
	public IGameCard consumeGood() {
		IGameCard oldGood =good;
		good =null;
		hasGood =false;
		return oldGood;
	}
	
	@Override
	public IGameCard getGood() {
		return good;
	}
	
	@Override
	public void setPlayable(boolean p) {
		playable =p;
	}

	// DrawableCard implementation
	@Override
	public int getImageId() {
		return id;
	}

	@Override
	public int getPowerId(int i) {
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
	
	@Override
	public boolean isPlayable() {
		return playable;
	}

	// Public methods
	@Override
	public String toString() {
		return getName()+"("+getCost()+","+getScoreValue()+","+getHomeWorldId()+")";
	}
}
