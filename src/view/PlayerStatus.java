package view;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	
	public PlayerStatus(int nbButton) {
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
