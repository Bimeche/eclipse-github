package poo;

import java.util.ArrayList;
import java.util.Comparator;

public class Chanson implements Searchable<Chanson>{
	private int id;
	private String nom;
	private Artiste art;
	private Genre tab[];
	
	public Chanson(){
		id=0;
		nom="";
		art = null;
		tab = new Genre[3];
	}
	
	public Chanson(int i, String s, Artiste a, Genre t[]){
		id = i;
		nom = s;
		art = a;
		tab = t;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Artiste getArt() {
		return art;
	}
	
	public void setArt(Artiste a) {
		art = a;
	}
	
	public Genre[] getTab() {
		return tab;
	}
	
	public void setTab(Genre tab[]) {
		this.tab = tab;
	}
	
	@Override
	public ArrayList<Chanson> recherche(String s, ArrayList<Chanson> arr) {
		ArrayList<Rech_song> result = new ArrayList<Rech_song>();
		
		// On commence la recherche dans la liste des albums de la BD
		for(Chanson x: arr){
			
			// Si un album est trouvé exactement (son nom est dans la BD, casse excluse) on l'ajoute à la liste
			// avec une distance de 0, définie comme la distance minimale possible.
			if(s.equalsIgnoreCase(x.getNom())){			
				
				result.add(new Rech_song(new Chanson(x.getId(), x.getNom(), x.getArt(), x.getTab()), 0));
				System.out.println("Correspondance exacte\nAlbum trouvé : " + result.get(0).a.getNom() +", " + result.get(0).a.getArt() + ".");
			} 
			// Sinon, si on le nom rentré par l'utilisateur est contenu dans le nom d'un album de la BD, mais que ce n'est
			// pas le nom exact, on l'ajoute mais avec une distance de 1, qui est la distance minimale pour 2 chaînes non identiques.
			else if(x.getNom().contains(s)){			
				
				result.add(new Rech_song(new Chanson(x.getId(), x.getNom(), x.getArt(), x.getTab()), 1));
				System.out.println("Correspondance approximative\nAlbum trouvé : " + result.get(0).a.getNom() +", " + result.get(0).a.getArt() + ".");
			} 
			// Sinon on calcule la distance de Levenshtein, et si elle n'est pas trop grande, on insère l'album dans la liste avec
			// comme distance, la distance de Levenshtein.
			else if(distance(x.getNom(),s)<10){
				result.add(new Rech_song(new Chanson(x.getId(), x.getNom(), x.getArt(), x.getTab()), distance(x.getNom(),s)));
			}
		}
		
		result.sort(new Comparator<Rech_song>(){
						public int compare(Rech_song r1, Rech_song r2) {
							if(r1.d<r2.d) return -1;
							else if(r1.d>r2.d) return 1;
							return 0;
							}});

		
		ArrayList<Chanson> res = new ArrayList<Chanson>();
		for(Rech_song e: result){
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
