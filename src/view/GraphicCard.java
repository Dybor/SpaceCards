package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;


public class GraphicCard implements IDrawable{

	private Image image ;
	private int x;
	private int y;
	private int width;
	private int height;
	
	
	public GraphicCard(Image image, int x, int y, int width, int height) {
		this.image=image;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	@Override
	public void draw(Graphics g) {
		//g.drawImage(scaleImage(image,width,height),x,y,width,height,null);
		g.drawImage(scaleImage(image,width,height),x,y,width,height,null);
	}

	/** 
	 * Redimensionne une image.
	 * 
	 * @param source Image à redimensionner.
	 * @param width Largeur de l'image cible.
	 * @param height Hauteur de l'image cible.
	 * @return Image redimensionnée.
	 */
	public Image scaleImage(Image source, int width, int height){
		 /* On crée une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	 
	    /* On dessine sur le Graphics de l'image bufferisée. */
	    Graphics2D g2d = buf.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.drawImage(source, 0, 0, width, height, null);
	    g2d.dispose();
	 
	    /* On retourne l'image bufferisée, qui est une image. */
	    return buf;
	}

	// Utilise pour reperer la taille de l'image afin de pouvoir cliquer dessus.
	public Rectangle getRectangle() {
		return new Rectangle(x,y,width,height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
