package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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

// Menu
private JMenuBar menuBar = new JMenuBar();
private JMenu menu_File = new JMenu("Fichier");
private JMenu menu_Game = new JMenu("Jeu");

private JMenuItem item_new = new JMenuItem("Nouvelle Partie");
private JMenuItem item_join = new JMenuItem("Rejoindre une Partie");
private JMenuItem item_quit = new JMenuItem("Quitter");



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
	
	initFrame();
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

private void initFrame(){
	this.setLayout(new BorderLayout());
	this.getContentPane().add(board,BorderLayout.CENTER);
	this.getContentPane().add(stats,BorderLayout.EAST);
	
	this.setJMenuBar(menuBar);

}
 
//Implémentation du pattern observer
public void update(String str) {

}

@Override
public void actionPerformed(ActionEvent e) {
	 if(e.getSource()==item_join){
		 
	 } else if(e.getSource()==item_new){
		 
	 } else if(e.getSource()==item_quit){
		System.exit(0); 
	 }
		 
	
}  

}