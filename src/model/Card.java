package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import modelInterfaces.DrawableCard;
import modelInterfaces.GameCard;
import modelInterfaces.NetworkCard;

public class Card implements GameCard, DrawableCard, NetworkCard {

	// Attributes
	private String name;
	private String type;
	private int cost;
	private int scoreValue;
	private String path;

	// Builder
	public Card(String stringValue) {
		String[] values = stringValue.split(",");
		name = values[0];
		type = values[1];
		cost = Integer.parseInt(values[2]);
		scoreValue = Integer.parseInt(values[3]);
		path = "./src/Cards/card1.png";
	}

	// GameCard implementation
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
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

	// DrawableCard implementation
	@Override
	public Image getImage() {
		Image im = null;
		try {
			im = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return im;
	}

	@Override
	public String serialize() {
		String cardToString = name + "," + type + "," + cost + "," + scoreValue
				+ "," + path;
		return cardToString;
	}

}
