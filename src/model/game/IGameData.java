package model.game;

import java.util.ArrayList;

/**
 * Definit le comportement a adopter pour l'observabilite de la partie par le modele
 * @author Nicolas
 *
 */
public interface IGameData {

	// Getters
	public ArrayList<IGamePlayer> getPlayers();
	public ArrayList<IGameCard> getRemainingCards();
	public int getRemainingVP();
	public boolean playersAreReady();
	public boolean boardsNotComplete();
	public ArrayList<IGameCard> getCards();
	
	// Setters
	public void setPlayersUnready();
	public void initializeParameters();
	public void setCards(ArrayList<IGameCard> cs);
}
