package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Card;
import model.Player;
import controler.AbstractControler;
import observer.Observer;

public class Board extends JFrame implements Observer, ActionListener{


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
public static int POSITION_y_HAND = 400; // position de départ de la main du joueur en y
public static int WIDTH_CARD_HAND = 120; // largeur d'une carte dans la main du joueur
public static int HEIGHT_CARD_HAND = 200;// hauteur d'une carte dans la main du joueur


// Test Model
private ArrayList<Card> cards = new ArrayList<Card>();


public Board(AbstractControler controler){                
  this.setSize(800, 600);
  this.setTitle("Race For The Galaxy");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  this.setResizable(false);
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
}

private void initFrame(){
	this.setLayout(new BorderLayout());
	this.getContentPane().add(board,BorderLayout.CENTER);
	this.getContentPane().add(stats,BorderLayout.EAST);
	this.getContentPane().add(hand,BorderLayout.SOUTH);
	
	
	this.setJMenuBar(menuBar);

}

private void initTestModel(){
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	Image image = toolkit.getImage("card1.png");
	this.getContentPane().add(new JLabel(createImageIcon("/Cards/card1.png", "descr")),BorderLayout.WEST);
	
	
	for(int i = 1;i<8;i++){
			cards.add(new Card(toolkit.getImage("card1.png")));
	}
	
}

/** Returns an ImageIcon, or null if the path was invalid. */
protected ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}


@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 
	 } else if(e.getSource()==item_new){
		 for(Card c : cards){
			 System.out.println(c.getImage() .getHeight(null));
		 }
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


}