package controler;

import model.GameModel;

public class GameControler{

	private GameModel game;
	public GameControler(GameModel game) {
		this.game = game;
	}

	public void launchGame(){
		game.launchGame();
	}
	
	public void control() {

	}

}