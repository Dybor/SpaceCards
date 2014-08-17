package observer;

import java.util.ArrayList;

import model.drawable.IDrawableBoard;
import model.drawable.IDrawableCard;
import model.drawable.IDrawableHand;

public interface Observer {
	  public void updatePV(ArrayList<Integer> pv);    //met à jour les points de victoire de tous les joueurs
	  
	  public void updateHand(IDrawableHand hand); //met à jour les cartes du joueur actif
	  public void updateGameRound(ArrayList<Boolean> bool); // met à jour les tours de jeu en les grisant
	  public void updatePlayerEvent(String str); // met à jour la ligne d'affichage des evenements
	  
	  public void updateBoard(IDrawableBoard board); // Met à jour l'ensemble du plateau du joueur actif
	  
	  
	  
}