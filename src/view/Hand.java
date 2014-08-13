package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;





import model.Card;

public class Hand extends JPanel implements MouseListener, MouseMotionListener{

	private ArrayList<GraphicCard> cards = new ArrayList<GraphicCard>();
	
	private int flagCard = -1; 
	private boolean focusACard;
	private float ratio = (float) 1.25; //ratio de hauteur lors de la selection de la carte
	
	public Hand() {
		this.setPreferredSize(new Dimension(10*Board.WIDTH_CARD_HAND, (int)(Board.HEIGHT_CARD_HAND*ratio)));
		
		this.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
	}
	
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Iterator iter = cards.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}
	}
	
	public void updateCards(ArrayList<Card> cards){
		int x = Board.POSITION_X_HAND;
		int y = Board.POSITION_y_HAND;		
		for(Card card : cards){
			GraphicCard gCard = new GraphicCard(card.getImage(), x, (int)(y+(ratio-1)*Board.HEIGHT_CARD_HAND), Board.WIDTH_CARD_HAND, Board.HEIGHT_CARD_HAND);
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
		flagCard = -1; // mise a jour du flag pour le reperage de la carte survolee
		
		// "Abaissement de la carte"
		downAllCards();
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {		
		// reperage de la carte survolee
		focusACard = false;
		for(GraphicCard card : cards){
			int index = cards.indexOf(card);
			
			if(card.getRectangle().contains(e.getPoint())){
				focusACard = true;
				if(flagCard!=index ){
					flagCard = index;
					upFocusedCard();
				}
			}
		}
		
		if(!focusACard){
			flagCard = -1;
			downAllCards();
		}
		
		
		
	}
	
	/**
	 * "leve" la carte sur laquelle le curseur de la souris est posée
	 */
	public void upFocusedCard(){
		for(GraphicCard gc : cards){
			if(cards.indexOf(gc)==flagCard){
				gc.setY(Board.POSITION_y_HAND);
				
			} else {
				gc.setY((int)(Board.POSITION_y_HAND+(ratio-1)*Board.HEIGHT_CARD_HAND));
			}
		}
		this.repaint();
	}
	
	public void downAllCards(){
		for(GraphicCard gc : cards){
				gc.setY((int)(Board.POSITION_y_HAND+(ratio-1)*Board.HEIGHT_CARD_HAND));
		}
		this.repaint();
	}
	
	
	public GraphicCard getFocusedCard(){
		if(flagCard>-1){
			return cards.get(flagCard);
		} else return null;
		
	}
	
	public int getFlag(){
		return flagCard;
	}
	
	public boolean getFocusACard(){
		return focusACard;
	}

}
