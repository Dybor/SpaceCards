package controler;

/**
 * Comportement du controleur pour le modele
 * @author Nicolas
 *
 */
public interface IControler {

	/**
	 * Indique au controleur qu'il doit rafraichir la vue.
	 */
	public void notifyView();
}