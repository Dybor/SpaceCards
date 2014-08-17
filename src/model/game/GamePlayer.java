package model.game;

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
	 * Attribue une main a un joueur.
	 */
	public void setHand(GameHand h);
	
	/**
	 * Attribue un plateau a un joueur.
	 */
	public void setBoard(GameBoard b);
	
	/**
	 * Accesseur de la main du joueur.
	 * @return Renvoie la liste des cartes que le joueur a en mains.
	 */
	public GameHand getHand();
	
	/**
	 * Accesseur du plateau de jeu du joueur.
	 * @return Renvoie le plateau de jeux.
	 */
	public GameBoard getBoard();
}
