package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class PanelResRech extends JPanel{
	JLabel infos;
	public boolean det_open = false;
	public PanelResRech(String s, JPanel p){
		setBackground(Color.WHITE);
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(loweredetched);
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350,75));
		infos = new JLabel(s);
//		JButton details = new JButton("V");
		JButton record = new JButton("Rec");
		
//		PanelResRech[] comp = p.getComponents();
//		Component elected;
//		for(Component c : comp){
//			if(c.det_open == true) elected = c;
//		}
		
//		details.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				if(already_opened(p.getComponents())){
//					which_opened(p.getComponents()).setVisible(false);
//				}
//				setVisible(true);
//				
//				
//			}
//			
//		});
		
		record.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
			}
			
		});
		
		
		
		
		this.add(BorderLayout.WEST, infos);
	}
	
//	public boolean already_opened(Component[] comp){
//		return false;
//	}
//	
//	public PanelResRech which_opened(Component[] comp){
//		return null;
//	}

}
