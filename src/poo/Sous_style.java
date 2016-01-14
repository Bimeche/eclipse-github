package poo;

import java.util.ArrayList;

/**
 * Classe qui sera implémentée par les programmeurs
 * @author oceane
 *
 */
public class Sous_style extends Genre implements Comparable<Genre>{
	private int idSS;
	private Genre pere;
	public String nom;
	private ArrayList<Sous_style> fils;
	/**
	 * Constructeur de sous_style
	 * @param n le nom du style
	 * @param i le numéro du fils (et son rapport d proximité par rapport au Style père)
	 */
	
	public Sous_style(String n, int id, Genre p){
		super(n,id);
		nom=n;
		this.pere = p;
	}
	
	public float comparer(Genre s){
		float resultat;
		//Cas 1 : Le père est un genre
		if (this.pere.est_genre()){
			//Cas 1-1 : Le père est le genre recherché
			if (pere == s) resultat = id; 
			//Cas 1-2 : Le père n'est pas la genre recherché 
			else resultat = id + pere.comparer(s);
		}
		//Cas 2 : Le père est un autre sous_style :
		else return (id + comparer(pere)); 
		
		return resultat;
	}

	public float comparer(Sous_style s){
		return 0;
	}
	
}
