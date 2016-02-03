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
	public int getAnnee(){return annee;}

	public String getTitre(){return titre;}
	public String getTheme(){return theme;}
	public Time getDuree(){	return duree;}
	public Style getSt1(){ 
		if(est_chanson())	return ((Chanson)this).getSt1();
		else return style1;}
	public Style getSt2(){ 
		if(est_chanson())	return ((Chanson)this).getSt2();
		else return style1;}
	public Style getSt3(){ 
		if(est_chanson())	return ((Chanson)this).getSt3();
		else return style1;}
	public boolean est_chanson(){return id!=-1;}
	
	

}
