package view;


import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;



import model.drawable.IDrawableBoard;
import model.drawable.IDrawableHand;
import controler.GameControler;
import controler.INotifyView;
import controler.Observable;

public class Board extends JFrame implements ActionListener, MouseListener, MouseMotionListener, ItemListener, INotifyView{


//L'instance de notre objet contrôleur
private GameControler controler;
private Observable game;

private JPanel board;


private ZoomCardPanel zoomCard;
private PlayerStatus pStatus;

private JTextArea playerEvent;

private Hand hand;
private BoardCard playerBoard;
private ArrayList<BoardCard> otherPlayerBoard;
private GameRound gameRound;

// Menu
private JMenuBar menuBar = new JMenuBar();
private JMenu menu_File = new JMenu("Fichier");
private JMenu menu_Game = new JMenu("Jeu");
private JMenu menu_Option = new JMenu("Options");

private JMenuItem item_new = new JMenuItem("Nouvelle Partie");
private JMenuItem item_join = new JMenuItem("Rejoindre une Partie");
private JMenuItem item_quit = new JMenuItem("Quitter");

private JCheckBoxMenuItem item_fullScreen = new JCheckBoxMenuItem("Plein écran");

public static int POSITION_X_HAND = 0;   // position de départ de la main du joueur en x 
public static int POSITION_Y_HAND = 50; // position de départ de la main du joueur en y
public static int POSITION_X_BOARD = 0;   // position de départ sur le plateau du joueur en x 
public static int POSITION_Y_BOARD = 0; // position de départ sur le plateau du joueur en y

public static int WIDTH_CARD_HAND = 142; // largeur d'une carte dans la main du joueur
public static int HEIGHT_CARD_HAND = 200;// hauteur d'une carte dans la main du joueur
public static int WIDTH_CARD_BOARD = 71; // largeur d'une carte sur le plateau du joueur
public static int HEIGHT_CARD_BOARD = 100;// hauteur d'une carte sur le plateau du joueur
public static int WIDTH_CARD_ZOOM = 357; // largeur d'une carte zoomée
public static int HEIGHT_CARD_ZOOM = 500;// hauteur d'une carte zoomée

public static int POSITION_X_ROUND = 0;   // position de départ du tour de jeu
public static int POSITION_Y_ROUND = 0; // position de départ du tour de jeu
public static int WIDTH_ROUND = 50; // largeur d'un element du tour de jeu
public static int HEIGHT_ROUND = 50;// hauteur d'un element du tour de jeu

public Board(GameControler controler, Observable observable){                
  this.setTitle("Race For The Galaxy");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setExtendedState(this.MAXIMIZED_BOTH);
  
  this.setLocationRelativeTo(null);
  initComposant();                
  this.controler = controler;    
  this.game = observable;
  this.setVisible(true);
  
}

private void initComposant(){
	initBoard();			//Initialisation du board central
	initZoomCard();			//Initialisation de l'espace pour la carte zoomée
	initMenu();				//Initialisation de la barre de menu
	initHands();			//Initialisation de la main du joueur
	initGameRound();		//Initialisation des tours de jeu 
	initPlayerEvent();		//Initialisation des évenements de jeu
	initPlayerStatus();		//Initialisation du panneau de status du joueur
	
	initTestModel();
	
	
	initFrame();			//Initialisation de la frame
	
}   

private void initBoard(){
	board = new JPanel();
	playerBoard = new BoardCard(6,2,0,10,0,0,Board.WIDTH_CARD_BOARD,Board.HEIGHT_CARD_BOARD,Board.POSITION_X_BOARD,Board.POSITION_Y_BOARD);
	playerBoard.addMouseMotionListener(this);
	playerBoard.addMouseListener(this);
//	board.setPreferredSize(new Dimension(10*Board.WIDTH_CARD_HAND, playerBoard.getHeightPanel()));
	
	

	
	
	
	
}


private void initMenu(){
	item_new.addActionListener(this);
	item_join.addActionListener(this);
	item_quit.addActionListener(this);
	
	menu_File.add(item_new);
	menu_File.add(item_join);
	menu_File.add(item_quit);
	
	item_fullScreen.setState(false);
	item_fullScreen.addItemListener(this);
	menu_Option.add(item_fullScreen);
	
	menuBar.add(menu_File);
	menuBar.add(menu_Game);
	menuBar.add(menu_Option);
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

private void initPlayerStatus(){
	pStatus = new PlayerStatus(7);
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
	gbc.gridwidth = GridBagConstraints.RELATIVE;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	this.add(hand,gbc);
	
	gbc.gridx = 2; gbc.gridy = 3;
	gbc.gridheight = GridBagConstraints.REMAINDER;
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	this.add(pStatus,gbc);

	this.setJMenuBar(menuBar);
	
	
}

private void initTestModel(){

	
}

private void fullScreen(boolean bool){
	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = environment.getDefaultScreenDevice();
	if(bool){
		this.dispose();
		this.setUndecorated(true);
		device.setFullScreenWindow(this);
		this.setVisible(true);
	} else {
		this.dispose();
		this.setUndecorated(false);
		device.setFullScreenWindow(null);
		this.setVisible(true);
	}
}

public void refreshAll(){
	int me = game.getPlayerViewOwnerIndex();
	updateBoard(game.getPlayers().get(me).getDrawableBoard());
	updateHand(game.getPlayers().get(me).getDrawableHand());
	updatePoolPV(game.getRemainingVP());
	
}


@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 controler.launchGame();
	 } else if(e.getSource()==item_new){
		 
			model.Hand hand = new model.Hand();
//			hand.addCard(new Card("12,12,12,12,12,12"));
//			hand.addCard(new Card("12,12,12,12,12,12"));
//			hand.addCard(new Card("12,12,12,12,12,12"));
//			hand.addCard(new Card("12,12,12,12,12,12"));
//			hand.addCard(new Card("12,12,12,12,12,12"));
//			
//			model.Board board = new model.Board();
//			board.addCard(new Card("12,12,12,12,12,12"));
//			board.addCard(new Card("12,12,12,12,12,12"));
//			board.addCard(new Card("12,12,12,12,12,12"));
//			board.addCard(new Card("12,12,12,12,12,12"));
//			board.addCard(new Card("12,12,12,12,12,12"));
		 
		 updateHand(game.getPlayers().get(game.getPlayerViewOwnerIndex()).getDrawableHand());
		 updateBoard(game.getPlayers().get(game.getPlayerViewOwnerIndex()).getDrawableBoard());		 
		 
		 ArrayList<Boolean> bool = new ArrayList<Boolean>();
		 bool.add(true);
		 bool.add(false);
		 bool.add(false);
		 bool.add(true);
		 bool.add(false);
		 bool.add(false);
		 
		 gameRound.setGreyRound(bool);
		 
		 sendInformationMessage("Tour1");
		 
		 
			
			
			if(game!=null){
				//int nbPlayer = game.getPlayers().size();
				int nbPlayer = 4;
				
				board.setLayout(new GridLayout(2,nbPlayer-1));
				
				if(nbPlayer >1)
				{
					otherPlayerBoard = new ArrayList<>();
					for(int i = 0;i<nbPlayer-1;i++){
						BoardCard otherPlayer = new BoardCard(6, 2, 0, 0, 0, 0, Board.WIDTH_CARD_BOARD,Board.HEIGHT_CARD_BOARD, 
								Board.POSITION_X_BOARD, Board.POSITION_Y_BOARD);
						otherPlayer.addMouseMotionListener(this);
						otherPlayer.addMouseListener(this);
						otherPlayerBoard.add(otherPlayer);
						
						board.add(otherPlayer);
					}
				
				}
				
				board.add(playerBoard);
			}
			
		 
	 } else if(e.getSource()==item_quit){
		System.exit(0); 
	 }
		 
	
}

public void updatePoolPV(int pv) {
	
}

public void updateHand(IDrawableHand hand){
	this.hand.updateCards(hand);
}

public void updateBoard(IDrawableBoard board){
	playerBoard.updateBoard(board);
}

public void updateGameRound(ArrayList<Boolean> bool) {
	gameRound.setGreyRound(bool);
	
}

public void sendInformationMessage(String str) {
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

@Override
public void itemStateChanged(ItemEvent e) {
	if(e.getSource()==item_fullScreen){
		fullScreen(((JCheckBoxMenuItem)e.getSource()).getState());
	}
	
}



}