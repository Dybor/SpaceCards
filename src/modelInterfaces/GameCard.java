package modelInterfaces;

/**
 * D�fnit le comportement d'une carte � jouer.
 * @author Nicolas
 *
 */
public interface GameCard {

	/**
	 * Accesseur du nom de la carte.
	 * @return Renvoie le nom de la carte.
	 */
	public String getName();
	
	/**
	 * Accesseur du type de la carte (Monde, D�veloppement, Monde productif, Monde Militaire, ...).
	 * @return Renvoie le type de la carte.
	 */
	public String getType();
	
	/**
	 * Accesseur du co�t de la carte.
	 * @return Renvoie un entier image du coup de la carte.
	 */
	public int getCost();
	
	/**
	 * Accesseur du score de la carte.
	 * @return Renvoie le nombre de points de victoire que rapporte la carte.
	 */
	public int getScoreValue();
}
