package view;

import java.awt.BorderLayout;
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

import model.Card;
import controler.AbstractControler;
import observer.Observer;

public class Board extends JFrame implements Observer, ActionListener, MouseListener, MouseMotionListener{


//L'instance de notre objet contrôleur
private AbstractControler controler;

private JPanel board;


private JPanel eastSide;
private JPanel stats;
private ZoomCardPanel zoomCard;


private JLabel label_PV;
private JLabel label_nbBuilding;
private JLabel label_nbCard;

private Hand hand;
private BoardCard boardCard;
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
private ArrayList<Card> cards = new ArrayList<Card>();
private ArrayList<Card> cards2 = new ArrayList<Card>();


public Board(AbstractControler controler){                
  this.setTitle("Race For The Galaxy");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setExtendedState(this.MAXIMIZED_BOTH);
  
  this.setLocationRelativeTo(null);
  initComposant();                
  this.controler = controler;                
  this.setVisible(true);
}

private void initComposant(){
	initBoard();
	initStats();
	initZoomCard();
	initMenu();
	initHands();
	initGameRound();
	
	initEastSide();
	
	
	initTestModel();
	
	
	initFrame();
	
}   

private void initBoard(){
	board = new JPanel();
	boardCard = new BoardCard();
	boardCard.addMouseMotionListener(this);
	boardCard.addMouseListener(this);
	board.add(boardCard);
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

private void initEastSide(){
	eastSide = new JPanel();
	//eastSide.setLayout(new GridLayout(2, 1));
	//eastSide.add(stats);
	eastSide.add(zoomCard);
	eastSide.add(gameRound);
	
}

private void initFrame(){
	this.setLayout(new BorderLayout());
	this.getContentPane().add(board,BorderLayout.CENTER);
	this.getContentPane().add(eastSide,BorderLayout.EAST);
	this.getContentPane().add(hand,BorderLayout.SOUTH);
	
	
	this.setJMenuBar(menuBar);
}

private void initTestModel(){
	
	for(int i = 1;i<9;i++){
			try {
				cards.add(new Card(ImageIO.read(new File("./src/Cards/card"+i+".png"))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	for(int i = 8;i>0;i--){
		try {
			cards2.add(new Card(ImageIO.read(new File("./src/Cards/card"+i+".png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
}

	
	
}


@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 
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
	 } else if(e.getSource()==item_quit){
		System.exit(0); 
	 }
		 
	
}

@Override
public void updatePV(ArrayList<Integer> pv) {
	
}

public void updateCards(ArrayList<Card> cards){
	hand.updateCards(cards);
}

public void updateBoardCards(ArrayList<Card> cards){
	boardCard.updateCards(cards);
}

@Override
public void updateGameRound(ArrayList<Boolean> bool) {
	gameRound.setGreyRound(bool);
	
}



@Override
public void mouseClicked(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseClicked(e);
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mouseClicked(e);
	}
	
}

@Override
public void mouseEntered(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseEntered(e);
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mouseEntered(e);
	}
}

@Override
public void mouseExited(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseExited(e);
		zoomCard.setCard(null);
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mouseExited(e);
	}
}

@Override
public void mousePressed(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mousePressed(e);
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mousePressed(e);
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseReleased(e);
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mouseReleased(e);
	}
}

@Override
public void mouseDragged(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseDragged(e);
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mouseDragged(e);
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
	
	} else if(e.getComponent().equals(boardCard)){
		boardCard.mouseMoved(e);
		if(boardCard.getFocusedCard()!=null){
			zoomCard.setCard(new GraphicCard(boardCard.getFocusedCard().getImage(),0,0,Board.WIDTH_CARD_ZOOM, Board.HEIGHT_CARD_ZOOM));
		} else {
			zoomCard.setCard(null);
		}
	}
}




}