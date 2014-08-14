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

	public BoardCard(int nbCardbyRow, int nbRow, int marge_ext_x, int marge_int_x,
			int marge_ext_y, int marge_int_y, int scale_width, int scale_height, int position_X, int position_Y){
		super(nbCardbyRow, nbRow, marge_ext_x, marge_int_x ,marge_ext_y, 
				marge_int_y, scale_width,scale_height, position_X, position_Y);
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
