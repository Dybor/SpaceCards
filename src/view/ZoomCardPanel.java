package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ZoomCardPanel extends JPanel {
	
	private GraphicCard card;
	
	public ZoomCardPanel() {
		this.setPreferredSize(new Dimension(Board.WIDTH_CARD_ZOOM, Board.HEIGHT_CARD_ZOOM));
		this.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(card!=null){
			card.draw(g);
		}		
	}


	public void setCard(GraphicCard card) {
		this.card = card;
		repaint();
	}
	
	
}
