package model;

import model.drawable.IDrawableBoard;
import model.drawable.IDrawableHand;
import model.drawable.IDrawablePlayer;
import model.game.IGameBoard;
import model.game.IGameHand;
import model.game.IGamePlayer;

public class Player implements IGamePlayer, IDrawablePlayer {

	// Attributes
	private String name;
	private int score;
	private IGameHand hand;
	private IGameBoard board;

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
	public IGameHand getHand() {
		return hand;
	}

	@Override
	public IGameBoard getBoard() {
		return board;
	}

	@Override
	public void setHand(IGameHand h) {
		hand =h;
	}

	@Override
	public void setBoard(IGameBoard b) {
		board =b;
	}

	// GamePlayer implementation
	@Override
	public IDrawableHand getDrawableHand() {
		return (IDrawableHand)hand;
	}

	@Override
	public IDrawableBoard getDrawableBoard() {
		return (IDrawableBoard)board;
	}
}
