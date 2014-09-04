package controler;

import model.GameModel;

public class GameControler implements IControler {

	private INotifyView view;
	
	private GameModel game;
	public GameControler(GameModel game) {
		this.game = game;
		game.setController(this);
	}

	public void launchGame(){
		game.launchGame();
	}
	
	public void handCardClicked(int id){
		game.treatSelectedCard(id);
	}
	
	public void BoardCardClicked(int id){
		
	}
	
	public void actionCardSelected(int i){
		
	}
	
	public void validateButtonClicked(){
		
	}

	
	public void control() {

	}
	
	public void setView(INotifyView view){
		this.view = view;
	}

	@Override
	public void notifyView() {
		view.refreshAll();
	}

}