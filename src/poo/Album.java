package poo;

import java.util.ArrayList;
import java.util.Comparator;

public class Album implements Searchable<Album>{
	private int id;
	private String nom;
	private Artiste art;
	private int annee;
	
	public Album(){
		id = 0;
		nom = "";
		art = null;
		annee = 0;
	}
	
	public Album(int id_a, String n, Artiste t, int a){
		id=id_a;
		nom=n;
		art=t;
		annee = a;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int i) {
		id = i;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String n) {
		nom = n;
	}

	public Artiste getArt() {
		return art;
	}

	public void setArt(Artiste f) {
		art = f;
	}
	
	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int f) {
		annee = f;
	}
	
	@Override
	public ArrayList<Album> recherche(String s, ArrayList<Album> arr) {
		ArrayList<Rech_alb> result = new ArrayList<Rech_alb>();
		
		// On commence la recherche dans la liste des albums de la BD
		for(Album x: arr){
			
			// Si un album est trouvé exactement (son nom est dans la BD, casse excluse) on l'ajoute à la liste
			// avec une distance de 0, définie comme la distance minimale possible.
			if(s.equalsIgnoreCase(x.getNom())){			
				
				result.add(new Rech_alb(new Album(x.getId(), x.getNom(), x.getArt(), x.getAnnee()), 0));
				System.out.println("Correspondance exacte\nAlbum trouvé : " + result.get(0).a.getNom() +", " + result.get(0).a.getArt() + ".");
			} 
			// Sinon, si on le nom rentré par l'utilisateur est contenu dans le nom d'un album de la BD, mais que ce n'est
			// pas le nom exact, on l'ajoute mais avec une distance de 1, qui est la distance minimale pour 2 chaînes non identiques.
			else if(x.getNom().contains(s)){			
				
				if(result.get(0).d!=0) result.add(new Rech_alb(new Album(x.getId(), x.getNom(), x.getArt(), x.getAnnee()), 1));
				else result.add(new Rech_alb(new Album(x.getId(), x.getNom(), x.getArt(), x.getAnnee()), 1));
				System.out.println("Correspondance approximative\nAlbum trouvé : " + result.get(0).a.getNom() +", " + result.get(0).a.getArt() + ".");
			} 
			// Sinon on calcule la distance de Levenshtein, et si elle n'est pas trop grande, on insère l'album dans la liste avec
			// comme distance, la distance de Levenshtein.
			else if(distance(x.getNom(),s)<10){
				result.add(new Rech_alb(new Album(x.getId(), x.getNom(), x.getArt(), x.getAnnee()), distance(x.getNom(),s)));
			}
		}
		
		result.sort(new Comparator<Rech_alb>(){
						public int compare(Rech_alb r1, Rech_alb r2) {
							if(r1.d<r2.d) return -1;
							else if(r1.d>r2.d) return 1;
							return 0;
							}});

		
		ArrayList<Album> res = new ArrayList<Album>();
		for(Rech_alb e: result){
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
