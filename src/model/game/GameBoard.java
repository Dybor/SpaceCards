package model.game;

import java.util.ArrayList;

/**
 * Définit le compotement du plateau de jeu du joueur.
 * @author Nicolas
 *
 */
public interface GameBoard {

	/**
	 * Accesseur des cartes du plateau de jeu du joueur.
	 * @return Renvoie la liste des cartes presentes sur le plateau de jeu du joueur.
	 */
	public ArrayList<GameCard> getCards();
	
	/**
	 * Accesseur de la taille du plateau de jeu.
	 * @return Renvoie un entier.
	 */
	public int size();
	
	/**
	 * Ajoute une carte sur le plateau de jeu.
	 * @param card
	 */
	public void addCard(GameCard card);
	
	/**
	 * Retire une carte du plateau de jeu.
	 * @param card
	 */
	public void removeCard(GameCard card);
	
	/**
	 * Ce test renverra vrai si le plateau de jeu contient au moins 12 cartes.
	 * @return 
	 */
	public boolean isFull();
}
