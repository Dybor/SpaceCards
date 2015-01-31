package com.geekteam.core.model.drawable;

/**
 * Definit le comportement du joueur a adopter pour la vue.
 * @author Nicolas
 *
 */
public interface IDrawablePlayer {

	public IDrawableHand getDrawableHand();
	public IDrawableBoard getDrawableBoard();
	public boolean canValidate();
	
}
