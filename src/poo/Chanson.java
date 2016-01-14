package poo;

public class Chanson {
	private int id;
	private String nom;
	private Genre tab[];
	
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
	
	public Genre[] getTab() {
		return tab;
	}
	
	public void setTab(Genre tab[]) {
		this.tab = tab;
	}
}
