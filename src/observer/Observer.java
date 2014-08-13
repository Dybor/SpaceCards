package observer;

import java.util.ArrayList;

import model.Card;
import model.Player;

public interface Observer {
	  public void updatePV(ArrayList<Integer> pv);    //met � jour les points de victoire de tous les joueurs
	  public void updateCards(ArrayList<Card> cards); //met � jour les cartes du joueur actif
	  
	  
}