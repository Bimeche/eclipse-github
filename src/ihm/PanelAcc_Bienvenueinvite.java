package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelAcc_Bienvenueinvite extends JPanel{
	public PanelAcc_Bienvenueinvite(){
	
		new JPanel();
		setLayout(new BorderLayout());
		JPanel pBContent = new JPanel();
		pBContent.setLayout(new BorderLayout());
			pBContent.setPreferredSize(new Dimension(400, 300));
			TitledBorder title;
			Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			title = BorderFactory.createTitledBorder(loweredetched,"Bienvenue");
			
			pBContent.setBorder(title);


		/* Boutons du panel Bienvenue */
		JPanel pBButton = new JPanel();
		JButton bConnexion = new JButton("Connexion");
		JButton bInscription = new JButton("Inscription");
		pBButton.add(bConnexion);
		pBButton.add(bInscription);
		
		this.add(pBContent, BorderLayout.CENTER);
		this.add(pBButton, BorderLayout.SOUTH);
		
		/*On ajoute un listener sur le bouton Inscription */
		bInscription.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
				PanelAcc.pInscription.setVisible(true);
			}
		});
	
		bConnexion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				FenetreConnexion fenetre_co = new FenetreConnexion();
				fenetre_co.setVisible(true);
			}
		});

	}
	
}
