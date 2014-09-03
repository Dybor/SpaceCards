package model.game;


/**
 * Définit le compotement du plateau de jeu du joueur.
 * @author Nicolas
 *
 */
public interface IGameBoard {

	/**
	 * Accesseur des cartes du plateau de jeu du joueur.
	 * @param i Indice de la carte
	 * @return
	 */
	public IGameCard getCard(int i);
	
	/**
	 * Accesseur de la taille du plateau de jeu.
	 * @return Renvoie un entier.
	 */
	public int size();
	
	/**
	 * Ajoute une carte sur le plateau de jeu.
	 * @param card
	 */
	public void addCard(IGameCard card);
	
	/**
	 * Retire une carte du plateau de jeu.
	 * @param card
	 */
	public void removeCard(IGameCard card);
	
	/**
	 * Ce test renverra vrai si le plateau de jeu contient au moins 12 cartes.
	 * @return 
	 */
	public boolean isFull();
}
