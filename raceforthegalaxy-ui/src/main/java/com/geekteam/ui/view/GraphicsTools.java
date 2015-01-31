package com.geekteam.ui.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class GraphicsTools {
	
	/** 
	 * Redimensionne une image.
	 * 
	 * @param source Image � redimensionner.
	 * @param width Largeur de l'image cible.
	 * @param height Hauteur de l'image cible.
	 * @return Image redimensionn�e.
	 */
	public static Image scaleImage(Image source, int width, int height){
		 /* On cr�e une nouvelle image aux bonnes dimensions. */
	    BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	 
	    /* On dessine sur le Graphics de l'image bufferis�e. */
	    Graphics2D g2d = buf.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2d.drawImage(source, 0, 0, width, height, null);
	    g2d.dispose();
	 
	    /* On retourne l'image bufferis�e, qui est une image. */
	    return buf;
	}
	
	public static Image greyImage(Image source){
		BufferedImage imagedst = new BufferedImage(source.getWidth(null), 
				source.getHeight(null), BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = imagedst.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return imagedst;
	}


}
