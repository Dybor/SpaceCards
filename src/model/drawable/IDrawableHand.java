package model.drawable;

import java.util.ArrayList;

/**
 * Definit le comportement de la main pour la vue.
 * @author Nicolas
 *
 */
public interface IDrawableHand {

	/**
	 * Accesseur des cartes de la main du joueur.
	 * @return
	 */
	public ArrayList<IDrawableCard> getCards();
}
