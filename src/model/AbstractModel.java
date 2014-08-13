package model;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;


public abstract class AbstractModel implements Observable{

  protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
  protected ArrayList<Player> listPlayer = new ArrayList<Player>();
  
  public abstract void updatePV();
  
  
  public void addPlayer(Player p){
	  listPlayer.add(p);
  }
  
  public void removePlayer(Player p){
	  if(listPlayer.contains(p)){
		  listPlayer.remove(listPlayer.indexOf(p));
	  }
	  
  }
  
  //Implémentation du pattern observer
  public void addObserver(Observer obs) {
    this.listObserver.add(obs);
  }

  public void notifyObserver(int i) {
  
  }

  public void removeObserver() {
    listObserver = new ArrayList<Observer>();
  }  
}