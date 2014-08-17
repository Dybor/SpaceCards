package model;

import java.util.ArrayList;

import model.game.GameBoard;
import model.game.GameCard;
import model.game.GamePlayer;

public class Player implements GamePlayer {

	// Attributes
	private String name;
	private int score;
	private ArrayList<GameCard> hand;
	private GameBoard board;

	// Builder
	public Player(String n) {
		name =n;
		score =0;
		hand =new ArrayList<>();
	}

	// GamePlayer implementation
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public ArrayList<GameCard> getHand() {
		return hand;
	}

	@Override
	public GameBoard getBoard() {
		return board;
	}
}
