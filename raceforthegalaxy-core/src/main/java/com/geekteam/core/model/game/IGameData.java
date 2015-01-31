package com.geekteam.core.model.game;

import java.util.ArrayList;

/**
 * Definit le comportement a adopter pour l'observabilite de la partie par le modele
 * @author Nicolas
 *
 */
public interface IGameData {

	// Attributs statiques
	public static final int WAITING_PHASE =-3;
	public static final int ACTION_CHOICE_PHASE =-2;
	public static final int SETUP_PHASE =-1;
	public static final int EXPLORE_5_PHASE =0;
	public static final int EXPLORE_1_1_PHASE =1;
	public static final int DEVELOP_PHASE =2;
	public static final int SETTLE_PHASE =3;
	public static final int SELL_PHASE =4;
	public static final int CONSUME_vp2_PHASE =5;
	public static final int PRODUCE_PHASE =6;
	
	// Getters
	public ArrayList<IGamePlayer> getPlayers();
	public ArrayList<IGameCard> getStack();
	public int getRemainingVP();
	public int getRounds();
	public int getPhase();
	public boolean playersAreReady();
	public boolean boardsComplete();
	public IGameCard getFirstCard();
	
	// Setters
	public void initializeParameters();
	public void setCards(ArrayList<IGameCard> cs);
	public void setPlayersUnready();
	public void addDiscardedCard(IGameCard c);
	public void setPhase(int phase);
}
