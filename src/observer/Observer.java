package observer;

import java.util.ArrayList;

import model.Card;
import model.Player;

public interface Observer {
	  public void updatePV(ArrayList<Integer> pv);
	  public void updateCards(ArrayList<Card> cards);
	  
	  
}