package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelAcc_Inscription extends JPanel{
	public PanelAcc_Inscription(){
		new JPanel();
		setVisible(false);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 500));

			
		/*Le panneau des diff√©rents champs*/
		JPanel panelins = new JPanel();
		panelins.setPreferredSize(new Dimension(300,300));
		panelins.setLayout(new GridBagLayout());
		//D√©finition des contraintes du GridBagLayout
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc.fill = GridBagConstraints.HORIZONTAL;
	    gc.insets = new Insets(6, 6, 0, 0);
	    gc.gridx = GridBagConstraints.RELATIVE;
	    gc.gridy = 0;
		
		/* Pour la bordure du titre*/
		TitledBorder titleins;
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		titleins = BorderFactory.createTitledBorder(loweredetched,"Formulaire d'inscription");
		panelins.setBorder(titleins);
		
			/* Champ de nom*/
			JTextField fieldnom = new JTextField(15); 
				JLabel nom = new JLabel("Nom :");
				
			/* Champ de pr√©nom*/
			JTextField fieldprenom = new JTextField(15);
				JLabel prenom = new JLabel("*PrÈnom :");
			
			/*Champ de Mail */
			JTextField fieldmail = new JTextField(15); 
				JLabel mail = new JLabel("*Mail :");

			/*Champ de MDP*/
			JPasswordField fieldmdp = new JPasswordField(15); 
				JLabel passwd = new JLabel("*Mot de Passe :");

			/* On place les √©l√©ments correctement gr√¢ce au GridBagLayout */
			panelins.add(nom,gc);
			gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.fill = GridBagConstraints.HORIZONTAL;
		    panelins.add(fieldnom, gc);
		    gc.gridwidth = 1;
		    gc.gridy++;
		    panelins.add(prenom, gc);
		    panelins.add(fieldprenom, gc);
		    gc.gridy++;
		    panelins.add(mail, gc);
		    panelins.add(fieldmail, gc);
		    gc.gridy++;
		    panelins.add(passwd, gc);
		    panelins.add(fieldmdp, gc);				

		    JPanel glue = new JPanel();
		    gc.gridy++; panelins.add(glue,gc);
		    gc.gridy++; panelins.add(glue,gc);
		/* Les boutons de l'inscription */
		    JButton retour_ins = new JButton("Retour");
			JButton valider_ins_butt = new JButton("Valider");
			
			gc.anchor = GridBagConstraints.PAGE_END;
			gc.gridy++;
			panelins.add(retour_ins, gc);
			panelins.add(valider_ins_butt, gc);
		this.add(BorderLayout.NORTH, panelins);
		/* On ajoute des listeners sur les boutons */
		retour_ins.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				PanelAcc.pBienvenue.setVisible(true);
				PanelAcc.pInscription.setVisible(false);
			}
		});
		/* VÈrification que l'adresse mail n'est pas dÈj‡† utilisÈe */
		valider_ins_butt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(verifier_mail()) 
			}
			});
		}
	}


