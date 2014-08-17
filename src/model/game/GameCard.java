package model.game;

/**
 * Défnit le comportement d'une carte à jouer.
 * @author Nicolas
 *
 */
public interface GameCard {

	/**
	 * Identifiant d'un monde vierge.
	 */
	public static int WORLD =1;
	
	/**
	 * Identifiant d'un monde productif.
	 */
	public static int PRODUCTIVE_WORLD =2;
	
	/**
	 * Identifiant d'un monde a trouvaille.
	 */
	public static int WINDFALLS_WORLD =3;
	
	/**
	 * Identifiant d'un monde militaire.
	 */
	public static int MILITARY_WORLD =4;
	
	/**
	 * Identifiant d'un developpement.
	 */
	public static int DEVELOPMENT =5;
	
	/**
	 * Accesseur du nom de la carte.
	 * @return Renvoie le nom de la carte.
	 */
	public String getName();
	
	/**
	 * Accesseur du type de la carte (Monde, Développement, Monde productif, Monde Militaire, ...).
	 * @return Renvoie le type de la carte.
	 */
	public int getType();
	
	/**
	 * Accesseur du coût de la carte.
	 * @return Renvoie un entier image du coup de la carte.
	 */
	public int getCost();
	
	/**
	 * Accesseur du score de la carte.
	 * @return Renvoie le nombre de points de victoire que rapporte la carte.
	 */
	public int getScoreValue();
	
	/**
	 * Test qui renvoie vrai si la carte est un monde de depart.
	 * @return
	 */
	public boolean isHomeWorld();
	
	/**
	 * Produit un bien sur le monde.
	 * @param card
	 */
	public void produceGood(GameCard card);
	
	/**
	 * Consomme le bien present sur le monde.
	 */
	public GameCard consumeGood();
	
	/**
	 * Test d'existence d'un bien sur le monde.
	 * @return
	 */
	public boolean hasGood();
}
