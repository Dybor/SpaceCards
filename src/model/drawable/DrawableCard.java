package model.drawable;

import java.awt.Image;

/**
 * Définit le comportement graphique d'une carte à jouer
 * @author Nicolas
 *
 */
public interface DrawableCard {

	/**
	 * Accesseur de l'image de la carte.
	 * @return Renvoie un objet contenant le fichier image de la carte.
	 */
	public Image getImage();
}
