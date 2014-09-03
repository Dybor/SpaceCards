package model.game;


/**
 * Definit le comportement de la main d'un joueur.
 * @author Nicolas
 *
 */
public interface IGameHand {

	// Getters and setters
	/**
	 * Accesseur des cartes de la mains du joueur.
	 * @param i Indice de la carte
	 * @return
	 */
	public IGameCard getCard(int i);
	
	/**
	 * Accesseur de la taille de la main du joueur.
	 * @return
	 */
	public int size();
	
	/**
	 * Ajoute une carte a la main.
	 * @param card Carte a ajouter dans la main.
	 */
	public void addCard(IGameCard card);
	
	/**
	 * Retire une carte a la main.
	 * @param card Carte a retirer de la main.
	 */
	public void discardCard(IGameCard card);
	
	/**
	 * Ce test renverra vrai si la main est pleine, c'est-a-dire contient plus de 10 cartes. 
	 * @return Renvoie vrai si la taille de la main est strictement superieure a 10.
	 */
	public boolean isFull();
	
	// Actions de jeu
	/**
	 * Selectionne une carte.
	 * @param id
	 */
	public void selectCard(int id);
}
