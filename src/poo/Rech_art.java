package poo;

public class Rech_art {
	protected Artiste a;
	protected int d;
	
	public Rech_art(){};
	
	public Rech_art(Artiste artiste, int x){
		a = artiste;
		d = x;
	}
	
	public Rech_art(Rech_art r){
		a=r.a;
		d=r.d;
	}
}
