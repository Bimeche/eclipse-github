package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import poo.init;
import reserv.Client;

public class FenetreConnexion extends JFrame{
	private boolean success = false;
	public FenetreConnexion(){
		try {
 		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
 		        if ("Nimbus".equals(info.getName())) {
 		            UIManager.setLookAndFeel(info.getClassName());
 		            break;
 		        }
 		    }
 		} catch (Exception e) {}
		
		new JFrame("Connexion");
		setVisible(false);
		Toolkit tk=Toolkit.getDefaultToolkit();;
		Dimension dim = tk.getScreenSize();
		setBounds((dim.width)/4+100, (dim.height)/4+100, 300,200);
	    
	    JTextField fieldlogin = new JTextField(); 
		JPasswordField fieldmdp = new JPasswordField();
	    fieldlogin.setPreferredSize(new Dimension(150,25));
	    fieldmdp.setPreferredSize(new Dimension(150,25));
		JLabel login = new JLabel("Pseudo :");
		JLabel passwd = new JLabel("Mot de Passe :");
	
	    /*Ã©lÃ©ments de connexion*/
	    JPanel pco = new JPanel();
	    JButton seconnecter = new JButton("Valider");
	    JButton annuler = new JButton("Annuler");
		pco.setPreferredSize(new Dimension(300,200));
		pco.setLayout(new GridBagLayout());
		
		//DÃ©finition des contraintes du GridBagLayout
		GridBagConstraints gc2 = new GridBagConstraints();
		   
		gc2.anchor = GridBagConstraints.FIRST_LINE_START;
		gc2.fill = GridBagConstraints.HORIZONTAL;
		gc2.gridx = GridBagConstraints.RELATIVE;
		gc2.gridy = 0;
		gc2.gridwidth = 2;
		gc2.gridy++;
		gc2.gridwidth = 1;
		pco.add(login,gc2);
		pco.add(fieldlogin,gc2);
		   
		gc2.gridy++;
		pco.add(passwd, gc2);
		pco.add(fieldmdp, gc2);
		gc2.gridy++;
		gc2.gridy++;
		pco.add(annuler,gc2);
		pco.add(seconnecter,gc2);
		
		// Récupération des clients pour comparaison au clic sur 'valider'
		ArrayList<Client> cli = init.recuperer_clients();
		
		annuler.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				fieldlogin.setText("");
				fieldmdp.setText("");
				dispose();
			}
		});
		seconnecter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				
				int i=0;
				String pass = new String(fieldmdp.getPassword());
                String login_cl= fieldlogin.getText().trim();
				Iterator<Client> it = cli.iterator();
				Client c = null, tmp;
				while(it.hasNext()){
					tmp = it.next();
					if(tmp.getPseudo().equals(login_cl)) c=tmp;
				}
				
				HashMap<String,String> hmLoginPass = new HashMap<String,String>();
                hmLoginPass.put(c.getPseudo(),c.getMdp());
                if(!hmLoginPass.containsKey(login_cl)||!hmLoginPass.containsValue(pass)){
                	JOptionPane.showMessageDialog(pco, "Le pseudo ou le mot de passe est incorrect", "Erreur lors de la connexion", JOptionPane.ERROR_MESSAGE);
					fieldmdp.setText("");
                }else if(hmLoginPass.get(login_cl).equals(pass)){
                    MaFenetre.log = true;
    				fieldlogin.setText("");
    				fieldmdp.setText("");
    				dispose();
    				PanelAcc.pBienvenue.setVisible(false);
    				PanelAcc.login = login_cl;
    				PanelAcc.pBienvenuelog = new PanelAcc_Bienvenuelog();
    				MaFenetre.jp1.add(PanelAcc.pBienvenuelog);
    				PanelAcc.pBienvenuelog.setVisible(true);
                }
			}
		});
		    
		this.add(pco);
	}
}

