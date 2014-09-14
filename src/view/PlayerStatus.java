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
	private ArrayList<String> nameList;
	private int buttonSelected = 0;
	
	private Button validate_Button;
	private JLabel nbPV;
	
	private Image statusImage;
	
	public PlayerStatus(int nbButton) {
//		this.setPreferredSize(new Dimension(200, 400));
		this.setLayout(new GridLayout(12,1));
		
		bg = new ButtonGroup();
		brList = new ArrayList<>();
		nameList = new ArrayList<>();
		nameList.add("Explorer +5");
		nameList.add("Explorer +1+1");
		nameList.add("Développer");
		nameList.add("Coloniser");
		nameList.add("Consommer-Vendre");
		nameList.add("Consommer-x2");
		nameList.add("Produire");
		
		for(int i = 0 ;i<nbButton;i++){
			JRadioButton rb = new JRadioButton(nameList.get(i));
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
	
	public void setActionListenerRadioButton(ActionListener a){
		for(JRadioButton radio : brList){
			radio.addActionListener(a);
		}
	}
	
	public void setVisibleTrueValidateButton(boolean b){
		validate_Button.setVisible(b);
	}
	
	public void cleanRadioButton(){
		bg.clearSelection();
	}
	
	public void setNbPV(int pv){
		nbPV.setText(""+pv);
	}

	public Button getValidate_Button() {
		return validate_Button;
	}

	public ArrayList<JRadioButton> getBrList() {
		return brList;
	}
	
	public void setButtonSelected(int i){
		buttonSelected = i;
	}
	
	public int getButtonSelected(){
		return buttonSelected;
	}
	


}
