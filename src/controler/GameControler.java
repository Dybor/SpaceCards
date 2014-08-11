package controler;

import model.AbstractModel;


public class GameControler extends AbstractControler {

  public GameControler(AbstractModel game) {
    super(game);
  }

  public void control() {
    //On notifie le modèle d'une action si le contrôle est bon
    //--------------------------------------------------------
      
    //Si l'opérateur est dans la liste
    if(this.listOperateur.contains(this.operateur)){
      //Si l'opérateur est = 
      if(this.operateur.equals("="))
        this.game.getResultat(); //On ordonne au modèle d'afficher le résultat
      //Sinon, on passe l'opérateur au modèle
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