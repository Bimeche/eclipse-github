package poo;

import java.util.ArrayList;


public class Genre extends Style implements Comparable<Genre> {
	public int dist_genres[]; // Détermine la distance entre les genres
	public Genre pere;
	public ArrayList<Style> fils;
	public String nom;
	protected int id;
	
	/**
	 * Constructeur de Genre
	 * @param n nom du Genre
	 */
	public Genre(String n){
		super(n, 0, null);
		dist_genres = new int[100];

		pere = null;
		nom = n;
		fils = new ArrayList<Style>();
	}
	

	/**
	 * Constructeur de Genre n°2
	 * @param n Nom du Genre
	 * @param i indice du Genre
	 */
	public Genre(String n, int i){
		super(n, i, null);
		dist_genres = new int[100];
		nom = n;
		id = i;
		dist_genres[id] = 0;
		fils = new ArrayList<Style>();
	}
	
	/**
	 * Définit la distance entre deux Genres 
	 * @param s le Genre
	 * @param distance La distance entre les Genres
	 */
	public void set_distance(Genre s, int distance){
		this.dist_genres[s.id] = distance;
	}
	
	/**
	 * Ajoute un sous_style à l'ArrayListe du Genre contenant ses fils
	 * @param s le sous_style à ajouter
	 */
	public void add_sstyle(Style s){
		fils.add(s);
	}
	
	/**
	 * Le nombre de Fils du Genre
	 * @return Renvoie le nombre de fils du Genre (int)
	 */
	public int nb_fils(){
		return fils.size();
	}
	
	/**
	 * Affiche le Genre au format : 
	 * [Genre] a nbfils fils.
	 * Ses Sous_styles sont : ssstyle1 ssstyle2...
	 */
	public void afficher(){
		int i,j;
		System.out.println(nom);
		for(i=0; i<this.nb_fils(); i++){
			System.out.print("  " + fils.get(i).get_nomS());
			for(j=0; j<fils.get(i).nb_fils(); j++){
				System.out.print("\n     " + (fils.get(i)).fils.get(j).get_nomS());
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	/**
	 * Fonction qui détermine si s est un fils direct du genre
	 * @param s
	 * @return
	 */
	public int a_pour_fils(Style s){
		int res =-1;
		int i;
		if(nb_fils() != 0 ){
		
			for(i=0; i<nb_fils(); i++){
				if(fils.get(i) == s)	res = this.idSS;
			}
		}
		return res;
		}
	public int a_pour_petit_fils(Style s){
		int res = -1;
		int i, j;
		//Ne marche que pour les genres : 
		if(est_genre()){
			for(i=0; i<nb_fils(); i++){
				if(fils.get(i).a_pour_fils(s)!=-1)	res = fils.get(i).a_pour_fils(s);
			}
			}
		return res;
		
	}

	public String get_nomG(){	return nom;}

	//--- COMPARAISON ---
	public float distG(Genre s){
		float r = this.dist_genres[s.idSS];
		if(this == s)r = 0;
		return r;
	}
	
	public float comparerG(Genre s){
		if(this==null || s ==null)	return (float) 0;
		if(this == s)	return (float)1;
		float r = this.distG(s);
		r = (float)(1-r/20);
		if(r <0)	r = 0;
		return r;
	}
	
	public float comparer(Style s){
		float result=0;
		if(s.est_genre())	return (this).comparer((Genre)s);
		int test = a_pour_petit_fils(s); // L'id du pere de s
		if(this==null || s ==null)	result = 10000;
			//Test si le sous_style est un fils du genre
		else if(a_pour_fils(s) != -1)	result = (float) s.idSS;
		
			//Test si le sous_style est un petit-fils du genre
		else if(test != -1)	result = (float) s.idSS + test;
		
		else{// On récupère le Genre du Sous_style et on ajoute la distance au genre
			result = s.idSS;
			if(s.pere.est_genre())	result += distG(s.pere);
			else{
				result += s.pere.idSS;
				result += dist_genres[s.pere.pere.idSS];
			}			
		}	
		result = (float)(1-result/20);
		if(result <0)	result = 0;
		return result;
	}
}
