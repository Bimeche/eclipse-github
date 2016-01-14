package poo;

import java.util.ArrayList;
import java.util.Comparator;

public class Artiste implements Searchable<Artiste>{
	private int id;
	private String nom;
	private String type;

	public Artiste(){
		id = 0;
		nom = "";
		type = "";
	}
	
	public Artiste(int id_a, String n, String t){
		id=id_a;
		nom=n;
		type=t;
	}
	
	// Getters et Setters
	public String getType() {
		return type;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String n) {
		nom = n;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	// Mise en forme de l'affichage un artiste
	public String toString(){
		return "Donn�es sur l'artiste :\nNom : " + nom + "\nProfession : " + type + ".";
	}
	
	public void afficher(){
		System.out.println(this);
	}
	
	@Override
	public ArrayList<Artiste> recherche(String s, ArrayList<Artiste> arr) {
		ArrayList<Rech_art> result = new ArrayList<Rech_art>();
		int i = 0, j;
		boolean in_result = false;
		
		// On commence la recherche dans la liste des artistes de la BD
		for(Artiste x: arr){
			
			// Si un artiste est trouv� exactement (son nom est dans la BD, casse excluse) on le met � l'indice 0, le premier,
			// peu importe qu'il y ait d�j� quelque chose dans la liste, car c'est le cas le plus proche possible.
			if(s.equalsIgnoreCase(x.getNom())){			
				
				result.add(0, new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), 0));
				System.out.println("Correspondance exacte\nArtiste trouv� : " + result.get(0).a.getNom() +", " + result.get(0).a.getType() + ".");
				i++;
			} 
			// Sinon, si on le nom rentr� par l'utilisateur est contenu dans le nom d'un artiste de la BD, mais que ce n'est
			// pas le nom exact, on le met au premier indice derri�re la recherche exacte (0 si la condition ci-dessus n'a pas 
			// encore �t� remplie, 1 sinon) car c'est mieux qu'une approximation de distance, mais pas exact quand m�me.
			else if(x.getNom().contains(s)){			
				
				if(result.get(0).d!=0) result.add(0, new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), 1));
				else result.add(1, new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), 1));
				System.out.println("Correspondance approximative\nArtiste trouv� : " + result.get(i).a.getNom() +", " + result.get(i).a.getType() + ".");
				i++;
			} 
			// Sinon on calcule la distance de Levenshtein, et si elle n'est pas trop grande, on ins�re l'artiste dans la liste.
			// Pour l'ins�rer on regarde d'abord si il y a un �l�ment ou non. Si oui, on compare la distance de l'�l�ment actuel
			// avec celles des �l�ments d�j� pr�sents (0 pour correspondance exacte, 1 pour contenu. 1 est le minimum atteignable
			// pour deux chaines diff�rentes) et si on arrive a un �l�ment dont la distance est sup�rieure � l'�l�ment actuel, on
			// ins�re l'�l�ment � cette place, sinon on l'ins�re � la fin.
			else if(distance(x.getNom(),s)<10){
				if(!result.isEmpty()){
					j = 0;
					in_result = false;
					while(j<result.size() && in_result){
						if(distance(x.getNom(),s) < result.get(j).d){
							result.add(result.indexOf(result.get(j)), new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), distance(x.getNom(),s)));
							in_result = true;
						}
						j++;
					}
				}
				if(!in_result) result.add(i, new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), distance(x.getNom(),s)));
				i++;
			}
		}
		
		result.sort(new Comparator<Rech_art>(){
						public int compare(Rech_art r1, Rech_art r2) {
							if(r1.d<r2.d) return -1;
							else if(r1.d>r2.d) return 1;
							return 0;
							}});

		
		ArrayList<Artiste> res = new ArrayList<Artiste>();
		for(Rech_art e: result){
			res.add(e.a);
		}
		
		if(res.isEmpty()) System.out.println("Aucune coorespondance pour \"" + s + "\" dans la base de donn�es.");
		return res;
	}
	
	
	public int min(int x, int y, int z){
		int mini = x;
		if(x<=y){
			if(x<=z){
				mini = x;
			} else {
				mini = z;
			}
		} else {
			if(y<=z){
				mini = y;
			} else {
				mini = z;
			}
		}
		
		return mini;
	}
	
	public int distance(String a, String b){
		int[][] d = new int[a.length()+1][b.length()+1];
		
		int i, j, cout;
		
		for(i = 0; i<a.length()+1; i++) d[i][0] = i;
		for(j = 0; j<b.length()+1; j++) d[0][j] = j;
		
		for(i = 1; i<a.length()+1;i++){
			for(j = 1; j<b.length()+1;j++){
				if(a.regionMatches(true, i-1, b, j-1, 1)) cout = 0;
				else cout = 1;
				d[i][j] = min(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1]+cout);
			}
		}
		
		return d[a.length()][b.length()];
	}
	
	
	
}
