package controler;

/**
 * Definit le comportement du modele lorsqu'il est controle par le controler.
 * @author Nicolas
 *
 */
public interface Controllable {

	/**
	 * Lance une nouvelle partie.
	 */
	public void launchGame();
	
	/**
	 * Traite la carte avec l'identifiant id.
	 * @param id
	 */
	public void treatSelectedCard(int id);
	
	/**
	 * 
	 * @param c
	 */
	public void setController(IControler c);
	
}
