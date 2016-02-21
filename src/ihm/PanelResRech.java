package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import poo.Chanson;
import poo.Profil;
import poo.init;
import reserv.Client;

public class PanelResRech extends JPanel{
	public boolean det_open = false;
	public ArrayList<Client> cli;
	public Client elu;
	public PanelResRech(Chanson chanson, Profil p){
		JPanel infos = new JPanel();
		infos.setLayout(new BorderLayout());
		infos.setPreferredSize(new Dimension(300,75));
		JPanel haut = new JPanel();
		JPanel centre = new JPanel();
		JPanel bas = new JPanel();
		setBackground(infos.getBackground());
		Border loweredetched;
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(loweredetched);
		this.setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350,75));
		String s1 = "";
		String s2 = "";
		String s3 = "";
		if(chanson.getTitre()!=null) s1 += "Titre : " + chanson.getTitre();
		if(chanson.getArtiste()!=null){
			if(s1.equals("")) s1 += "Artiste : " + chanson.getArtiste();
			else s1 += " - Artiste : " + chanson.getArtiste();
		}
		if(chanson.getAlbum()!=null) s2 += "Album : " + chanson.getAlbum();
		if(chanson.getDuree()!=null){
			if(s2.equals("")) s2 += "Duree : " + chanson.getDuree();
			else s2 += " - Duree : " + chanson.getDuree();
		}
		if(chanson.getAnnee()!=0) s3 += "Annee : " + chanson.getAnnee();
		if(chanson.getTheme()!=null){
			if(s3.equals("")) s3 += "Theme : " + chanson.getTheme();
			else s3 += " - Theme : " + chanson.getTheme();
		}
		if(p.getSt1()==null){
			if(s3.equals("")) s3 += "Style : " + chanson.getSt1().get_nomS();
			else s3 += " - Style : " + chanson.getSt1().get_nomS();
		}
		else{
			if(chanson.getSt1()!=null && p.getSt1()!=null){
				if(p.getSt1().get_nomS().equals(chanson.getSt1())){
					if(s3.equals("")) s3 += "Style : " + chanson.getSt1().get_nomS();
					else s3 += " - Style : " + chanson.getSt1().get_nomS();
				}
			}
			if(chanson.getSt2()!=null && p.getSt1()!=null){
				if(p.getSt1().get_nomS().equals(chanson.getSt2())){
					if(s3.equals("")) s3 += "Style : " + chanson.getSt2().get_nomS();
					else s3 += " - Style : " + chanson.getSt2().get_nomS();
				}
			}
			if(chanson.getSt3()!=null && p.getSt1()!=null){
				if(p.getSt1().get_nomS().equals(chanson.getSt3())){
					if(s3.equals("")) s3 += "Style : " + chanson.getSt3().get_nomS();
					else s3 += " - Style : " + chanson.getSt2().get_nomS();
				}
			}
		}
		JLabel details1 = new JLabel(s1);
		JLabel details2 = new JLabel(s2);
		JLabel details3 = new JLabel(s3);
		haut.add(details1);
		centre.add(details2);
		bas.add(details3);
		infos.add(haut, BorderLayout.NORTH);
		infos.add(centre, BorderLayout.CENTER);
		infos.add(bas, BorderLayout.SOUTH);
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
		cli = init.recuperer_clients();
		elu = null;
		for(Client c : cli){
			if(c.getPseudo().equals(PanelAcc.login)) elu = new Client(c.getId(), c.getNom(), c.getPrenom(), c.getPseudo(), c.getMdp());
		}
		
		record.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
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
					String sql = "INSERT INTO playlist " + "VALUES ('" + elu.getId() +"', '" + chanson.getId() + "')";
					//Etape 4 : exÃ©cution requÃªte
					try{
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(infos, "Chanson enregistrée dans la playlist", "Enregistrement réussi", JOptionPane.PLAIN_MESSAGE);
						MaFenetre.Onglets.remove(3);
						MaFenetre.jp4 = new PanelPlay();
	    				MaFenetre.Onglets.addTab("Playlist", null, MaFenetre.jp4);
					}catch(MySQLIntegrityConstraintViolationException cve){
						JOptionPane.showMessageDialog(infos, "Cette chanson est déjà enregistrée dans votre playlist", "Chanson déjà existante", JOptionPane.ERROR_MESSAGE);
					}
					//Etape 5 : (Parcours Resultset)
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}catch(ClassNotFoundException cnfe){
					cnfe.printStackTrace();
				}finally {
					try{
						// Etape 5 : libÃ©rer les ressources de la mÃ©moire
						cn.close();
						st.close();
					}catch(SQLException sqle2){
						sqle2.printStackTrace();
					}
				}
			}
			
		});
		
		
		
		
		if(elu!=null) this.add(BorderLayout.EAST, record);
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
