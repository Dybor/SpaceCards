package model;

import java.util.ArrayList;

import controler.Controllable;
import controler.Observable;
import model.drawable.IDrawableCard;
import model.drawable.IDrawablePlayer;
import model.game.IGameCard;
import model.game.IGamePlayer;
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
	public int getPlayerViewOwnerIndex() {
		return 0;
	}

	@Override
	public ArrayList<IDrawablePlayer> getPlayers() {
		ArrayList<IDrawablePlayer> newList =new ArrayList<>();
		for (IGamePlayer p:currentGame.getPlayers())
			newList.add((IDrawablePlayer)p);
		return newList;
	}

	@Override
	public ArrayList<IDrawableCard> getCards() {
		ArrayList<IDrawableCard> newList =new ArrayList<>();
		for (IGameCard c:currentGame.getCards())
			newList.add((IDrawableCard)c);
		return newList;
	}

	@Override
	public int getRemainingVP() {
		return currentGame.getRemainingVP();
	}

	// Implémentation du pattern Controllable pour le controleur
	@Override
	public void launchGame() {
		// Création d'une partie
		currentGame =new RunnableGame("Nouvelle partie de "+player.getName()+" ("+games.size()+")", player);
		games.add(currentGame);
		
		// Lancement de la partie
		currentGame.run();
	}
}