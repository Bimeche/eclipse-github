package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MaFenetre extends JFrame{
	static boolean log = false;
	private JTabbedPane Onglets = null;
	private JPanel container;
	static PanelAcc jp1 = new PanelAcc();
    private PanelRech jp2 = new PanelRech();
    private PanelComp jp3 = new PanelComp();
    private PanelPlay jp4 = new PanelPlay();
    private PanelData jp5 = new PanelData();
	
	public MaFenetre(int x, int y, int l, int h){
		try {
 		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
 		        if ("Nimbus".equals(info.getName())) {
 		            UIManager.setLookAndFeel(info.getClassName());
 		            break;
 		        }
 		    }
 		} catch (Exception e) {}
		
		JScrollPane scrollPane = new JScrollPane(jp1);
		setTitle("Music Comparator");
		setBounds(x,y,l,h);
		container = new JPanel();
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());
        
        /* LES ONGLETS */
		 Onglets = new JTabbedPane();
		 Onglets.addTab("Accueil", null, jp1);
		 Onglets.addTab("Recherche", null , jp2);
		 Onglets.addTab("Comparaison", null ,jp3);
		 Onglets.addTab("Playlist", null, jp4);
		 Onglets.addTab("Données", null, jp5);
		 //Onglets.setEnabledAt(4, false);
		
		 
	     container.add(Onglets, null);
	     this.setContentPane(container);
	     this.setVisible(true);
		
	}
	

}
