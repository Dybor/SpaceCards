package controler;

import model.AbstractModel;

public abstract class AbstractControler {

	protected AbstractModel game;

	public AbstractControler(AbstractModel cal) {
		this.game = cal;
	}

	public void updatePV() {
		this.game.updatePV();
	}

	// M�thode de contr�le
	abstract void control();
}