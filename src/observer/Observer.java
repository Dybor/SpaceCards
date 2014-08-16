package observer;

import java.util.ArrayList;

import modelInterfaces.DrawableCard;

public interface Observer {
	  public void updatePV(ArrayList<Integer> pv);    //met � jour les points de victoire de tous les joueurs
	  public void updateCards(ArrayList<DrawableCard> cards); //met � jour les cartes du joueur actif
	  public void updateGameRound(ArrayList<Boolean> bool); // met � jour les tours de jeu en les grisant
	  public void updatePlayerEvent(String str); // met � jour la ligne d'affichage des evenements
}