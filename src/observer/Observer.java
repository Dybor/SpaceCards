package observer;

import java.util.ArrayList;

import model.Card;

public interface Observer {
	  public void updatePV(ArrayList<Integer> pv);    //met à jour les points de victoire de tous les joueurs
	  public void updateCards(ArrayList<Card> cards); //met à jour les cartes du joueur actif
	  public void updateGameRound(ArrayList<Boolean> bool); // met à jour les tours de jeu en les grisant
	  public void updatePlayerEvent(String str); // met à jour la ligne d'affichage des evenements
}