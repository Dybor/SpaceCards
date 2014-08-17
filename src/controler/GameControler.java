package controler;

import model.game.AbstractGameModel;

public class GameControler{

	private AbstractGameModel game;
	public GameControler(AbstractGameModel game) {
		this.game = game;
	}

	public void launchGame(){
		game.launchGame();
	}
	
	public void control() {

	}

}