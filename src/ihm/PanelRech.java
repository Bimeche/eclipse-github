package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import poo.Artiste;
import poo.Chanson;
import poo.Genre;
import poo.Profil;
import poo.Style;
import poo.init;

public class PanelRech extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int date;
	static int nb_recherche = 0;
	private ArrayList<Chanson> result_songs = null;
	private ArrayList<Genre> styles1 = new ArrayList<Genre>();
	private ArrayList<Style> styles = new ArrayList<Style>();
	private JTextField fieldtitle;
	private JLabel titre;
	private JTextField fieldart;
	private JLabel art;
	private JComboBox fieldstyle;
	private JLabel style;
	private JComboBox fieldtheme;
	private JLabel theme;
	private JTextField fieldalb;
	private JLabel alb;
	private JComboBox fieldannee;
	private JLabel annee;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelRech(){
		new JPanel();
		setLayout(new FlowLayout());
		JPanel pBContent = new JPanel();
		pBContent.setLayout(new FlowLayout());
		pBContent.setPreferredSize(new Dimension(400, 400));
		TitledBorder title;
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title = BorderFactory.createTitledBorder(loweredetched,"Recherche");
		pBContent.setBorder(title);
		
		JPanel recherche_song = new JPanel();
		recherche_song.setLayout(new GridLayout(1,2,15,5));
		recherche_song.setPreferredSize(new Dimension(350, 250));
		recherche_song.setVisible(true);
		JPanel rech_l = new JPanel();
		rech_l.setLayout(new GridLayout(4,2,-70,30));
		rech_l.setPreferredSize(new Dimension(175, 200));
		JPanel rech_d = new JPanel();
		rech_d.setLayout(new GridLayout(3,2,-65,60));
		rech_d.setPreferredSize(new Dimension(175, 200));
		
		fieldtitle = new JTextField(8);
		titre = new JLabel("Titre ");
		fieldart = new JTextField(8);
		art = new JLabel("Artiste ");
		fieldstyle = new JComboBox();
		style = new JLabel("Style ");
		fieldtheme = new JComboBox();
		theme = new JLabel("Theme ");
		fieldalb = new JTextField(8);
		alb = new JLabel("Album ");
		fieldannee = new JComboBox();
		annee = new JLabel("Annee ");
		
		JPanel pan_duree = new JPanel();
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(59);
	    
	    formatter.setCommitsOnValidEdit(true);
	    JFormattedTextField fieldmin = new JFormattedTextField(formatter);
	    fieldmin.setPreferredSize(new Dimension(30,30));
		//JTextField fieldmin = new JTextField(2);
	    NumberFormat format1 = NumberFormat.getInstance();
	    NumberFormatter formatter1 = new NumberFormatter(format1);
	    formatter1.setValueClass(Integer.class);
	    formatter1.setMinimum(0);
	    formatter1.setMaximum(59);
	    
	    formatter1.setCommitsOnValidEdit(true);
	    JFormattedTextField fieldsec = new JFormattedTextField(formatter1);
	    fieldsec.setPreferredSize(new Dimension(30,30));
		//JTextField fieldsec = new JTextField(2);
		JLabel min = new JLabel("m");
		JLabel sec = new JLabel("s");
		JLabel duree = new JLabel("Duree ");
		
		styles1 = init.recuperer_arbre();

		ArrayList<String> themes = init.recuperer_themes();
		
		if(styles1!=null){
			styles.addAll(styles1);
			for(Genre g : styles1){
				styles.addAll(g.fils);
				for(Style s : g.fils){
					styles.addAll(s.fils);
				}
			}
		}
		
		
		Collections.sort(styles, new Comparator<Style>(){
			public int compare(Style r1, Style r2) {
				if(r1.get_nomS().compareToIgnoreCase(r2.get_nomS())>0) return 1;
				else if(r1.get_nomS().compareToIgnoreCase(r2.get_nomS())<0) return -1;
				return 0;
				}
			}
		);
		
		for(Style s : styles){
			fieldstyle.addItem(s.get_nomS());
		}
		
		for(String t : themes){
			fieldtheme.addItem(t);
		}
		
		Calendar cal = Calendar.getInstance();
		date = cal.get(Calendar.YEAR);
		
		for(int i=0; i<151; i++){
			fieldannee.addItem(date-i);
		}

		fieldstyle.setSelectedIndex(-1);
		fieldannee.setSelectedIndex(-1);
		fieldtheme.setSelectedIndex(-1);
		
		pan_duree.add(fieldmin);
		pan_duree.add(min);
		pan_duree.add(fieldsec);
		pan_duree.add(sec);
		
		rech_l.add(titre);
		rech_l.add(fieldtitle);
		rech_l.add(art);
		rech_l.add(fieldart);
		rech_l.add(alb);
		rech_l.add(fieldalb);
		rech_l.add(duree);
		rech_l.add(pan_duree);
		
		
		rech_d.add(style);
		rech_d.add(fieldstyle);
		rech_d.add(theme);
		rech_d.add(fieldtheme);
		rech_d.add(annee);
		rech_d.add(fieldannee);
		
		recherche_song.add(rech_l);
		recherche_song.add(rech_d);

		
//		JPanel recherche_art = new JPanel();
//		recherche_art.setLayout(new GridLayout(3,1,-65,60));
//		recherche_art.setPreferredSize(new Dimension(350, 250));
//		recherche_art.setVisible(false);
//		
//		JPanel rech_left = new JPanel();
//		JPanel rech_right = new JPanel();
//		rech_left.setLayout(new GridLayout(4,2,-70,30));
//		rech_left.setPreferredSize(new Dimension(175, 200));
//		rech_right.setLayout(new GridLayout(3,2,-65,60));
//		rech_right.setPreferredSize(new Dimension(175, 200));
//		
//		JTextField fieldchanson = new JTextField(8);
//		JLabel chanson = new JLabel("Chanson ");
//		JTextField fieldartiste = new JTextField(8);
//		JLabel artiste = new JLabel("Artiste ");
//		JTextField fieldalbum = new JTextField(8);
//		JLabel album = new JLabel("Album ");
//		
//		recherche_art.add(artiste);
//		recherche_art.add(fieldartiste);
//		recherche_art.add(chanson);
//		recherche_art.add(fieldchanson);
//		recherche_art.add(album);
//		recherche_art.add(fieldalbum);
		
		JPanel valid = new JPanel();
		valid.setPreferredSize(new Dimension(350, 50));
		
		JButton validate = new JButton("Rechercher");
		JButton reinit = new JButton("Réinitialiser");
		
		valid.add(reinit);
		valid.add(validate);
		
		
		JPanel resultat = new JPanel();
		JPanel panel_chansons;
		JPanel chansons;
		JButton bouton_back = new JButton("<- Retour à la recherche");
		
		panel_chansons = new JPanel();
		chansons = new JPanel();
		chansons.setLayout(new WrapLayout());
		TitledBorder title_res;
		Border loweredetched_res = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		title_res = BorderFactory.createTitledBorder(loweredetched_res,"Chansons");
		panel_chansons.setBorder(title_res);
		
		JScrollPane scrollChanson = new JScrollPane(chansons);
		scrollChanson.setPreferredSize(new Dimension(400,180));
		scrollChanson.setVisible(true);
		panel_chansons.add(scrollChanson);
	
		panel_chansons.setVisible(true);
		resultat.setPreferredSize(new Dimension(500,500));
		resultat.setLayout(new FlowLayout());
		resultat.add(panel_chansons);
		resultat.add(bouton_back, BorderLayout.SOUTH);
		resultat.setVisible(false);
		
		bouton_back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resultat.setVisible(false);
				pBContent.setVisible(true);
			}
			
		});
		
		
		reinit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				fieldart.setText("");
				fieldtitle.setText("");
				fieldalb.setText("");
				fieldmin.setText("");
				fieldsec.setText("");
				fieldstyle.setSelectedIndex(-1);
				fieldannee.setSelectedIndex(-1);
				fieldtheme.setSelectedIndex(-1);
			}
		});
		
		validate.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				chansons.removeAll();
				nb_recherche++;
				Style style_rech = null;
				
				if(fieldstyle.getSelectedItem()!=null){
					for(Genre g : styles1){
						if(fieldstyle.getSelectedItem().equals(g.get_nomG())) style_rech = new Genre(g.get_nomG(), g.getId());
						for(Style s : g.fils){
							if(fieldstyle.getSelectedItem().equals(s.get_nomS())) style_rech = new Style(s.get_nomS(), s.getId(), s.getPere());
							for(Style ss : s.fils ){
								if(fieldstyle.getSelectedItem().equals(ss.get_nomS())) style_rech = new Style(ss.get_nomS(), ss.getId(), ss.getPere());
							}
						}
					}
				}
				
				int an;
				if(fieldannee.getSelectedItem()!=null){
					an = (int)fieldannee.getSelectedItem();
					
				}
				else an = 0;
				Time ti;
				if(fieldmin.getText().isEmpty()&&fieldsec.getText().isEmpty()) ti = new Time(1000000000);
				else if(!fieldmin.getText().isEmpty()&&fieldsec.getText().isEmpty()) ti = new Time(((Integer.parseInt(fieldmin.getText()))*60)*1000);
				else if(fieldmin.getText().isEmpty()&&!fieldsec.getText().isEmpty()) ti = new Time((Integer.parseInt(fieldmin.getText()))*1000);
				else ti = new Time(((Integer.parseInt(fieldmin.getText())*60)+Integer.parseInt(fieldmin.getText()))*1000);
				
				System.out.println(fieldart.getText());
				System.out.println(fieldtitle.getText());
				System.out.println(fieldalb.getText());
				System.out.println(an);
				Profil p = new Profil(nb_recherche, an, fieldtitle.getText(), style_rech, null, null, (String)fieldtheme.getSelectedItem(), ti, 0, null, null, fieldalb.getText(), fieldart.getText());
				Chanson res = new Chanson();
				result_songs = res.recherche(p, init.recuperer_chansons(styles1));
				if(result_songs!=null){
					for(int j=0; j<result_songs.size(); j++){
						chansons.add(new PanelChanson(result_songs.get(j).getTitre() + " -  " + result_songs.get(j).getArtiste()));
					}
				}
				pBContent.setVisible(false);
				resultat.setVisible(true);
			}
		});
		
		pBContent.add(recherche_song, BorderLayout.CENTER);
		pBContent.add(valid, BorderLayout.SOUTH);
		
		this.add(BorderLayout.CENTER,pBContent);
		this.add(BorderLayout.CENTER, resultat);
		
	}
}
