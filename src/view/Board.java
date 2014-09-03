package view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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







import model.Hand;
import model.drawable.IDrawableBoard;
import model.drawable.IDrawableHand;
import controler.GameControler;
import controler.INotifyView;
import controler.Observable;

public class Board extends JFrame implements ActionListener, MouseListener, MouseMotionListener, ItemListener, INotifyView, ComponentListener{


//L'instance de notre objet contrôleur
private GameControler controler;
private Observable game;

private GamePanel board;

private PlayerStatus pStatus;

private GameRound gameRound;

// Menu
private JMenuBar menuBar = new JMenuBar();
private JMenu menu_File = new JMenu("Fichier");
private JMenu menu_Game = new JMenu("Jeu");
private JMenu menu_Option = new JMenu("Options");

private JMenuItem item_new = new JMenuItem("Nouvelle Partie");
private JMenuItem item_join = new JMenuItem("Rejoindre une Partie");
private JMenuItem item_quit = new JMenuItem("Quitter");

private JMenuItem item_test = new JMenuItem("Piocher");

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
	initMenu();				//Initialisation de la barre de menu
	initGameRound();		//Initialisation des tours de jeu 
	initPlayerStatus();		//Initialisation du panneau de status du joueur
	
	initTestModel();
	
	
	initFrame();			//Initialisation de la frame
	
}   

private void initBoard(){
	board = new GamePanel(1,this.getWidth(),this.getHeight());
	board.addMouseListener(this);
	board.addMouseMotionListener(this);
}


private void initMenu(){
	item_new.addActionListener(this);
	item_join.addActionListener(this);
	item_quit.addActionListener(this);
	
	menu_File.add(item_new);
	menu_File.add(item_join);
	menu_File.add(item_quit);
	
	item_test.addActionListener(this);
	menu_Game.add(item_test);
	
	item_fullScreen.setState(false);
	item_fullScreen.addItemListener(this);
	menu_Option.add(item_fullScreen);
	
	menuBar.add(menu_File);
	menuBar.add(menu_Game);
	menuBar.add(menu_Option);
}


private void initGameRound(){
	gameRound = new GameRound(0, 0, 0);
	
}

private void initPlayerStatus(){
	pStatus = new PlayerStatus(7);
}


private void initFrame(){
	this.setLayout(new BorderLayout());
	this.add(board, BorderLayout.CENTER);
	this.add(gameRound, BorderLayout.WEST);
	
	this.setJMenuBar(menuBar);
	this.addComponentListener(this);
	
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

//*******Fonction Test ************ //

private void piocher(){
	int me = game.getPlayerViewOwnerIndex();
	 model.Hand hand = (Hand) game.getPlayers().get(me).getDrawableHand();
	 hand.addCard(hand.getCard(0));
	 updateHand(hand);
}

private void poserCarte(){
	int me = game.getPlayerViewOwnerIndex();
	model.Board board= (model.Board) game.getPlayers().get(me).getDrawableBoard();
	board.addCard(board.getCard(0));
	updateBoard(board);
}


@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 controler.launchGame();
	 } else if(e.getSource()==item_new){
		 
			model.Hand hand = new model.Hand();
		 
		 updateHand(game.getPlayers().get(game.getPlayerViewOwnerIndex()).getDrawableHand());
		 updateBoard(game.getPlayers().get(game.getPlayerViewOwnerIndex()).getDrawableBoard());		 
		 
		 ArrayList<Boolean> bool = new ArrayList<Boolean>();
		 bool.add(true);
		 bool.add(true);
		 bool.add(false);
		 bool.add(false);
		 bool.add(true);
		 bool.add(false);
		 
		 gameRound.setGreyRound(bool);
		 
		 sendInformationMessage("Tour1");
		 
		 refreshSizeGamePanel();
		 
	 } else if(e.getSource()==item_quit){
		System.exit(0); 
	 } else if(e.getSource()==item_test){
		 piocher();
	 }

}



public void refreshSizeGamePanel(){
	board.setWidth_Screen(board.getWidth());
	board.setHeight_Screen(board.getHeight());
	board.repaint();
}

public void updatePoolPV(int pv) {
	
}

public void updateHand(IDrawableHand hand){
	this.board.updateCards(hand);
}

public void updateBoard(IDrawableBoard board){
	this.board.updatePlayerBoard(board);
}

public void updateOtherBoard(ArrayList<IDrawableBoard> list){
	this.board.updateOtherPlayerBoard(list);
}

public void updateGameRound(ArrayList<Boolean> bool) {
	gameRound.setGreyRound(bool);
	
}

public void sendInformationMessage(String str) {
	board.setScreenMessage(str);
}





@Override
public void mouseClicked(MouseEvent e) {
//	if(e.getComponent().equals(board)){
//		board.mouseClicked(e);
//	} 
	
}

@Override
public void mouseEntered(MouseEvent e) {
//	if(e.getComponent().equals(board)){
//		board.mouseEntered(e);
//	} 
}

@Override
public void mouseExited(MouseEvent e) {
//	if(e.getComponent().equals(board)){
//		board.mouseExited(e);
//		zoomCard.setCard(null);
//	}
}

@Override
public void mousePressed(MouseEvent e) {
	if(e.getComponent().equals(board)){
		if(board.isFocusedCardInHand()){
			controler.handCardClicked(board.getFocusedCard().getId());
		} else if (board.isFocusedCardOnPlayerBoard()){
			controler.BoardCardClicked(board.getFocusedCard().getId());
		}
	} 
}

@Override
public void mouseReleased(MouseEvent e) {
//	if(e.getComponent().equals(board)){
//		board.mouseReleased(e);
//	} 
}

@Override
public void mouseDragged(MouseEvent e) {
//	if(e.getComponent().equals(board)){
//		board.mouseDragged(e);
//	} 
}

@Override
public void mouseMoved(MouseEvent e) {
//	if(e.getComponent().equals(board)){
//		board.mouseMoved(e);
//		if(board.getFocusedCard()!=null){
//			zoomCard.setCard(new GraphicCard(board.getFocusedCard().getImage(),0,0,Board.WIDTH_CARD_ZOOM, Board.HEIGHT_CARD_ZOOM));
//		} else {
//			zoomCard.setCard(null);
//		}
//	
//	} 
}

@Override
public void itemStateChanged(ItemEvent e) {
	if(e.getSource()==item_fullScreen){
		fullScreen(((JCheckBoxMenuItem)e.getSource()).getState());
	}
	
}

@Override
public void componentHidden(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void componentMoved(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void componentResized(ComponentEvent e) {
	refreshSizeGamePanel();
}

@Override
public void componentShown(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}



}