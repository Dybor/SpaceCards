package model.drawable;

/**
 * Definit le comportement du joueur a adopter pour la vue.
 * @author Nicolas
 *
 */
public interface IDrawablePlayer {

	/**
	 * Renvoie la main du joueur pour la vue.
	 * @return
	 */
	public IDrawableHand getDrawableHand();
	
	/**
	 * Renvoie le plateau du joueur pour la vue.
	 * @return
	 */
	public IDrawableBoard getDrawableBoard();
	
}
