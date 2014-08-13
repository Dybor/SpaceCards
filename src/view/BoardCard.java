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

public class BoardCard extends AbstractPanelCard{

	public BoardCard() {
		super(6,20,10,10,80,Board.WIDTH_CARD_BOARD,Board.HEIGHT_CARD_BOARD,Board.POSITION_X_BOARD,Board.POSITION_Y_BOARD); //nbcard,ext_x,int_x,ext_y,int_y
		this.setPreferredSize(new Dimension(8*Board.WIDTH_CARD_BOARD, Board.HEIGHT_CARD_BOARD*3));
		
		this.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		flagCard = -1; // mise a jour du flag pour le reperage de la carte survolee
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
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
						}
					}
				}
				
				if(!focusACard){
					flagCard = -1;
				}
	}

}
