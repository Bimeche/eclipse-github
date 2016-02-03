package ihm;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import poo.Artiste;
import poo.Chanson;
import poo.Genre;
import poo.init;

public class PanelData extends JPanel{
	
	private JPanel container;
	private JPanel panel_chansons;
	private JPanel chansons;
	private JPanel panel_artistes;
	private JPanel artistes;
	private ArrayList<Genre> lg;
	private ArrayList<Chanson> lc;
	private ArrayList<Artiste> la;
	public PanelData(){
		// On récupère les arraylists
		lg = init.recuperer_arbre();
		lc = init.recuperer_chansons(lg);
		la = init.recuperer_artistes();
		
		container = new JPanel();
		/*--------- PANNEAU DE CHANSONS ------------ */
		panel_chansons = new JPanel();
		chansons = new JPanel();
		chansons.setLayout(new WrapLayout());
			TitledBorder title;
			Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
			title = BorderFactory.createTitledBorder(loweredetched,"Chansons");
			panel_chansons.setBorder(title);
			
		Collections.sort(lc,new Comparator(){

			@Override
			public int compare(Object a1, Object a2) {
				return ((Chanson)a1).titre.compareTo(((Chanson)a2).titre);	}
	 	});
		for(int j=0; j<lc.size(); j++){
			chansons.add(new PanelChanson(lc.get(j).getTitre() + " -  " + lc.get(j).getArtiste()));
		}		
		JScrollPane scrollChanson = new JScrollPane(chansons);
		scrollChanson.setPreferredSize(new Dimension(400,180));
		scrollChanson.setVisible(true);
		panel_chansons.add(scrollChanson);
		/*------------ PANNEAU D'ARTISTES ---------- */		
		panel_artistes = new JPanel();
		artistes = new JPanel();
		artistes.setLayout(new WrapLayout());
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder(loweredetched,"Artistes");
		panel_artistes.setBorder(title2);
		Collections.sort(la,new Comparator(){

			@Override
			public int compare(Object a1, Object a2) {
				return ((Artiste)a1).getNom().compareTo(((Artiste)a2).getNom());	}
	 	});
		for(int i=0; i<la.size(); i++){
			artistes.add(new PanelArtiste(la.get(i).getNom() + " -  " + la.get(i).getType()));
		}
		JScrollPane scrollArtiste = new JScrollPane(artistes);
		scrollArtiste.setPreferredSize(new Dimension(400,180));
		scrollArtiste.setVisible(true);
		panel_artistes.add(scrollArtiste);
		
		/*------------ PANNEAU CONTAINER ---------*/
		panel_chansons.setVisible(true);
		panel_artistes.setVisible(true);
		container.setPreferredSize(new Dimension(500,500));
		container.setLayout(new FlowLayout());
		container.add(panel_chansons);
		container.add(panel_artistes);
		container.setVisible(true);
		this.add(container);
	}

}
