package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import poo.Artiste;
import poo.Chanson;
import poo.Genre;
import poo.Profil;
import poo.init;
import reserv.Client;

public class PanelPlay extends JPanel{
	private ArrayList<Client> cli;
	private Client elu;
	private JPanel container;
	private JPanel panel_chansons;
	private JPanel chansons;
	private ArrayList<Chanson> pl;
	
	public PanelPlay(){
		cli = init.recuperer_clients();
		elu = null;
		for(Client c : cli){
			if(c.getPseudo().equals(PanelAcc.login)) elu = new Client(c.getId(), c.getNom(), c.getPrenom(), c.getPseudo(), c.getMdp());
		}
			
		container = new JPanel();
		/*--------- PANNEAU DE CHANSONS ------------ */
		panel_chansons = new JPanel();
		chansons = new JPanel();
		chansons.setLayout(new WrapLayout());
		TitledBorder title;
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title = BorderFactory.createTitledBorder(loweredetched,"Chansons");
		panel_chansons.setBorder(title);
		
		pl = init.recuperer_playlist(elu);
		if(pl!=null){
			for(int j=0; j<pl.size(); j++){
				chansons.add(new PanelChanson(pl.get(j).getTitre() + " -  " + pl.get(j).getArtiste()));
			}
		}
		
		JScrollPane scrollChanson = new JScrollPane(chansons);
		scrollChanson.setPreferredSize(new Dimension(400,180));
		scrollChanson.setVisible(true);
		panel_chansons.add(scrollChanson);

		

		
		/*------------ PANNEAU CONTAINER ---------*/
		panel_chansons.setVisible(true);
		container.setPreferredSize(new Dimension(500,500));
		container.setLayout(new FlowLayout());
		container.add(panel_chansons);
		container.setVisible(true);
		this.add(container);
	}
}