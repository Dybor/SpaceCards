package controler;

import model.game.AbstractGameModel;

public abstract class AbstractControler {

	protected AbstractGameModel game;

	public AbstractControler(AbstractGameModel cal) {
		this.game = cal;
	}

	public void updatePV() {
	}

	// Méthode de contrôle
	abstract void control();
}