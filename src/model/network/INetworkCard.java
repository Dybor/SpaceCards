package model.network;

/**
 * D�finit le comportement r�seau/s�rialisable d'une carte � jouer.
 * @author Nicolas
 *
 */
public interface INetworkCard {

	/**
	 * Cr�e une cha�ne de caract�re qui s�rialise la carte � jouer.
	 * @return Une cha�ne de caract�re repr�sentant la carte.
	 */
	public String serialize();
}
