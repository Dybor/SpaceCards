package controler;

import java.util.ArrayList;

import model.drawable.IDrawableCard;
import model.drawable.IDrawablePlayer;

/**
 * Definit le comportement du modele lorsqu'il est questionne par la vue.
 * @author Nicolas
 *
 */
public interface Observable {
	
	/**
	 * Renvoie la position du joueur possedant la vue courrante dans la liste de
	 * joueurs de la partie.
	 * @return
	 */
	public int getPlayerViewOwnerIndex();
	
	/**
	 * Renvoie la liste des joueurs pour la vue.
	 * @return
	 */
	public ArrayList<IDrawablePlayer> getPlayers();
	
	/**
	 * Ranvoie la liste des cartes de la pioche pour la vue.
	 * @return
	 */
	public ArrayList<IDrawableCard> getCards();
	
	/**
	 * Renvoie le nombre de points restants dans le pool de PV.
	 * @return
	 */
	public int getRemainingVP();
}
