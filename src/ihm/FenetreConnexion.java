package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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
    
    JTextField fieldmail2 = new JTextField(); 
	JPasswordField fieldmdp2 = new JPasswordField();
    fieldmail2.setPreferredSize(new Dimension(150,25));
    fieldmdp2.setPreferredSize(new Dimension(150,25));
	JLabel mail2 = new JLabel("Mail :");
	JLabel passwd2 = new JLabel("Mot de Passe :");

    	/*éléments de connexion*/
    	JPanel pco = new JPanel();
    	JButton seconnecter = new JButton("Valider");
    	JButton annuler = new JButton("Annuler");
		pco.setPreferredSize(new Dimension(300,200));
		pco.setLayout(new GridBagLayout());
		
		//Définition des contraintes du GridBagLayout
		GridBagConstraints gc2 = new GridBagConstraints();
	    JPanel glue = new JPanel();
	    final JLabel errormessage = new JLabel("Les identifiants sont incorrects");	    
	    errormessage.setVisible(false);
	    errormessage.setForeground(Color.RED);
	    
		gc2.anchor = GridBagConstraints.FIRST_LINE_START;
	    gc2.fill = GridBagConstraints.HORIZONTAL;
	    gc2.gridx = GridBagConstraints.RELATIVE;
	    gc2.gridy = 0;
	    gc2.gridwidth = 2;
	    pco.add(errormessage, gc2);
	    gc2.gridy++;
	    gc2.gridwidth = 1;
	    pco.add(mail2,gc2);
	    pco.add(fieldmail2,gc2);
	    
	    gc2.gridy++;
	    pco.add(passwd2, gc2);
	    pco.add(fieldmdp2, gc2);
	    gc2.gridy++;   pco.add(glue,gc2);
	    gc2.gridy++;
	    pco.add(annuler,gc2);
	    pco.add(seconnecter,gc2);
	    annuler.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				dispose();
			}
	    });
	    seconnecter.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(true){
				MaFenetre.log = true;
				dispose();
				PanelAcc.pBienvenue.setVisible(false);
				PanelAcc.pBienvenuelog.setVisible(true);
				}else {
					errormessage.setVisible(true);
				}
			}
		});
	    
	this.add(pco);
	}
}

