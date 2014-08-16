package model;

import java.util.ArrayList;

import observer.Observer;

public class Game extends AbstractModel{

	public static int UPDATE_PV = 0;
	@Override
	public void updatePV() {
		notifyObserver(UPDATE_PV);
	}

	@Override
	public void notifyObserver(int i) {
		for(Observer obs : listObserver){
			switch (i){
			case 0: // UPDATE_PV
				obs.updatePV(listOfPV());
			break;
			default:
			}
		}
		
		
	}
	
	private ArrayList<Integer> listOfPV(){
		ArrayList<Integer> list = new ArrayList<>();
		
		for (Player p : this.listPlayer){
			list.add(p.getScore());
		}
		
		return list;
	}


}