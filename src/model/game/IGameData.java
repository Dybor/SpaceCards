package model.game;

import java.util.ArrayList;

/**
 * Definit le comportement a adopter pour l'observabilite de la partie par le modele
 * @author Nicolas
 *
 */
public interface IGameData {

	// Getters
	/**
	 * Renvoie la liste de joueurs.
	 * @return
	 */
	public ArrayList<IGamePlayer> getPlayers();
	
	/**
	 * Renvoie la liste des cartes restantes dans la pioche.
	 * @return
	 */
	public ArrayList<IGameCard> getRemainingCards();
	
	/**
	 * Renvoie le nombre de points restants dans le pool de PVs.
	 * @return
	 */
	public int getRemainingVP();
	
	// Traitement des actions des joueurs
	/**
	 * Traite la carte selectionnee par le joueur.
	 * @param id
	 */
	public void treatSelectedCard(IGamePlayer p, int id);
}
