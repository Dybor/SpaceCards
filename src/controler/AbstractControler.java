package controler;

import java.util.ArrayList;

import model.AbstractModel;

public abstract class AbstractControler {

	  protected AbstractModel game;
	  protected String operateur = "", nbre = "";
	  protected ArrayList<String> listOperateur = new ArrayList<String>();

	  public AbstractControler(AbstractModel cal){
	    this.game = cal;
	    //On définit la liste des opérateurs
	    //Afin de s'assurer qu'ils sont corrects
	    this.listOperateur.add("+");
	    this.listOperateur.add("-");
	    this.listOperateur.add("*");
	    this.listOperateur.add("/");
	    this.listOperateur.add("=");
	   }
	   
	  //Définit l'opérateur
	  public void setOperateur(String ope){
	    this.operateur = ope;
	    control();
	  }
	   
	  //Définit le nombre
	  public void setNombre(String nombre){
	    this.nbre = nombre;
	    control();
	  }
	   
	  //Efface
	  public void reset(){
	    this.game.reset();
	  }
	   
	  //Méthode de contrôle
	  abstract void control();
	}