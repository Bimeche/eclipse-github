package poo;

public class Album {
	private int id;
	private String nom;
	private Artiste[] feat;
	
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

	public Artiste[] getFeat() {
		return feat;
	}

	public void setFeat(Artiste[] f) {
		feat = f;
	}
	
}
