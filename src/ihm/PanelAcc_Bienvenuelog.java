package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelAcc_Bienvenuelog extends JPanel {
	public PanelAcc_Bienvenuelog(){

		new JPanel();
		setLayout(new BorderLayout());
		JPanel pBContent = new JPanel();
		setVisible(false);
		pBContent.setLayout(new BorderLayout());
			pBContent.setPreferredSize(new Dimension(400, 300));
			TitledBorder title;
			Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			title = BorderFactory.createTitledBorder(loweredetched,"Bienvenue "+PanelAcc.login);
			
			pBContent.setBorder(title);


		/* Boutons du panel Bienvenue */
		JPanel pBButton = new JPanel();
		JButton bDeconnexion = new JButton("Deconnexion");
		pBButton.add(bDeconnexion);
		
		this.add(pBContent, BorderLayout.CENTER);
		this.add(pBButton, BorderLayout.SOUTH);
		
	
		bDeconnexion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				PanelAcc.pBienvenuelog.setVisible(false);
				PanelAcc.pBienvenue.setVisible(true);
			}
		});

	}
		
	}


