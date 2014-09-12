package controler;

/**
 * Definit le comportement du modele lorsqu'il est controle par le controler.
 * @author Nicolas
 *
 */
public interface Controllable {

	/**
	 * 
	 * @param c
	 */
	public void setController(IControler c);
	
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
	 * Validation de l'action du joueur.
	 */
	public void validateAction();
	
}
