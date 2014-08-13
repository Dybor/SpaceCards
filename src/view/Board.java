package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class Board extends JFrame implements Observer, ActionListener, MouseListener{


//L'instance de notre objet contrôleur
private AbstractControler controler;

private JPanel board;
private JPanel stats;

private JLabel label_PV;
private JLabel label_nbBuilding;
private JLabel label_nbCard;

private Hand hand;

// Menu
private JMenuBar menuBar = new JMenuBar();
private JMenu menu_File = new JMenu("Fichier");
private JMenu menu_Game = new JMenu("Jeu");

private JMenuItem item_new = new JMenuItem("Nouvelle Partie");
private JMenuItem item_join = new JMenuItem("Rejoindre une Partie");
private JMenuItem item_quit = new JMenuItem("Quitter");


public static int POSITION_X_HAND = 0;   // position de départ de la main du joueur en x 
public static int POSITION_y_HAND = 0; // position de départ de la main du joueur en y
public static int WIDTH_CARD_HAND = 120; // largeur d'une carte dans la main du joueur
public static int HEIGHT_CARD_HAND = 200;// hauteur d'une carte dans la main du joueur
public static int WIDTH_CARD_ZOOM = 240; // largeur d'une carte zoomée
public static int HEIGHT_CARD_ZOOM = 400;// hauteur d'une carte zoomée

// Test Model
private ArrayList<Card> cards = new ArrayList<Card>();


public Board(AbstractControler controler){                
  this.setSize(1900, 1000);
  this.setTitle("Race For The Galaxy");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  initComposant();                
  this.controler = controler;                
  this.setVisible(true);
}

private void initComposant(){
	initBoard();
	initStats();
	initMenu();
	initHands();
	
	
	
	initFrame();
	initTestModel();
}   

private void initBoard(){
	board = new JPanel();
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
}

private void initFrame(){
	this.setLayout(new BorderLayout());
	this.getContentPane().add(board,BorderLayout.CENTER);
	this.getContentPane().add(stats,BorderLayout.EAST);
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
	
}


@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 
	 } else if(e.getSource()==item_new){
		 updateCards(cards);
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



@Override
public void mouseClicked(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseClicked(e);
	}
	
}

@Override
public void mouseEntered(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseEntered(e);
	}
}

@Override
public void mouseExited(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseEntered(e);
	}
}

@Override
public void mousePressed(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mousePressed(e);
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	if(e.getComponent().equals(hand)){
		hand.mouseReleased(e);
	}
}


}