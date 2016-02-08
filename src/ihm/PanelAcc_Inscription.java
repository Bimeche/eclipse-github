package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import poo.init;
import reserv.Client;

public class PanelAcc_Inscription extends JPanel{
	public PanelAcc_Inscription(){
		new JPanel();
		setVisible(false);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(400, 500));

			
		/*Le panneau des diffÃ©rents champs*/
		JPanel panelins = new JPanel();
		panelins.setPreferredSize(new Dimension(300,300));
		panelins.setLayout(new GridBagLayout());
		//DÃ©finition des contraintes du GridBagLayout
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
				JLabel nom = new JLabel("Nom ");
				
			/* Champ de prÃ©nom*/
			JTextField fieldprenom = new JTextField(15);
				JLabel prenom = new JLabel("Prénom* ");
			
			/*Champ de Mail */
			JTextField fieldpseudo = new JTextField(15); 
				JLabel pseudo = new JLabel("Pseudo* ");

			/*Champ de MDP*/
			JPasswordField fieldmdp = new JPasswordField(15); 
				JLabel passwd = new JLabel("Mot de Passe* ");

			/* On place les Ã©lÃ©ments correctement grÃ¢ce au GridBagLayout */
			panelins.add(nom,gc);
			gc.gridwidth = GridBagConstraints.REMAINDER;
			gc.fill = GridBagConstraints.HORIZONTAL;
		    panelins.add(fieldnom, gc);
		    gc.gridwidth = 1;
		    gc.gridy++;
		    panelins.add(prenom, gc);
		    panelins.add(fieldprenom, gc);
		    gc.gridy++;
		    panelins.add(pseudo, gc);
		    panelins.add(fieldpseudo, gc);
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
				fieldnom.setText("");
				fieldprenom.setText("");
				fieldpseudo.setText("");
				fieldmdp.setText("");
				fieldprenom.setBackground(Color.WHITE);
				fieldpseudo.setBackground(Color.WHITE);
				fieldmdp.setBackground(Color.WHITE);
				PanelAcc.pBienvenue.setVisible(true);
				PanelAcc.pInscription.setVisible(false);
			}
		});
		/* Vérification que l'adresse mail n'est pas déjà  utilisée */
		valider_ins_butt.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent a){
				fieldprenom.setBackground(Color.WHITE);
				fieldpseudo.setBackground(Color.WHITE);
				fieldmdp.setBackground(Color.WHITE);
				ArrayList<Client> cli = init.recuperer_clients();
				int i = 0;
				for(Client c : cli){
				if(c.verifier_pseudo(fieldpseudo.getText())) i++;
				}
				if(i==cli.size()&&!fieldprenom.getText().equals("")&&!fieldpseudo.getText().equals("")&&!fieldmdp.getPassword().equals("")){
					i++;
					String url = "jdbc:mysql://localhost/Projet_Poo";
					String login = "root";
					String passwd = "projet";
					Connection cn = null;
					Statement st = null;
					try{
						//Etape 1 : Chargement du driver
						Class.forName("com.mysql.jdbc.Driver");
						//Etape 2 : rÃ©cupÃ©ration de la connexion
						cn = (Connection) DriverManager.getConnection(url, login, passwd);
						//Etape 3 : CrÃ©ation d'un statement
						st = (Statement) cn.createStatement();
						String sql = "INSERT INTO Client " + "VALUES ('"+i+"', '"+fieldnom.getText()+"', '"+fieldprenom.getText()+"', '"+fieldpseudo.getText()+"', '"+new String(fieldmdp.getPassword())+"')";
						//Etape 4 : exÃ©cution requÃªte
						st.executeUpdate(sql);
						//Etape 5 : (Parcours Resultset)
					}catch(SQLException e){
						e.printStackTrace();
					}catch(ClassNotFoundException e){
						e.printStackTrace();
					}finally {
						try{
							// Etape 5 : libÃ©rer les ressources de la mÃ©moire
							cn.close();
							st.close();
						}catch(SQLException e){
							e.printStackTrace();
						}
					}
					String options[] = {"OK"};
					int n = JOptionPane.showOptionDialog(panelins, "Merci pour votre inscription", "Incription réussie", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
					if(n==0 || n==JOptionPane.CLOSED_OPTION ){
						System.out.println(fieldpseudo.getText());
						PanelAcc.login = fieldpseudo.getText();
						fieldnom.setText("");
						fieldprenom.setText("");
						fieldpseudo.setText("");
						fieldmdp.setText("");
						setVisible(false);
						MaFenetre.log = true;
						PanelAcc.pBienvenuelog = new PanelAcc_Bienvenuelog();
						MaFenetre.jp1.add(PanelAcc.pBienvenuelog);
						PanelAcc.pBienvenuelog.setVisible(true);
					}
					
				}else{
					if(fieldprenom.getText().equals("")&&fieldpseudo.getText().equals("")&&fieldmdp.getPassword().length==0){
						fieldprenom.setBackground(new Color(254,150,160));
						fieldpseudo.setBackground(new Color(254,150,160));
						fieldmdp.setBackground(new Color(254,150,160));
						JOptionPane.showMessageDialog(panelins, "Les champs prénom, pseudo et mot de passe sont vides", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
					}
					else if(fieldprenom.getText().equals("")&&fieldpseudo.getText().equals("")){
						fieldprenom.setBackground(new Color(254,150,160));
						fieldpseudo.setBackground(new Color(254,150,160));
						JOptionPane.showMessageDialog(panelins, "Les champs prénom et pseudo sont vides", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
					}
					else if(fieldprenom.getText().equals("")&&fieldmdp.getPassword().length==0){
						fieldprenom.setBackground(new Color(254,150,160));
						fieldmdp.setBackground(new Color(254,150,160));
						JOptionPane.showMessageDialog(panelins, "Les champs prénom et mot de passe sont vides", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
					}
					else if(fieldmdp.getPassword().length==0&&fieldpseudo.getText().equals("")){
						fieldpseudo.setBackground(new Color(254,150,160));
						fieldmdp.setBackground(new Color(254,150,160));
						JOptionPane.showMessageDialog(panelins, "Les champs pseudo et mot de passe sont vides", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
					}
					else{
						if(i!=cli.size()){
							fieldpseudo.setBackground(new Color(254,150,160));
							JOptionPane.showMessageDialog(panelins, "Le pseudo choisi est déjà utilisé", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
						}
						if(fieldprenom.getText().equals("")){
							fieldprenom.setBackground(new Color(254,150,160));
							JOptionPane.showMessageDialog(panelins, "Le champ prénom est vide", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
						}
						if(fieldpseudo.getText().equals("")){
							fieldpseudo.setBackground(new Color(254,150,160));
							JOptionPane.showMessageDialog(panelins, "Le champ pseudo est vide", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
						}
						if(fieldmdp.getPassword().length==0){
							fieldmdp.setBackground(new Color(254,150,160));
							JOptionPane.showMessageDialog(panelins, "Le champ mot de passe est vide", "Erreur lors de l'inscription", JOptionPane.ERROR_MESSAGE);
						}
					}
					fieldmdp.setText("");
				}
			}
		});
	}
}


