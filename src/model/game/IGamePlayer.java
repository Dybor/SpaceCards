package model.game;

/**
 * Défnit le comportement d'un joueur de la partie
 * @author Nicolas
 *
 */
public interface IGamePlayer {

	// Getters and setters
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
	 * Attribue une main a un joueur.
	 */
	public void setHand(IGameHand h);
	
	/**
	 * Attribue un plateau a un joueur.
	 */
	public void setBoard(IGameBoard b);
	
	/**
	 * Accesseur de la main du joueur.
	 * @return Renvoie la liste des cartes que le joueur a en mains.
	 */
	public IGameHand getHand();
	
	/**
	 * Accesseur du plateau de jeu du joueur.
	 * @return Renvoie le plateau de jeux.
	 */
	public IGameBoard getBoard();
	
	/**
	 * Indique si le joueur a termine de jouer et attend que la partie avance.
	 * @return
	 */
	public boolean isReady();
	
	/**
	 * Change l'etat du joueur.
	 * @param r
	 */
	public void setReady(boolean r);
	
	/**
	 * Renvoie le nombre de carte actuellement selectionnees.
	 * @return
	 */
	public int getSelectedCardsNumber();
	
	/**
	 * Renvoie le nombre de carte a selectionner.
	 * @return
	 */
	public int getCardsToBeSelectedNumber();
	
	/**
	 * Active le nombre de carte que le joueur doit selectionner.
	 * @return
	 */
	public void setCardsToBeSelectedNumber(int n);
	
	// Actions de jeu
	/**
	 * Selectionne une carte.
	 * @param id
	 */
	public void selectCard(int id);
}
