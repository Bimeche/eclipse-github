package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelArtiste extends JPanel{
	JLabel infoArtiste;
	public PanelArtiste(String s){
		setBackground(Color.WHITE);
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(loweredetched);
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350,25));
		infoArtiste = new JLabel(s);
		this.add(BorderLayout.WEST, infoArtiste);		
		
	}

}
