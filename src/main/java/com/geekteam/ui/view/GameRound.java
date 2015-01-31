package com.geekteam.ui.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameRound extends JPanel {

	private static final long serialVersionUID = -5487530607254800775L;
	private final List<Image> roundImage = new ArrayList<Image>();
	private List<Boolean> greyRound = new ArrayList<Boolean>(); // true =
	// gris�

	private int marge_ext_x = 0;
	private int marge_int_y = 0;
	private int marge_ext_y = 0;

	public GameRound(final int marge_ext_x, final int marge_int_y,
			final int marge_ext_y) {
		this.marge_ext_x = marge_ext_x;
		this.marge_int_y = marge_int_y;
		this.marge_ext_y = marge_ext_y;

		this.setPreferredSize(new Dimension(Board.WIDTH_ROUND,
				Board.HEIGHT_ROUND * 6));
		// this.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());

		initGameRound();
	}

	public void initGameRound() {
		// Chargement des Images
		for (int i = 1; i < 7; i++) {
			try {
				roundImage.add(ImageIO.read(getClass().getResourceAsStream(
						"/images/rounds/" + i + "_down.png")));
				greyRound.add(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		for (int i = 1; i < 7; i++) {
			try {
				roundImage.add(ImageIO.read(getClass().getResourceAsStream(
						"/images/rounds/" + i + "_up.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		int x = Board.POSITION_X_ROUND + marge_ext_x;
		int y = Board.POSITION_Y_ROUND + marge_ext_y;
		for (int i = 0; i < greyRound.size(); i++) {
			if (greyRound.get(i)) {
				g.drawImage(GraphicsTools.scaleImage(roundImage.get(i),
						Board.WIDTH_ROUND, Board.HEIGHT_ROUND), x, y,
						Board.WIDTH_ROUND, Board.HEIGHT_ROUND, null);
			} else {
				g.drawImage(GraphicsTools.scaleImage(roundImage.get(i + 6),
						Board.WIDTH_ROUND, Board.HEIGHT_ROUND), x, y,
						Board.WIDTH_ROUND, Board.HEIGHT_ROUND, null);
			}
			y = y + Board.HEIGHT_ROUND + marge_int_y;
		}
	}

	public void setGreyRound(final ArrayList<Boolean> greyR) {
		greyRound = greyR;
		repaint();
	}

}
