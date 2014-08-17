package model;

import model.drawable.IDrawableCard;
import model.game.IGameCard;
import model.network.INetworkCard;

public class Card implements IGameCard, IDrawableCard, INetworkCard {

	// Attributes
	private String name;
	private int type;
	private int cost;
	private int scoreValue;
	private boolean isHomeWorld;
	private int goodColor;

	private int imageId;
	private int[] powerIds;
	private String path;
	
	private IGameCard good;
	private boolean hasGood;

	// Builder
	public Card(String stringValue) {
		String[] values = stringValue.split(",");
		name = values[0];
		type = Integer.parseInt(values[1]);
		cost = Integer.parseInt(values[2]);
		scoreValue = Integer.parseInt(values[3]);
		isHomeWorld = Boolean.parseBoolean(values[4]);
		goodColor =Integer.parseInt(values[5]);

		imageId = 1;
		powerIds = new int[] { 0, 0, 0, 0, 0, 0 };
		path = "./src/Cards/card1.png";
		
		good =null;
		hasGood =false;
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
	public int getCost() {
		return cost;
	}

	@Override
	public int getScoreValue() {
		return scoreValue;
	}

	@Override
	public boolean isHomeWorld() {
		return isHomeWorld;
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
	public boolean hasGood() {
		return hasGood;
	}

	// DrawableCard implementation
	@Override
	public int getImageId() {
		return imageId;
	}

	@Override
	public int getPowerId(int i) {
		return powerIds[i];
	}
	
	@Override
	public int getGoodColor() {
		return goodColor;
	}

	// NetowrkCard implementation
	@Override
	public String serialize() {
		String cardToString = name + "," + type + "," + cost + "," + scoreValue
				+ "," + path;
		return cardToString;
	}

}
