package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ProgressBar extends JPanel{
	private float valeur;
	private int hauteur = 20;
	private int longueur = 200;
	private JPanel barre;
	
	public ProgressBar(){
		Border loweredbevel;
		loweredbevel = BorderFactory.createLoweredBevelBorder();
		setLayout(new BorderLayout());
		setBorder(loweredbevel);
		this.setPreferredSize(new Dimension(longueur,hauteur));
		barre = new JPanel();
		
	}

	public void setProgressBar(float f){
		if(f<0.2)			barre.setBackground(new Color(255,9,33));
		if(f<0.4 && f>=0.2)	barre.setBackground(new Color(255,127,0));
		if(f<0.6 && f>=0.4)	barre.setBackground(new Color(254,163,71));
		if(f<0.8 && f>=0.6)	barre.setBackground(new Color(254,248,108));
		if(f>=0.8) 			barre.setBackground(new Color(194,247,50));
		this.setPreferredSize(new Dimension(longueur,hauteur));
		barre.setPreferredSize(new Dimension((int)(200*f), 20));
		this.add(barre,BorderLayout.WEST);
	}

}