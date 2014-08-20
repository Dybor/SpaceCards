package controler;

/**
 * Definit le comportement du modele lorsqu'il est questionne par la vue.
 * @author Nicolas
 *
 */
public interface Observable {
	
	/**
	 * Renvoie les donnees de jeu pour mise a jour de la vue.
	 * @return
	 */
	public int getModelData();
}
