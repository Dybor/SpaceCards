package com.geekteam.core.model.drawable;

import java.util.ArrayList;

/**
 * Definit le comportement du plateau du joueur pour la vue.
 * @author Nicolas
 *
 */
public interface IDrawableBoard {

	/**
	 * Accesseur des cartes du plateau du joueur.
	 * @return
	 */
	public ArrayList<IDrawableCard> getCards();
}
