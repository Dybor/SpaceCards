package model.drawable;

/**
 * Definit le comportement graphique d'une carte a jouer.
 * @author Nicolas
 *
 */
public interface IDrawableCard {
	
	/**
	 * Monde sans ressource ou, developpement.
	 */
	public static final int WITHOUT_GOOD =0;
	
	/**
	 * Monde avec ressources nouvelles (bleues).
	 */
	public static final int NOVELTY_GOODS =1;
	
	/**
	 * Monde avec ressources rares (marrons).
	 */
	public static final int RARE_ELEMENTS =2;
	
	/**
	 * Monde avec ressources elevees (vertes).
	 */
	public static final int GENES =3;
	
	/**
	 * Monde avec ressources aliens (jaunes).
	 */
	public static final int ALIEN_TECHNOLOGY =4;
	

	// Getters and setters
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
	
	/**
	 * Accesseur de la couleur d'une ressource si elle existe.
	 * @return Renvoie un entier.
	 */
	public int getGoodColor();
	
	/**
	 * Accesseur du chemin de l'image de la carte a jouer.
	 * @return Renvoie un entier.
	 */
	public String getImagePath();
	
	/**
	 * Teste la presence d'une ressource sur la carte.
	 * @return
	 */
	public boolean hasGood();
	
	// Actions
	/**
	 * Indique si la carte peut etre jouee.
	 * @return
	 */
	public boolean isPlayable();
	
	/**
	 * Indique si la carte est selectionnee.
	 * @return
	 */
	public boolean isSelected();
	
	/**
	 * Indique si la carte est dans la liste de cartes a défausser.
	 * @return
	 */
	public boolean isDiscarded();
}
