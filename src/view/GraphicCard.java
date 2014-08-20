package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GraphicCard implements IDrawable{

	private Image image ;
	private int x;
	private int y;
	private int width;
	private int height;
	private int id;
	
	
	public GraphicCard(Image image, int x, int y, int width, int height) {
		this.image=image;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public GraphicCard(int id, int x, int y, int width, int height) {
		try {
			this.image=ImageIO.read(new File("./src/Cards/card"+id+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	
	@Override
	public void draw(Graphics g) {
		//g.drawImage(scaleImage(image,width,height),x,y,width,height,null);
		g.drawImage(GraphicsTools.scaleImage(image,width,height),x,y,width,height,null);
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
