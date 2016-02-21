package poo;

import java.sql.Time;

public class Profil{
	private int id = -1;
	private int annee = 0;
	private String titre = null;
	private Style style1 = null;
	private Style style2 = null;
	private Style style3 = null;
	private String theme = null;
	private Time duree = null;
	private int rythme = 0;
	private String paroles = null;
	private String instrument = null;
	private String album = null;
	private String artiste = null;
	
	public Profil(){}
	
	public Profil(int idp, int a, String t, Style st1, Style st2, Style st3, String th, Time d, int r, String prl, String instru, String alb, String art){
		id = idp;
		annee = a;
		titre = t;
		style1 = st1;
		style2 = st2;
		style3 = st3;
		theme = th;
		duree = d;
		rythme = r;
		paroles = prl;
		instrument = instru;
		album = alb;
		artiste = art;
	}
	
	public Profil(Chanson c){
		id = c.getId();
		annee = c.getAnnee();
		titre = c.getTitre();
		style1 = c.getSt1();
		style2 = c.getSt2();
		style3 = c.getSt3();
		theme = c.getTheme();
		duree = c.getDuree();
		rythme = c.getRythme();
		paroles = c.getParoles();
		instrument = c.getinstru();
		album = c.getAlbum();
		artiste = c.getArtiste();
	}
	
	public int getAnnee(){return annee;}

	public String getTitre(){return titre;}
	public String getTheme(){return theme;}
	public Time getDuree(){	return duree;}
	public Style getSt1(){ 
		if(est_chanson()){
			Chanson c = new Chanson(id, titre, artiste, album, annee, style1, style2, style3, theme, duree, rythme, paroles, instrument);
			return c.getSt1();
		}
		else return style1;}
	public Style getSt2(){ 
		if(est_chanson()){
			Chanson c = new Chanson(id, titre, artiste, album, annee, style1, style2, style3, theme, duree, rythme, paroles, instrument);
			return c.getSt2();
		}
		else return style1;}
	public Style getSt3(){ 
		if(est_chanson()){
			Chanson c = new Chanson(id, titre, artiste, album, annee, style1, style2, style3, theme, duree, rythme, paroles, instrument);
			return c.getSt3();
		}
		else return style1;}
	public boolean est_chanson(){return id!=-1;}
	
	public String getAlbum(){
		return album;
	}
	public String getArtiste(){
		return artiste;
	}

}
