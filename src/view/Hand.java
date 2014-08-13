package view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Card;

public class Hand extends JPanel{

	private ArrayList<GraphicCard> cards = new ArrayList<GraphicCard>();
	
	public Hand() {
		// TODO Auto-generated constructor stub
	}
	
	public Hand(Image image){
		cards.add(new GraphicCard(image, 0, 50, Board.WIDTH_CARD_HAND, Board.HEIGHT_CARD_HAND));
		this.add(new JLabel("Test"));
	}
		
	public void paintComponent(Graphics g) {
		for (Iterator iter = cards.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}
	}
	
	public void updateCards(ArrayList<Card> cards){
		int x = Board.POSITION_X_HAND;
		int y = Board.POSITION_y_HAND;		
		for(Card card : cards){
			GraphicCard gCard = new GraphicCard(card.getImage(), x, y, Board.WIDTH_CARD_HAND, Board.HEIGHT_CARD_HAND);
			this.cards.add(gCard);
			x = x + Board.WIDTH_CARD_HAND;
		}
		repaint();
	}
}
