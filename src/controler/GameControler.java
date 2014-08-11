package controler;

import model.AbstractModel;


public class GameControler extends AbstractControler {

  public GameControler(AbstractModel game) {
    super(game);
  }

  public void control() {
    //On notifie le mod�le d'une action si le contr�le est bon
    //--------------------------------------------------------
      
    //Si l'op�rateur est dans la liste
    if(this.listOperateur.contains(this.operateur)){
      //Si l'op�rateur est = 
      if(this.operateur.equals("="))
        this.game.getResultat(); //On ordonne au mod�le d'afficher le r�sultat
      //Sinon, on passe l'op�rateur au mod�le
      else
        this.game.setOperateur(this.operateur);
    }

    //Si le nombre est conforme
    if(this.nbre.matches("^[0-9.]+$"))
      this.game.setNombre(this.nbre);

    this.operateur = "";
    this.nbre = "";
  }
}