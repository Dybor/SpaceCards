package com.geekteam.core.controler;

import java.util.ArrayList;

import com.geekteam.core.model.drawable.IDrawableCard;
import com.geekteam.core.model.drawable.IDrawablePlayer;

/**
 * Definit le comportement du modele lorsqu'il est questionne par la vue.
 * @author Nicolas
 *
 */
public interface Observable {
	
	public int getPlayerViewOwnerIndex();
	public ArrayList<IDrawablePlayer> getPlayers();
	public ArrayList<IDrawableCard> getStack();
	public int getRemainingVP();
	public boolean isActionChoicePhase();
	
}
