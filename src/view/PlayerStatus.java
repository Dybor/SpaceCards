package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PlayerStatus extends JPanel {
	
	private ButtonGroup bg;
	private ArrayList<JRadioButton> brList;
	
	private Button validate_Button;
	private JLabel nbPV;
	
	private Image statusImage;
	
	public PlayerStatus(int nbButton) {
//		this.setPreferredSize(new Dimension(200, 400));
		this.setLayout(new GridLayout(3,3));
		
		bg = new ButtonGroup();
		brList = new ArrayList<>();
		
		for(int i = 0 ;i<nbButton;i++){
			JRadioButton rb = new JRadioButton("Action : "+i);
			brList.add(rb);
			bg.add(rb);
			this.add(rb);
		}
		
		validate_Button = new Button("Valider");
		this.add(validate_Button);
		
		nbPV = new JLabel("0");
		this.add(nbPV);
		

//		try {
//			statusImage=ImageIO.read(new File("./data/images/status.jpg"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
		

		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(GraphicsTools.scaleImage(statusImage,245,206),0,0,245,206,null);
		
	}
	
	public void setActionListenerValidateButton(ActionListener a){
		validate_Button.addActionListener(a);
	}
	
	public void cleanRadioButton(){
		bg.clearSelection();
	}
	
	public void setNbPV(int pv){
		nbPV.setText(""+pv);
	}

}
