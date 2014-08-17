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
import model.drawable.IDrawableCard;

public abstract class AbstractPanelCard extends JPanel implements MouseListener, MouseMotionListener{

	protected ArrayList<GraphicCard> cards = new ArrayList<GraphicCard>();
	protected int flagCard = -1;
	protected boolean focusACard;
	
	protected int nbCardbyRow = 6;
	protected int nbRow = 2;
	protected int marge_ext_x = 20;
	protected int marge_int_x = 10;
	protected int marge_ext_y = 10;
	protected int marge_int_y = 80;
	
	protected int scale_width = Board.WIDTH_CARD_HAND;
	protected int scale_height = Board.HEIGHT_CARD_HAND;
	protected int position_X = Board.POSITION_X_HAND;
	protected int position_y = Board.POSITION_Y_HAND;
	
	protected int widthPanel;
	protected int heightPanel;
	
	public AbstractPanelCard(int nbCardbyRow, int nbRow, int marge_ext_x, int marge_int_x,
			int marge_ext_y, int marge_int_y, int scale_width, int scale_height, int position_X, int position_Y) {
		this.nbCardbyRow = nbCardbyRow;
		this.nbRow = nbRow;
		this.marge_ext_x = marge_ext_x;
		this.marge_int_x = marge_int_x;
		this.marge_ext_y = marge_ext_y;
		this.marge_int_y = marge_int_y;
		this.scale_width = scale_width;
		this.scale_height = scale_height;
		this.position_X = position_X;
		this.position_y = position_Y;
		
		widthPanel = position_X + marge_ext_x*2 + nbCardbyRow*scale_width + marge_int_x*(nbCardbyRow-1);
		heightPanel = position_Y + marge_ext_y*2 + nbRow*scale_height + marge_int_y*(nbRow-1);
		this.setPreferredSize(new Dimension(widthPanel,heightPanel));
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Iterator iter = cards.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}
	}
	
	
	// Met à jour toute la main avec une liste de cartes
	public void updateCards(ArrayList<IDrawableCard> cards){
		this.cards.clear();
		int x = position_X + marge_ext_x;
		int y = position_y + marge_ext_y;		
		for(IDrawableCard card : cards){
			GraphicCard gCard = new GraphicCard(card.getImageId(), x, y, scale_width, scale_height);
			this.cards.add(gCard);
			if(x == position_X + marge_ext_x + (scale_width + marge_int_x)*(nbCardbyRow - 1) && y==position_y + marge_ext_y){
				x = position_X - scale_width - marge_int_x + marge_ext_x ;
				y = y + scale_height + marge_int_y;
			}
			x = x + scale_width + marge_int_x;
			
		}
		repaint();
	}
	
	// Met à jour l'affichage des cartes (apres une modification)
	private void updateGraphicsCards(){
		int x = position_X + marge_ext_x;
		int y = position_y + marge_ext_y;				
		for(GraphicCard gc : cards){
			gc.setX(x);
			gc.setY(y);
			if(x == position_X + marge_ext_x + (scale_width + marge_int_x)*(nbCardbyRow - 1) && y==position_y + marge_ext_y){
				x = position_X - scale_width - marge_int_x + marge_ext_x ;
				y = y + scale_height + marge_int_y;
			}
			x = x + scale_width + marge_int_x;
		}
		repaint();
	}
	
	// Ajoute un certain nombre de carte à la vue
	public void addCards(ArrayList<Card> cards){
		for(Card c : cards){
			this.cards.add(new GraphicCard(c.getImageId(), 0, 0, scale_width, scale_height));
		}
		updateGraphicsCards();
	}
	
	// Enleve un certain nombre de carte à la vue
	public void removeCards(ArrayList<Integer> index){
		ArrayList<GraphicCard> remove = new ArrayList<GraphicCard>();
		for(Integer i : index){
			remove.add(cards.get(i));
		}
		cards.removeAll(remove);
		updateGraphicsCards();
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

	public int getWidthPanel() {
		return widthPanel;
	}

	public int getHeightPanel() {
		return heightPanel;
	}
}
