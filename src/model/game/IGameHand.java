package model.game;


/**
 * Definit le comportement de la main d'un joueur.
 * @author Nicolas
 *
 */
public interface IGameHand {

	// Getters
	public IGameCard getCard(int i);
	public int size();
	public boolean isFull();
	
	// Setters
	public void addCard(IGameCard card);
	public void discardsCard(IGameCard card);
	
	// Actions de jeu
	public void selectCard(int id);
}
