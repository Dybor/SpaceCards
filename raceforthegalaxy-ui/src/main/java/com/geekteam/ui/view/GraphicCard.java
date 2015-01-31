package com.geekteam.ui.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.geekteam.core.model.drawable.IDrawableCard;

public class GraphicCard implements IDrawable {

	private Image image;
	private int x;
	private int y;
	private int width;
	private int height;
	private int id;
	private String path;

	private int goodColor;
	private final boolean hasGood;
	private final boolean isOnBoard;

	private final boolean isSelected;
	private final boolean isDiscarded;
	private final boolean isDrawed;

	public GraphicCard(final int id, final String path, final int x,
			final int y, final int width, final int height,
			final boolean hasGood, final int goodColor,
			final boolean isOnBoard, final boolean isDiscarded,
			final boolean isSelected, final boolean isDrawed) {

		this.path = path;
		try {
			this.image = ImageIO
					.read(getClass().getResourceAsStream(this.path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.goodColor = goodColor;
		this.isOnBoard = isOnBoard;
		this.isDiscarded = isDiscarded;
		this.hasGood = hasGood;
		this.isSelected = isSelected;
		this.isDrawed = isDrawed;
	}

	@Override
	public void draw(final Graphics gg) {
		// g.drawImage(scaleImage(image,width,height),x,y,width,height,null);
		Graphics2D g = (Graphics2D) gg;
		g.drawImage(GraphicsTools.scaleImage(image, width, height), x, y,
				width, height, null);

		if (isOnBoard && hasGood) {
			switch (goodColor) {
			case IDrawableCard.ALIEN_TECHNOLOGY:
				g.setColor(Color.YELLOW);
				break;
			case IDrawableCard.GENES:
				g.setColor(Color.GREEN);
				break;
			case IDrawableCard.NOVELTY_GOODS:
				g.setColor(Color.CYAN);
				break;
			case IDrawableCard.RARE_ELEMENTS:
				g.setColor(new Color(157, 62, 12));
				break;
			}
			Stroke stroke = new BasicStroke(4);
			g.setStroke(stroke);
			g.drawRect(x, y, width, height);
		}

		if (isSelected) {
			Stroke stroke = new BasicStroke(4);
			g.setStroke(stroke);
			g.setColor(Color.GREEN);
			g.drawRect(x, y, width, height);
		}

		if (isDrawed) {
			Stroke stroke = new BasicStroke(2);
			g.setStroke(stroke);
			g.setColor(Color.lightGray);
			g.drawRect(x, y, width, height);
		}

		if (isDiscarded) {
			drawKeepDiscardble(g);
		}

	}

	public void drawIsDiscarded(final Graphics2D g) {
		int x1 = x + width / 4;
		int x2 = x + width * 4 / 12;
		int x3 = x + width * 5 / 12;
		int x4 = x + width / 2;
		int x5 = x + width * 7 / 12;
		int x6 = x + width * 8 / 12;
		int x7 = x + width * 9 / 12;

		int y_ref = y + (height - width) / 2;

		int y1 = y_ref + width / 4;
		int y2 = y_ref + width * 4 / 12;
		int y3 = y_ref + width * 5 / 12;
		int y4 = y_ref + width / 2;
		int y5 = y_ref + width * 7 / 12;
		int y6 = y_ref + width * 8 / 12;
		int y7 = y_ref + width * 9 / 12;

		int[] xPoints = { x2, x4, x6, x7, x5, x7, x6, x4, x2, x1, x3, x1 };
		int[] yPoints = { y1, y3, y1, y2, y4, y6, y7, y5, y7, y6, y4, y2 };
		g.setColor(Color.RED);
		Stroke stroke = new BasicStroke(4);
		g.setStroke(stroke);
		g.drawPolygon(xPoints, yPoints, xPoints.length);
	}

	public void drawKeepDiscardble(final Graphics2D g) {
		// if(keepDiscardable){
		// int x1 = x + width/4;
		// int x2 = x + width*4/12;
		// int x3 = x + width*5/12;
		// int x4 = x + width*8/12;
		// int x5 = x + width*9/12;
		//
		// int y_ref = y + (height - width) /2;
		//
		// int y1 = y_ref + width/4;
		// int y2 = y_ref + width*4/12;
		// int y3 = y_ref + width*6/12;
		// int y4 = y_ref + width*7/12;
		// int y5 = y_ref + width*8/12;
		// int y6 = y_ref + width*9/12;
		//
		// int[] xPoints = {x2,x3,x4,x5,x3,x1};
		// int[] yPoints = {y3,y4,y1,y2,y6,y4};
		// g.setColor(Color.GREEN);
		// Stroke stroke = new BasicStroke(4);
		// g.setStroke(stroke);
		// g.drawPolygon(xPoints, yPoints, xPoints.length);
		// } else {
		//
		// int x1 = x + width/4;
		// int x2 = x + width*4/12;
		// int x3 = x + width*5/12;
		// int x4 = x + width/2;
		// int x5 = x + width*7/12;
		// int x6 = x + width*8/12;
		// int x7 = x + width*9/12;
		//
		// int y_ref = y + (height - width) /2;
		//
		// int y1 = y_ref + width/4;
		// int y2 = y_ref + width*4/12;
		// int y3 = y_ref + width*5/12;
		// int y4 = y_ref + width/2;
		// int y5 = y_ref + width*7/12;
		// int y6 = y_ref + width*8/12;
		// int y7 = y_ref + width*9/12;
		//
		// int[] xPoints = {x2,x4,x6,x7,x5,x7,x6,x4,x2,x1,x3,x1};
		// int[] yPoints = {y1,y3,y1,y2,y4,y6,y7,y5,y7,y6,y4,y2};
		// g.setColor(Color.RED);
		// Stroke stroke = new BasicStroke(4);
		// g.setStroke(stroke);
		// g.drawPolygon(xPoints, yPoints, xPoints.length);
		// }
	}

	// Utilise pour reperer la taille de l'image afin de pouvoir cliquer dessus.
	@Override
	public Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(final Image image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public int getGoodColor() {
		return goodColor;
	}

	public void setGoodColor(final int goodColor) {
		this.goodColor = goodColor;
	}

	public boolean isHasGood() {
		return hasGood;
	}
}
