package poo;

import java.util.ArrayList;


public class Genre implements Comparable<Genre> {
	private int dist_genres[]; // Détermine la distance entre les genres
	private Genre pere;
	public ArrayList<Sous_style> fils;
	protected String nom;
	protected int id;
	
	/**
	 * Constructeur de Genre
	 * @param n nom du Genre
	 */
	public Genre(String n){
		pere = null;
		nom = n;
		fils = new ArrayList<Sous_style>();
	}
	
	/**
	 * Constructeur de Genre n°2
	 * @param n Nom du Genre
	 * @param i indice du Genre
	 */
	public Genre(String n, int i){
		pere = null;
		dist_genres = new int[11];
		nom = n;
		id = i;
		dist_genres[id] = 0;
		fils = new ArrayList<Sous_style>();
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
	public void add_sstyle(Sous_style s){
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
		int i;
		System.out.print("["+nom+ ": "+ nb_fils() + " fils]");
		System.out.print(" Ses fils sont : ");
		for(i=0; i<fils.size(); i++){
			System.out.print(fils.get(i).nom+" ");
		}
		System.out.print("\n\n");
	}
	public boolean est_genre(){
		return (pere == null);
	}

	//--- COMPARAISON ---
	public float comparer(Genre s){
		return this.dist_genres[s.id];
	}
	
	
	public float comparer(Sous_style s){
		return 0;
	}
}
