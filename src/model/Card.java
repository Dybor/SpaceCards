package model;

import model.drawable.DrawableCard;
import model.game.GameCard;
import model.network.NetworkCard;

public class Card implements GameCard, DrawableCard, NetworkCard {

	// Attributes
	private String name;
	private int type;
	private int cost;
	private int scoreValue;
	private boolean isHomeWorld;

	private int imageId;
	private int[] powerIds;
	private String path;
	
	private GameCard good;
	private boolean hasGood;

	// Builder
	public Card(String stringValue) {
		String[] values = stringValue.split(",");
		name = values[0];
		type = Integer.parseInt(values[1]);
		cost = Integer.parseInt(values[2]);
		scoreValue = Integer.parseInt(values[3]);
		isHomeWorld = Boolean.parseBoolean(values[4]);

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
	public void produceGood(GameCard c) {
		good = c;
		hasGood =true;
	}
	
	@Override
	public GameCard consumeGood() {
		GameCard oldGood =good;
		good =null;
		hasGood =false;
		return oldGood;
	}

	@Override
	public boolean hasGood() {
		return hasGood;
	}

	// NetowrkCard implementation
	@Override
	public String serialize() {
		String cardToString = name + "," + type + "," + cost + "," + scoreValue
				+ "," + path;
		return cardToString;
	}

}
