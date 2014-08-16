package modelInterfaces;

import java.util.ArrayList;

/**
 * Défnit le comportement d'un joueur de la partie
 * @author Nicolas
 *
 */
public interface GamePlayer {

	/**
	 * Accesseur du nom du joueur.
	 * @return Renvoie le nom du joueur.
	 */
	public String getName();
	
	/**
	 * Accesseur du score du joueur.
	 * @return Renvoie le score du joueur.
	 */
	public int getScore();
	
	/**
	 * Accesseur de la main du joueur.
	 * @return Renvoie la liste des cartes que le joueur a en mains.
	 */
	public ArrayList<GameCard> getHand();
}
