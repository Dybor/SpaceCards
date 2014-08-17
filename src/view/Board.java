package view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Card;
import model.drawable.DrawableCard;
import controler.GameControler;
import observer.Observer;

public class Board extends JFrame implements Observer, ActionListener, MouseListener, MouseMotionListener{


//L'instance de notre objet contrôleur
private GameControler controler;

private JPanel board;


private JPanel stats;
private ZoomCardPanel zoomCard;


private JLabel label_PV;
private JLabel label_nbBuilding;
private JLabel label_nbCard;

private JTextArea playerEvent;

private Hand hand;
private BoardCard playerBoard;
private GameRound gameRound;

// Menu
private JMenuBar menuBar = new JMenuBar();
private JMenu menu_File = new JMenu("Fichier");
private JMenu menu_Game = new JMenu("Jeu");

private JMenuItem item_new = new JMenuItem("Nouvelle Partie");
private JMenuItem item_join = new JMenuItem("Rejoindre une Partie");
private JMenuItem item_quit = new JMenuItem("Quitter");


public static int POSITION_X_HAND = 0;   // position de départ de la main du joueur en x 
public static int POSITION_Y_HAND = 50; // position de départ de la main du joueur en y
public static int POSITION_X_BOARD = 0;   // position de départ sur le plateau du joueur en x 
public static int POSITION_Y_BOARD = 0; // position de départ sur le plateau du joueur en y

public static int WIDTH_CARD_HAND = 120; // largeur d'une carte dans la main du joueur
public static int HEIGHT_CARD_HAND = 200;// hauteur d'une carte dans la main du joueur
public static int WIDTH_CARD_BOARD = 60; // largeur d'une carte sur le plateau du joueur
public static int HEIGHT_CARD_BOARD = 100;// hauteur d'une carte sur le plateau du joueur
public static int WIDTH_CARD_ZOOM = 240; // largeur d'une carte zoomée
public static int HEIGHT_CARD_ZOOM = 400;// hauteur d'une carte zoomée

public static int POSITION_X_ROUND = 0;   // position de départ du tour de jeu
public static int POSITION_Y_ROUND = 0; // position de départ du tour de jeu
public static int WIDTH_ROUND = 50; // largeur d'un element du tour de jeu
public static int HEIGHT_ROUND = 50;// hauteur d'un element du tour de jeu

// Test Model
private ArrayList<DrawableCard> cards = new ArrayList<>();
private ArrayList<DrawableCard> cards2 = new ArrayList<>();


public Board(GameControler controler){                
  this.setTitle("Race For The Galaxy");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setExtendedState(this.MAXIMIZED_BOTH);
  
  this.setLocationRelativeTo(null);
  initComposant();                
  this.controler = controler;                
  this.setVisible(true);
}

private void initComposant(){
	initBoard();			//Initialisation du board central
	initStats();			//Initialisation du résumé des statistiques du joueur
	initZoomCard();			//Initialisation de l'espace pour la carte zoomée
	initMenu();				//Initialisation de la barre de menu
	initHands();			//Initialisation de la main du joueur
	initGameRound();		//Initialisation des tours de jeu 
	initPlayerEvent();		//Initialisation des évenements de jeu
	
	initTestModel();
	
	
	initFrame();			//Initialisation de la frame
	
}   

private void initBoard(){
	board = new JPanel();
	playerBoard = new BoardCard(6,2,0,10,0,80,Board.WIDTH_CARD_BOARD,Board.HEIGHT_CARD_BOARD,Board.POSITION_X_BOARD,Board.POSITION_Y_BOARD);
	playerBoard.addMouseMotionListener(this);
	playerBoard.addMouseListener(this);
//	board.setPreferredSize(new Dimension(10*Board.WIDTH_CARD_HAND, playerBoard.getHeightPanel()));
	board.add(playerBoard);
	
}

private void initStats(){
	stats = new JPanel();
	stats.setLayout(new GridLayout(3, 2));
	
	label_PV = new JLabel("0");
	label_nbBuilding = new JLabel("0");
	label_nbCard = new JLabel("0");
	
	stats.add(new JLabel("PV"));
	stats.add(label_PV);
	stats.add(new JLabel("Batiments Posés"));
	stats.add(label_nbBuilding);
	stats.add(new JLabel("Cartes en mains"));
	stats.add(label_nbCard);
	
	stats.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
	
}

private void initMenu(){
	item_new.addActionListener(this);
	item_join.addActionListener(this);
	item_quit.addActionListener(this);
	
	menu_File.add(item_new);
	menu_File.add(item_join);
	menu_File.add(item_quit);
	
	menuBar.add(menu_File);
	menuBar.add(menu_Game);
}

private void initHands(){
	hand = new Hand();
	hand.addMouseListener(this);
	hand.addMouseMotionListener(this);
}

private void initZoomCard(){
	zoomCard = new ZoomCardPanel();
	
}

private void initGameRound(){
	gameRound = new GameRound(0, 0, 0);
	
}

private void initPlayerEvent(){
	playerEvent = new JTextArea();
	playerEvent.setFocusable(false);
}


private void initFrame(){
	this.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	
	gbc.gridx = 0 ; gbc.gridy = 0; // la grille commence en (0,0)
	gbc.fill = GridBagConstraints.VERTICAL;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.gridwidth = 1;
	gbc.gridheight = 2;
	gbc.anchor = GridBagConstraints.CENTER;
	this.add(board,gbc);
	
	gbc.gridx = 1 ; gbc.gridy = 0;
	gbc.fill = GridBagConstraints.NONE;
	gbc.fill = GridBagConstraints.NONE;
	gbc.gridwidth = GridBagConstraints.RELATIVE;
	gbc.gridheight = 2;
	gbc.anchor = GridBagConstraints.LINE_END;
	this.add(gameRound,gbc);
	
	gbc.gridx = 2 ; gbc.gridy = 0;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.gridheight = 1;
	gbc.anchor = GridBagConstraints.LINE_END;
	this.add(stats,gbc);
	
	gbc.gridx = 2 ; gbc.gridy = 1;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.gridheight = GridBagConstraints.RELATIVE;
	gbc.anchor = GridBagConstraints.LINE_END;
	this.add(zoomCard,gbc);
	
	gbc.gridx = 0 ; gbc.gridy = 2;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.gridheight = GridBagConstraints.RELATIVE;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.PAGE_END;
	this.add(playerEvent,gbc);
	
	gbc.gridx = 0; gbc.gridy = 3;
	gbc.gridheight = GridBagConstraints.REMAINDER;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	this.add(hand,gbc);
	

	this.setJMenuBar(menuBar);
}

private void initTestModel(){
	cards.add(new Card("12,12,12,12,12"));
	cards.add(new Card("12,12,12,12,12"));
	cards.add(new Card("12,12,12,12,12"));
	cards.add(new Card("12,12,12,12,12"));
	cards.add(new Card("12,12,12,12,12"));
	cards.add(new Card("12,12,12,12,12"));
	
	cards2.add(new Card("12,12,12,12,12"));
	cards2.add(new Card("12,12,12,12,12"));
	cards2.add(new Card("12,12,12,12,12"));
	cards2.add(new Card("12,12,12,12,12"));
	cards2.add(new Card("12,12,12,12,12"));
	cards2.add(new Card("12,12,12,12,12"));
	
}


@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 controler.launchGame();
	 } else if(e.getSource()==item_new){
		 
		 
		 updateCards(cards);
		 updateBoardCards(cards2);		 
		 
		 ArrayList<Boolean> bool = new ArrayList<Boolean>();
		 bool.add(true);
		 bool.add(false);
		 bool.add(false);
		 bool.add(true);
		 bool.add(false);
		 bool.add(false);
		 
		 gameRound.setGreyRound(bool);
		 
		 updatePlayerEvent("Tour1");
		 
	 } else if(e.getSource()==item_quit){
		System.exit(0); 
	 }
		 
	
}

@Override
public void updatePV(ArrayList<Integer> pv) {
	
}

public void updateCards(ArrayList<DrawableCard> cards){
	hand.updateCards(cards);
}

public void updateBoardCards(ArrayList<DrawableCard> cards){
	playerBoard.updateCards(cards);
}

@Override
public void updateGameRound(ArrayList<Boolean> bool) {
	gameRound.setGreyRound(bool);
	
}

@Override
public void updatePlayerEvent(String str) {
	playerEvent.setText(str);
	
}




@Override
public void mouseClicked(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseClicked(e);
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mouseClicked(e);
	}
	
}

@Override
public void mouseEntered(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseEntered(e);
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mouseEntered(e);
	}
}

@Override
public void mouseExited(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseExited(e);
		zoomCard.setCard(null);
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mouseExited(e);
	}
}

@Override
public void mousePressed(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mousePressed(e);
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mousePressed(e);
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseReleased(e);
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mouseReleased(e);
	}
}

@Override
public void mouseDragged(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseDragged(e);
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mouseDragged(e);
	}
}

@Override
public void mouseMoved(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseMoved(e);
		if(hand.getFocusedCard()!=null){
			zoomCard.setCard(new GraphicCard(hand.getFocusedCard().getImage(),0,0,Board.WIDTH_CARD_ZOOM, Board.HEIGHT_CARD_ZOOM));
		} else {
			zoomCard.setCard(null);
		}
	
	} else if(e.getComponent().equals(playerBoard)){
		playerBoard.mouseMoved(e);
		if(playerBoard.getFocusedCard()!=null){
			zoomCard.setCard(new GraphicCard(playerBoard.getFocusedCard().getImage(),0,0,Board.WIDTH_CARD_ZOOM, Board.HEIGHT_CARD_ZOOM));
		} else {
			zoomCard.setCard(null);
		}
	}
}

}