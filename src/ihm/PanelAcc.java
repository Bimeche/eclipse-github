package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelAcc extends JPanel{
	static PanelAcc_Bienvenueinvite pBienvenue;
	static PanelAcc_Inscription pInscription;
	static PanelAcc_Bienvenuelog pBienvenuelog;
	static String login = null;
	
	public PanelAcc(){
		/* ---------------------------------- PARTIE PANNEAU BIENVENUE INVITE------------------------------ */
		pBienvenue = new PanelAcc_Bienvenueinvite();
		this.add(pBienvenue);		
		/* ---------------------------------- PARTIE PANNEAU BIENVENUE INVITE------------------------------ */
		pBienvenuelog = new PanelAcc_Bienvenuelog();
		this.add(pBienvenuelog);	
		/* ------------------ PARTIE PANNEAU INSCRIPTION ------------------ */
		pInscription = new PanelAcc_Inscription();
		this.add(pInscription);
		
		/* ---------- FENETRE DE CONNEXION -----------*/
		
	}

}
