package model;

import model.game.GameBoard;
import model.game.GameHand;
import model.game.GamePlayer;

public class Player implements GamePlayer {

	// Attributes
	private String name;
	private int score;
	private GameHand hand;
	private GameBoard board;

	// Builder
	public Player(String n) {
		name =n;
		score =0;
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
	public GameHand getHand() {
		return hand;
	}

	@Override
	public GameBoard getBoard() {
		return board;
	}

	@Override
	public void setHand(GameHand h) {
		hand =h;
	}

	@Override
	public void setBoard(GameBoard b) {
		board =b;
	}
}
