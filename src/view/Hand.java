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

public class Hand extends AbstractPanelCard{

	private float ratio = (float) 1.25; //ratio de hauteur lors de la selection de la carte
	
	public Hand() {
		super(10,1,0,0,0,0,Board.WIDTH_CARD_HAND, Board.HEIGHT_CARD_HAND,Board.POSITION_X_HAND,Board.POSITION_Y_HAND); //nbcard,ext_x,int_x,ext_y,int_y
		
		this.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
		
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
				gc.setY((int)(Board.POSITION_Y_HAND-(ratio-1)*Board.HEIGHT_CARD_HAND));				
			} else {

				gc.setY(Board.POSITION_Y_HAND);
			}
		}
		this.repaint();
	}
	
	public void downAllCards(){
		for(GraphicCard gc : cards){
			gc.setY(Board.POSITION_Y_HAND);
		}
		this.repaint();
	}
	
}
