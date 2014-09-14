package controler;

import java.util.ArrayList;

import model.drawable.IDrawableCard;
import model.drawable.IDrawablePlayer;

/**
 * Definit le comportement du modele lorsqu'il est questionne par la vue.
 * @author Nicolas
 *
 */
public interface Observable {
	
	public int getPlayerViewOwnerIndex();
	public ArrayList<IDrawablePlayer> getPlayers();
	public ArrayList<IDrawableCard> getStack();
	public int getRemainingVP();
	public boolean isActionChoicePhase();
	
}
