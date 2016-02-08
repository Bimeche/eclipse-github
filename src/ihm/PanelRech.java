package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import poo.Genre;
import poo.Sous_style;
import poo.Style;
import poo.init;

public class PanelRech extends JPanel{
	static int date;
	public PanelRech(){
		new JPanel();
		setLayout(new FlowLayout());
		JPanel pBContent = new JPanel();
		pBContent.setLayout(new FlowLayout());
		pBContent.setPreferredSize(new Dimension(400, 300));
		TitledBorder title;
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title = BorderFactory.createTitledBorder(loweredetched,"Recherche");
		pBContent.setBorder(title);
		JRadioButton song_box = new JRadioButton("Chanson", true);
		JRadioButton art_box = new JRadioButton("Artiste");
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(song_box);
		bg.add(art_box);
		JPanel boutons = new JPanel();
		boutons.setPreferredSize(new Dimension(350, 50));
		
		boutons.add(song_box);
		boutons.add(art_box);
		
		JPanel recherche = new JPanel();
		recherche.setLayout(new GridLayout(1,2,15,5));
		recherche.setPreferredSize(new Dimension(350, 200));
		JPanel rech_l = new JPanel();
		rech_l.setLayout(new GridLayout(4,2,-75,10));
		rech_l.setPreferredSize(new Dimension(150, 200));
		JPanel rech_d = new JPanel();
		rech_d.setLayout(new GridLayout(3,2,-65,40));
		rech_d.setPreferredSize(new Dimension(100, 200));
		
		JTextField fieldtitle = new JTextField(8);
		JLabel titre = new JLabel("Titre ");
		JTextField fieldart = new JTextField(8);
		JLabel art = new JLabel("Artiste ");
		JComboBox fieldstyle = new JComboBox();
		JLabel style = new JLabel("Style ");
		JComboBox fieldtheme = new JComboBox();
		JLabel theme = new JLabel("Theme ");
		JTextField fieldalbum = new JTextField(8);
		JLabel album = new JLabel("Album ");
		JTextField fieldduree = new JTextField(8);
		JLabel duree = new JLabel("Duree ");
		JComboBox fieldannee = new JComboBox();
		JLabel annee = new JLabel("Annee ");
		
		ArrayList<Genre> styles = init.recuperer_arbre();
		ArrayList<Style> styles1 = init.recuperer_styles();
		ArrayList<Sous_style> styles2 = init.recuperer_sousstyles();
		ArrayList<String> themes = init.recuperer_themes();
		for(Genre g : styles){
			fieldstyle.addItem(g.get_nomG());
		}
		for(Style g : styles1){
			fieldstyle.addItem(g.get_nomS());
		}
		for(Sous_style g : styles2){
			fieldstyle.addItem(g.get_nomSS());
		}
		for(String t : themes){
			fieldtheme.addItem(t);
		}
		
		Calendar cal = Calendar.getInstance();
		date = cal.get(Calendar.YEAR);
		
		for(int i=0; i<151; i++){
			fieldannee.addItem(date-i);
		}
		
		rech_l.add(titre);
		rech_l.add(fieldtitle);
		rech_l.add(art);
		rech_l.add(fieldart);
		rech_l.add(duree);
		rech_l.add(fieldduree);
		rech_l.add(album);
		rech_l.add(fieldalbum);
		
		rech_d.add(style);
		rech_d.add(fieldstyle);
		rech_d.add(theme);
		rech_d.add(fieldtheme);
		rech_d.add(annee);
		rech_d.add(fieldannee);
		
		recherche.add(rech_l);
		recherche.add(rech_d);
		
		
//		recherche.add(rech_l, FlowLayout.LEFT);
//		recherche.add(rech_d);
		
//		rech_l.setLayout(new BorderLayout());
//		rech_d.setLayout(new BorderLayout());
		
		pBContent.add(boutons, BorderLayout.NORTH);
		pBContent.add(recherche, BorderLayout.CENTER);
		
		this.add(BorderLayout.CENTER,pBContent);
	}
}
