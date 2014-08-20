package model;

import java.util.ArrayList;

import controler.Controllable;
import controler.Observable;
import model.game.RunnableGame;

public class GameModel implements Observable, Controllable {

	// Attributes
	private ArrayList<RunnableGame> games;
	private RunnableGame currentGame;
	private Player player;
	
	// Builder
	public GameModel(String n, Player p) {
		// Création de la partie et ajout du joueur principal
		player =p;
		games =new ArrayList<>();
	}

	// Implémentation du pattern Observable pour la vue
	@Override
	public int getModelData() {
		return 0;
	}

	// Implémentation du pattern Controllable pour le controleur
	public void launchGame() {
		// Création d'une partie
		currentGame =new RunnableGame("Nouvelle partie de "+player.getName()+" ("+games.size()+")", player);
		games.add(currentGame);
		
		// Lancement de la partie
		currentGame.run();
	}
}