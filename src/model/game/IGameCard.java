package model.game;

/**
 * Défnit le comportement d'une carte à jouer.
 * @author Nicolas
 *
 */
public interface IGameCard {

	/**
	 * Identifiant d'un monde vierge.
	 */
	public static int WORLD =11;
	
	/**
	 * Identifiant d'un monde militaire.
	 */
	public static int MILITARY_WORLD =12;
	
	/**
	 * Identifiant d'un developpement.
	 */
	public static int DEVELOPMENT =13;
	
	/**
	 * Identifiant d'un monde productif.
	 */
	public static int EMPTY_WORLD =21;
	
	/**
	 * Identifiant d'un monde productif.
	 */
	public static int PRODUCTIVE_WORLD =22;
	
	/**
	 * Identifiant d'un monde a trouvaille.
	 */
	public static int WINDFALLS_WORLD =23;
	
	
	
	/**
	 * Accesseur du nom de la carte.
	 * @return Renvoie le nom de la carte.
	 */
	public String getName();
	
	/**
	 * Accesseur du type de la carte (Développement, Monde, Monde Militaire).
	 * @return Renvoie le type de la carte.
	 */
	public int getType();
	
	/**
	 * Renvoie le sous-type de la carte (Vide, Productif, Trouvaille).
	 * @return
	 */
	public int getSubType();
	
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
	 * Renvoie un identifiant du type de ressource.
	 * @return
	 */
	public int getGoodType();
	
	/**
	 * Test qui renvoie vrai si la carte est un monde de depart.
	 * @return
	 */
	public int getHomeWorldId();
	
	/**
	 * Produit un bien sur le monde.
	 * @param card
	 */
	public void produceGood(IGameCard card);
	
	/**
	 * Consomme le bien present sur le monde.
	 */
	public IGameCard consumeGood();
	
	/**
	 * Renvoie le bien.
	 * @return
	 */
	public IGameCard getGood();
}
