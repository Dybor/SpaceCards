package com.geekteam.core.model.game;

/**
 * D�fnit le comportement d'un joueur de la partie
 * @author Nicolas
 *
 */
public interface IGamePlayer {

	// Getters and setters
	public String getName();
	public int getScore();
	public void setHand(IGameHand h);
	public void setBoard(IGameBoard b);
	public IGameHand getHand();
	public IGameBoard getBoard();
	public boolean isReady();
	public void setReady(boolean r);
	public int getSelectedCardsNumber();
	public int getCardsToBeSelectedNumber();
	public void setCardsToBeSelectedNumber(int n);
	public void setValidate(boolean v);
	
	// Actions de jeu
	public void selectCard(int id);
}
