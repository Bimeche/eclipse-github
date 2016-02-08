package reserv;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String pseudo;
	private String mdp;
	
	public Client(int idc, String n, String p, String ps, String m){
		id = idc;
		nom = n;
		prenom = p;
		pseudo = ps;
		mdp = m;
	}
	
	public int getId(){return id;}
	public String getNom(){return nom;}
	public String getPrenom(){return prenom;}
	public String getPseudo(){return pseudo;}
	public String getMdp(){return mdp;}
	
	public boolean verifier_pseudo(String p){
		
		if(pseudo.equals(p)) return false;
		return true;
	}
}
