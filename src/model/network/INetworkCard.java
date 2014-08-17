package model.network;

/**
 * Définit le comportement réseau/sérialisable d'une carte à jouer.
 * @author Nicolas
 *
 */
public interface INetworkCard {

	/**
	 * Crée une chaîne de caractère qui sérialise la carte à jouer.
	 * @return Une chaîne de caractère représentant la carte.
	 */
	public String serialize();
}
