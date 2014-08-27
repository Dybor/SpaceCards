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
import model.drawable.IDrawableBoard;
import model.drawable.IDrawableCard;
import model.drawable.IDrawableHand;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener{

	private ArrayList<GraphicCard> playerBoard = new ArrayList<GraphicCard>();
	private ArrayList<GraphicCard> hand = new ArrayList<GraphicCard>();
	private ArrayList<ArrayList<GraphicCard>> otherPlayerBoard = new ArrayList<>();
	private GraphicCard zoomCard ;
	
	private int flagHandCard = -1;
	private int flagPlayerBoardCard = -1;
	private ArrayList<Integer> flagOtherPlayerBoardCard = new ArrayList<>();
	private boolean focusACard;
	
	private int nbPlayer = 1;
	
	
	// Player Board
	
	private int position_X_P = 0;
	private int position_Y_P = 0;
	private int nbCardbyRow_P = 6;
	private int nbRow_P = 2;
	private int scale_width_P = Board.WIDTH_CARD_BOARD;
	private int scale_height_P = Board.HEIGHT_CARD_BOARD;
	private int marge_ext_x_P = 20;
	private int marge_int_x_P = 10;
	private int marge_ext_y_P = 10;
	private int marge_int_y_P = 30;
	
	// Other Player Board
	
	private int position_X_OP = 0;
	private int position_Y_OP = 0;
	private int nbCardbyRow_OP = 6;
	private int nbRow_OP = 2;
	private int scale_width_OP = Board.WIDTH_CARD_BOARD;
	private int scale_height_OP = Board.HEIGHT_CARD_BOARD;
	private int marge_ext_x_OP = 20;
	private int marge_int_x_OP = 10;
	private int marge_ext_y_OP = 10;
	private int marge_int_y_OP = 30;
	
	// Hand
	
	private int position_X_H = 0;
	private int position_Y_H = 0;
	private int nbCardbyRow_H = 30;
	private int nbRow_H = 1;
	private int scale_width_H = Board.WIDTH_CARD_HAND;
	private int scale_height_H = Board.HEIGHT_CARD_HAND;
	private int marge_ext_x_H = 20;
	private int marge_int_x_H = 10;
	private int marge_ext_y_H = 10;
	private int marge_int_y_H = 80;
	
	private int dyn_marge_int_x_H = 0;
	
	
	private int width_Screen = 0;
	private int height_Screen = 0;

	
	private int widthPanel;
	private int heightPanel;
	
	public GamePanel(int nbPlayer, int widthScreen, int heightScreen) {
		this.nbPlayer = nbPlayer;
		this.width_Screen = widthScreen;
		this.height_Screen = heightScreen;
		
		position_Y_H = height_Screen - scale_height_H;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		for (int i = 0;i<nbPlayer;i++){
			flagOtherPlayerBoardCard.add(-1);
		}
		
//		widthPanel = position_X + marge_ext_x*2 + nbCardbyRow*scale_width + marge_int_x*(nbCardbyRow-1);
//		heightPanel = position_Y + marge_ext_y*2 + nbRow*scale_height + marge_int_y*(nbRow-1);
//		this.setPreferredSize(new Dimension(widthPanel,heightPanel));
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		updateHand();
		updatePlayerBoard();
		updateOtherPlayerBoard();
		
		//affiche les cartes du plateau joueur
		for (Iterator iter = playerBoard.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}
		
		//affiche les cartes de la main du joueur
		for (Iterator iter = hand.iterator(); iter.hasNext();) {
			IDrawable d = (IDrawable) iter.next();
			d.draw(g);	
		}

		//affiche les cartes des plateaux des autres joueurs
		for (Iterator iter = otherPlayerBoard.iterator(); iter.hasNext();) {
			for(Iterator iter2 = (Iterator) iter.next(); iter.hasNext();){
				IDrawable d = (IDrawable) iter2.next();
				d.draw(g);	
			}
			
		}
		
		if (zoomCard != null){
			zoomCard.draw(g);
		}
		
	}
	
	
	// Met à jour toute la main avec une liste de cartes
	public void updateCards(IDrawableHand hand){
		this.hand.clear();		
		for(IDrawableCard card : hand.getCards()){
			GraphicCard gCard = new GraphicCard(card.getImageId(),card.getImagePath(), 0, 0, scale_width_H, scale_height_H,card.getGoodColor(),false,false,true);
			this.hand.add(gCard);
	
		}
		
		updateHand();
		
		repaint();
	}
	
	
	
	// Met à jour toute le plateau du joueur avec une liste de cartes
	public void updatePlayerBoard(IDrawableBoard board){
		this.playerBoard.clear();	
		for(IDrawableCard card : board.getCards()){
			GraphicCard gCard = new GraphicCard(card.getImageId(),card.getImagePath(), 0, 0, scale_width_P, scale_height_P,card.getGoodColor(),true,false,false);
			this.playerBoard.add(gCard);
		}
		updatePlayerBoard();
		repaint();
	}
	
	// Met à jour toute le plateau du joueur avec une liste de cartes
	public void updateOtherPlayerBoard(ArrayList<IDrawableBoard> list_board){
		this.otherPlayerBoard.clear();
		for (IDrawableBoard board : list_board){
			ArrayList<GraphicCard> list = new ArrayList<>();
			
			for(IDrawableCard card : board.getCards()){
				GraphicCard gCard = new GraphicCard(card.getImageId(),card.getImagePath(), 0, 0, scale_width_OP, scale_height_OP,card.getGoodColor(),true,false,false);
				list.add(gCard);
			}
			this.otherPlayerBoard.add(list);
		}	
		updateOtherPlayerBoard();
		repaint();
	}
		
		
	
		
	// Met à jour l'affichage des cartes de la main joueur(apres une modification)
	private void updateHand(){
		position_Y_H = height_Screen - scale_height_H - 2*marge_ext_y_H; // Permet de raffraichir la position des cartes en fonction de la taille du JPanel si redimensionnement
		
		int width_hand = hand.size()*Board.WIDTH_CARD_HAND + 2*marge_ext_x_H + marge_int_x_H*(hand.size()-1); // taille total de la main en X
		
		// Dynamise le chevauchement en fonction du nombre de carte en main
		if(width_hand > width_Screen){
			//Il y a chevauchement, on calcule la marge negative a appliquer
			position_X_H = 0;
			dyn_marge_int_x_H = (width_Screen - (hand.size()+1)*Board.WIDTH_CARD_HAND + 2*marge_ext_x_H)/(hand.size());
			
		} else {
			position_X_H = (width_Screen - width_hand) / 2 ; 
			dyn_marge_int_x_H = marge_int_x_H;
		}
		
		
		int x = position_X_H + marge_ext_x_H;
		int y = position_Y_H + marge_ext_y_H;				
		for(GraphicCard gc : hand){
			gc.setX(x);
			gc.setY(y);
			if(x == position_X_H + marge_ext_x_H + (scale_width_H + dyn_marge_int_x_H)*(nbCardbyRow_H - 1) && y==position_Y_H + marge_ext_y_H){
				x = position_X_H - scale_width_H - dyn_marge_int_x_H + marge_ext_x_H ;
				y = y + scale_height_H + marge_int_y_H;
			}
			x = x + scale_width_H + dyn_marge_int_x_H;
		}
		repaint();
	}
	
	// Met à jour l'affichage des cartes du plateau du joueur(apres une modification)
	private void updatePlayerBoard(){
		
		int widthPlayerBoard = 2*marge_ext_x_P + (nbCardbyRow_P-1)*marge_int_x_P + nbCardbyRow_P*Board.WIDTH_CARD_BOARD;
		position_X_P = (width_Screen - widthPlayerBoard)/2;
		
		int heightPlayerBoard = 2*marge_ext_y_P + (nbRow_P-1)*marge_int_y_P + nbRow_P*Board.HEIGHT_CARD_BOARD;
		position_Y_P = position_Y_H - heightPlayerBoard;
		
		int x = position_X_P + marge_ext_x_P;
		int y = position_Y_P + marge_ext_y_P;				
		for(GraphicCard gc : playerBoard){
			gc.setX(x);
			gc.setY(y);
			if(x == position_X_P + marge_ext_x_P + (scale_width_P + marge_int_x_P)*(nbCardbyRow_P - 1) && y==position_Y_P + marge_ext_y_P){
				x = position_X_P - scale_width_P - marge_int_x_P + marge_ext_x_P ;
				y = y + scale_height_P + marge_int_y_P;
			}
			x = x + scale_width_P + marge_int_x_P;
		}
		repaint();
	}
	
	private void updateOtherPlayerBoard(){
		int widthPlayerBoard = 2*marge_ext_x_P + (nbCardbyRow_P-1)*marge_int_x_P + nbCardbyRow_P*Board.WIDTH_CARD_BOARD;
		int heightPlayerBoard = 2*marge_ext_y_P + (nbRow_P-1)*marge_int_y_P + nbRow_P*Board.HEIGHT_CARD_BOARD;
		
		position_X_OP = 0;
		position_Y_OP = 0;
		
		for(ArrayList<GraphicCard> list : otherPlayerBoard){
			
			int x = position_X_OP + marge_ext_x_OP;
			int y = position_Y_OP + marge_ext_y_OP;		
			for(GraphicCard gc : list){
				gc.setX(x);
				gc.setY(y);
				if(x == position_X_OP + marge_ext_x_OP + (scale_width_OP + marge_int_x_OP)*(nbCardbyRow_OP - 1) && y==position_Y_OP + marge_ext_y_OP){
					x = position_X_OP - scale_width_OP - marge_int_x_OP + marge_ext_x_OP ;
					y = y + scale_height_P + marge_int_y_OP;
				}
				x = x + scale_width_OP + marge_int_x_OP;
			}
			
			position_X_OP = position_X_OP + widthPlayerBoard;
		}
		
		
		repaint();
	}
	
	private void updateZoomCard(GraphicCard card){
		zoomCard = new GraphicCard(card.getId(), card.getPath(),0,0,Board.WIDTH_CARD_ZOOM,Board.HEIGHT_CARD_ZOOM,card.getGoodColor(),false,false,false);
		
		float ratio = 0.75f;
		int max_X = width_Screen;
		int max_Y = height_Screen;
		
		int marge_X = 5;
		int marge_Y = 5;
		
		int card_X = card.getX();
		int card_Y = card.getY();
		
		int x = card_X - (Board.WIDTH_CARD_ZOOM - card.getWidth())/2;
		
		int y = (int)( card_Y - Board.HEIGHT_CARD_ZOOM + card.getHeight()*ratio);
		
		// Par defaut s'affiche a droite du curseur
		if(x < 0) {
			x = card_X;
		} else if (x + Board.WIDTH_CARD_ZOOM > max_X){
			x = card_X + card.getWidth() - Board.WIDTH_CARD_ZOOM; 
		} 
		
		if(y < 0){
			y = (int)(card_Y + card.getHeight()*(1-ratio));
		} 
		
		zoomCard.setX(x);
		zoomCard.setY(y);
		repaint();
	}

	
	
	
//	// Ajoute un certain nombre de carte à la vue
//	public void addCards(ArrayList<Card> cards){
//		for(Card c : cards){
//			this.playerBoard.add(new GraphicCard(c.getImageId(),c.getImagePath(), 0, 0, scale_width, scale_height));
//		}
//		updateGraphicsCards();
//	}
//	
//	// Enleve un certain nombre de carte à la vue
//	public void removeCards(ArrayList<Integer> index){
//		ArrayList<GraphicCard> remove = new ArrayList<GraphicCard>();
//		for(Integer i : index){
//			remove.add(playerBoard.get(i));
//		}
//		playerBoard.removeAll(remove);
//		updateGraphicsCards();
//	}
//		
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// reperage de la carte survolee
				focusACard = false;
				
				for(GraphicCard card : hand){
					int index = hand.indexOf(card);
					
					if(card.getRectangle().contains(e.getPoint())){
						focusACard = true;
						if(flagHandCard!=index ){
							flagHandCard = index;
							updateZoomCard(card);
						}
					}
				}
				
				for(GraphicCard card : playerBoard){
					int index = playerBoard.indexOf(card);
					
					if(card.getRectangle().contains(e.getPoint())){
						focusACard = true;
						if(flagPlayerBoardCard!=index ){
							flagPlayerBoardCard = index;
							updateZoomCard(card);
						}
					}
				}
				
				for(int i = 0 ; i<otherPlayerBoard.size();i++){
					for(GraphicCard card : otherPlayerBoard.get(i)){
						int index = otherPlayerBoard.get(i).indexOf(card);
						
						if(card.getRectangle().contains(e.getPoint())){
							focusACard = true;
							if(flagOtherPlayerBoardCard.get(i)!=index ){
								flagOtherPlayerBoardCard.set(i,index);
								updateZoomCard(card);
							}
						}
					}
				}
				
				if(!focusACard){
					flagHandCard = -1;
					flagPlayerBoardCard = -1;
					flagOtherPlayerBoardCard.clear();
					for (int i = 0;i<nbPlayer;i++){
						flagOtherPlayerBoardCard.add(-1);
					}
					zoomCard = null;
					repaint();
				}
				
				
				
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
		if(flagHandCard>-1){
			return hand.get(flagHandCard);
		} else if(flagPlayerBoardCard>-1){
			return playerBoard.get(flagPlayerBoardCard);
		} 
		
		for(int i = 0 ; i<flagOtherPlayerBoardCard.size();i++){
			int index = flagOtherPlayerBoardCard.get(i);
			if (index>-1){
				return otherPlayerBoard.get(i).get(index);
			}
		}
		
		return null;
	}
	
	public boolean isFocusedCardInHand(){
		return flagHandCard>-1;
	}
	
	public boolean isFocusedCardOnPlayerBoard(){
		return flagPlayerBoardCard>-1;
	}

	public boolean isFocusedCardOnOtherPlayerBoard(){
		for(int i = 0 ; i<flagOtherPlayerBoardCard.size();i++){
			int index = flagOtherPlayerBoardCard.get(i);
			if (index>-1){
				return true;
			}
		}
		
		return false;
	}
	
	public int getWidthPanel() {
		return widthPanel;
	}

	public int getHeightPanel() {
		return heightPanel;
	}

	public void setWidth_Screen(int width_Screen) {
		this.width_Screen = width_Screen;
	}

	public void setHeight_Screen(int height_Screen) {
		this.height_Screen = height_Screen;
	}
}
