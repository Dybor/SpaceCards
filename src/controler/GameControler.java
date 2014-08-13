package controler;

import model.AbstractModel;


public class GameControler extends AbstractControler {

  public GameControler(AbstractModel game) {
    super(game);
  }

  public void control() {
  
      this.game.updatePV();
  }

}