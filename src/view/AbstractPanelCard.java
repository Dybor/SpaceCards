package view;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import model.Card;

public abstract class AbstractPanelCard extends JPanel implements MouseListener, MouseMotionListener{

	protected ArrayList<GraphicCard> cards = new ArrayList<GraphicCard>();
	protected int flagCard = -1;
	protected boolean focusACard;
	
	protected int nbCardbyRow = 6;
	protected int marge_ext_x = 20;
	protected int marge_int_x = 10;
	protected int marge_ext_y = 10;
	protected int marge_int_y = 80;
	
	protected int scale_width = Board.WIDTH_CARD_HAND;
	protected int scale_height = Board.HEIGHT_CARD_HAND;
	protected int position_X = Board.POSITION_X_HAND;
	protected int position_y = Board.POSITION_Y_HAND;
	
	public AbstractPanelCard(int nbCardbyRow, int marge_ext_x, int marge_int_x,
			int marge_ext_y, int marge_int_y, int scale_width, int scale_height, int position_X, int position_Y) {
		this.nbCardbyRow = nbCardbyRow;
		this.marge_ext_x = marge_ext_x;
		this.marge_int_x = marge_int_x;
		this.marge_ext_y = marge_ext_y;
		this.marge_int_y = marge_int_y;
		this.scale_width = scale_width;
		this.scale_height = scale_height;
		this.position_X = position_X;
		this.position_y = position_Y;
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Iterator iter = cards.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}
	}
	
	
	
	public void updateCards(ArrayList<Card> cards){
		int x = position_X + marge_ext_x;
		int y = position_y + marge_ext_y;		
		for(Card card : cards){
			GraphicCard gCard = new GraphicCard(card.getImage(), x, y, scale_width, scale_height);
			this.cards.add(gCard);
			if(x == position_X + marge_ext_x + (scale_width + marge_int_x)*(nbCardbyRow - 1) && y==position_y + marge_ext_y){
				x = position_X - scale_width - marge_int_x + marge_ext_x ;
				y = y + scale_height + marge_int_y;
			}
			x = x + scale_width + marge_int_x;
			
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	public GraphicCard getFocusedCard(){
		if(flagCard>-1){
			return cards.get(flagCard);
		} else return null;
		
	}
}
