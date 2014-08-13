package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;




import model.Card;

public class Hand extends JPanel implements MouseListener{

	private ArrayList<GraphicCard> cards = new ArrayList<GraphicCard>();
	
	public Hand() {
		this.setPreferredSize(new Dimension(10*Board.WIDTH_CARD_HAND, Board.HEIGHT_CARD_HAND));
		
		this.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
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
	

	public void mouseClicked(MouseEvent e) {
	
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		for(GraphicCard card : cards){
			if(card.getRectangle().contains(e.getPoint())){
				card.setWidth(Board.WIDTH_CARD_ZOOM);
				card.setHeight(Board.HEIGHT_CARD_ZOOM);
			}
		}
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
