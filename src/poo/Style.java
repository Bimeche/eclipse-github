package poo;

import java.util.ArrayList;

/**
 * Classe qui sera implémentée par les programmeurs
 * @author oceane
 *
 */
public class Style implements Comparable<Style>{
	public int idSS;
	public Style pere;
	public String nom;
	public ArrayList<Style> fils;
	public int dist_genres[];
	
	public Style(){
		nom = "null";
		idSS = -1;
		pere = this;
		
	}
	public Style(String n, int id){
		nom = n;
		idSS = id;
		fils = new ArrayList<Style>();
		dist_genres = new int [100];
		this.pere = null;
	}
	/**
	 * Constructeur de sous_style
	 * @param n le nom du style
	 * @param i le numéro du fils (et son rapport d proximité par rapport au Style père)
	 */
	public Style(String n, int id, Style p){
		
		nom = n ;
		idSS = id;
		this.pere = p;
		fils = new ArrayList<Style>();
		dist_genres = new int[100];
	}
	
	public Style copieS(Style s){
		return (new Style(s.nom, s.idSS, s.pere));
	}
	
	public Object clone(){
		return new Style(this.nom, this.idSS, this.copieS(this.pere));
	}
	public void add_sstyle(Style s){
		fils.add(s);
	}
	public int nb_fils(){
		return fils.size();
	}
	public String get_nomS(){
		return nom;
	}
	public int getId(){
		return idSS;
	}
	public Style getPere(){
		return pere;
	}
	public boolean est_genre(){
		return (pere == null);
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
	/**
	 * Renvoie -1 si le Genre n'a pas le style comme petit-fils
	 * Renvoie l'id du père sinon
	 */
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
	
	public boolean est_dans_arbre(Style s){
		boolean b = false;
		if(this.est_genre()){
			if((this.nom.equals(s.nom)) || (a_pour_fils(s)!=-1) || (a_pour_petit_fils(s)!=-1))	b = true;
		}
		else return pere.est_dans_arbre(s);
		return b;
	}
	public float distG(Style s){
		return ((Genre)this).dist_genres[s.idSS];
	}
	public float distance_au_pere(){
		if(this.est_genre())	return 0;
		else if(this.pere.est_genre()) return 1;
		else return 2;
	}
	public Genre recuperer_genre(){
		if(est_genre()) return (Genre)this;
		else return pere.recuperer_genre();
	}
	public float distance_style(Style s){
		float resultat = 0;
		//Vérification si ce sont les mêmes styles
		if(this.nom.equals(s.nom))	resultat=0;
		
		//On regarde si les deux styles sont des genres 
		else if(this.est_genre() && s.est_genre())	resultat = (float)this.distG((Genre)s);
		else{
		// Si il est dans l'arbre : 
			if(this.est_dans_arbre(s)){
				if(this.est_genre()){
					if(this.a_pour_fils(s)!=-1) resultat += s.idSS;
					if(this.a_pour_petit_fils(s)!=-1) resultat += s.pere.idSS + s.idSS;
					}
				else if(pere.est_genre()) {
					if(this.a_pour_fils(s)!=-1)	resultat += s.idSS;
					else resultat += this.idSS + pere.distance_style(s);
				}
				else resultat += this.idSS + pere.distance_style(s);
			}
		//Si il n'est pas dans l'arbre :
			else{	
				resultat += this.distance_au_pere();
				resultat += this.recuperer_genre().distG(s.recuperer_genre());
				resultat += s.recuperer_genre().distance_style(s);	
			}
		}
		return resultat;
	}
	public float comparer(Style s){
		float r = this.distance_style(s);
		r = 1-r/20;
		if(r<=0)	r = 0;
		return r;
		
	}
	

	
}