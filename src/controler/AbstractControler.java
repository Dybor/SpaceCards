package controler;

import model.game.AbstractGameModel;

public abstract class AbstractControler {

	protected AbstractGameModel game;

	public AbstractControler(AbstractGameModel cal) {
		this.game = cal;
	}

	public void updatePV() {
	}

	// M�thode de contr�le
	abstract void control();
}