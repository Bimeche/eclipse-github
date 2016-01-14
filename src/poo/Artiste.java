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
	
	public String toString(){
		return "Nom : " + nom;
	}
	
	public void afficher(){
		System.out.println(this);
	}
	
	@Override
	public ArrayList<Artiste> recherche(String s, ArrayList<Artiste> arr) {
		ArrayList<Rech_art> result = new ArrayList<Rech_art>();
		int i = 0, j;
		
		// On commence la recherche dans la liste des artistes de la BD
		for(Artiste x: arr){
			
			// Si un artiste est trouvé exactement (son nom est dans la BD, casse excluse) on l'ajoute à la liste
			// avec une distance de 0, définie comme la distance minimale possible.
			if(s.equalsIgnoreCase(x.getNom())){			
				
				result.add(new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), 0));
				System.out.println("Correspondance exacte\nArtiste trouvé : " + result.get(0).a.getNom() +", " + result.get(0).a.getType() + ".");
				i++;
			} 
			// Sinon, si le nom rentré par l'utilisateur est contenu dans le nom d'un artiste de la BD, mais que ce n'est
			// pas le nom exact, on l'ajoute mais avec une distance de 1, qui est la distance minimale pour 2 chaînes non identiques.
			else if(x.getNom().contains(s)){			
				
				if(result.get(0).d!=0) result.add(0, new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), 1));
				else result.add(new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), 1));
				System.out.println("Correspondance approximative\nArtiste trouvé : " + result.get(i).a.getNom() +", " + result.get(i).a.getType() + ".");
				i++;
			} 
			// Sinon on calcule la distance de Levenshtein, et si elle n'est pas trop grande, on insère l'artiste dans la liste avec
			// comme distance, la distance de Levenshtein.
			else if(distance(x.getNom(),s)<10){
				result.add(new Rech_art(new Artiste(x.getId(), x.getNom(), x.getType()), distance(x.getNom(),s)));
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
		
		if(res.isEmpty()) System.out.println("Aucune coorespondance pour \"" + s + "\" dans la base de données.");
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
