package model.drawable;

/**
 * Definit le comportement graphique d'une carte a jouer.
 * @author Nicolas
 *
 */
public interface DrawableCard {

	/**
	 * Accesseur du numero d'identification de l'image de la carte.
	 * @return Renvoie un entier.
	 */
	public int getImageId();
	
	/**
	 * Accesseur du numero d'identification d'un des pouvoir de la carte (i compris entre 1 et 6).
	 * @param i Pouvoir de la carte (compris entre 1 et 6, du pouvoir d'exploration a celui de production).
	 * @return
	 */
	public int getPowerId(int i);
}
