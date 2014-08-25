package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.drawable.IDrawableCard;


public class GraphicCard implements IDrawable{

	private Image image ;
	private int x;
	private int y;
	private int width;
	private int height;
	private int id;
	private String path;
	
	private int goodColor;
	private boolean isOnBoard;
	
	public GraphicCard(int id, String path, int x, int y, int width, int height, int goodColor, boolean isOnBoard) {
		
		this.path = path;
		try {
			this.image=ImageIO.read(new File(this.path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(path);
		};
		
		this.id = id;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.goodColor=goodColor;
		this.isOnBoard=isOnBoard;
	}
	
	
	@Override
	public void draw(Graphics gg) {
		//g.drawImage(scaleImage(image,width,height),x,y,width,height,null);
		Graphics2D g = (Graphics2D) gg;
		g.drawImage(GraphicsTools.scaleImage(image,width,height),x,y,width,height,null);
		
		if(isOnBoard&&goodColor!=IDrawableCard.WITHOUT_GOOD){
			switch(goodColor){
			case IDrawableCard.ALIEN_TECHNOLOGY :
				g.setColor(Color.YELLOW);
			break;
			case IDrawableCard.GENES :
				g.setColor(Color.GREEN);
			break;
			case IDrawableCard.NOVELTY_GOODS :
				g.setColor(Color.CYAN);
			break;
			case IDrawableCard.RARE_ELEMENTS :
				g.setColor(new Color(56, 93, 82));
			break;
			}
			Stroke stroke = new BasicStroke(4);
			g.setStroke(stroke);
			g.drawRect(x, y, width, height);
		}
		
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public int getGoodColor() {
		return goodColor;
	}


	public void setGoodColor(int goodColor) {
		this.goodColor = goodColor;
	}
}
