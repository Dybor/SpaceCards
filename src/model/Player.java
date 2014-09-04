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
	
	private boolean ready;
	private int nSelectedCards;
	private int nCardsToBeSelected;

	// Builder
	public Player(String n) {
		name =n;
		score =0;
	}

	// GamePlayer implementation : Getters and setters
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
	
	@Override
	public boolean isReady() {
		return ready;
	}

	@Override
	public void setReady(boolean r) {
		ready =r;
	}
	
	@Override
	public int getSelectedCardsNumber() {
		return nSelectedCards;
	}
	
	@Override
	public int getCardsToBeSelectedNumber() {
		return nCardsToBeSelected;
	}
	
	@Override
	public void setCardsToBeSelectedNumber(int n) {
		nCardsToBeSelected =n;
	}

	// DrawablePlayer implementation
	@Override
	public IDrawableHand getDrawableHand() {
		return (IDrawableHand)hand;
	}

	@Override
	public IDrawableBoard getDrawableBoard() {
		return (IDrawableBoard)board;
	}

	@Override
	public void selectCard(int id) {
		hand.selectCard(id);
		nSelectedCards =0;
		for (int i =0 ; i<hand.size() ; i++)
			if (hand.getCard(i).isSelected()) nSelectedCards +=1;
	}
}
