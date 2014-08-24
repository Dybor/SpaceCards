package controler;

import model.GameModel;

public class GameControler{

	private INotifyView view;
	
	private GameModel game;
	public GameControler(GameModel game) {
		this.game = game;
	}

	public void launchGame(){
		game.launchGame();
	}
	
	public void handCardClicked(int id){
		
	}
	
	public void BoardCardClicked(int id){
		
	}
	
	public void actionCardSelected(int i){
		
	}

	
	public void control() {

	}
	
	public void setView(INotifyView view){
		this.view = view;
	}

}