package poo;

public class Rech_song {
	protected Chanson a;
	protected int d;
	
	public Rech_song(){};
	
	public Rech_song(Chanson song, int x){
		a = song;
		d = x;
	}
	
	public Rech_song(Rech_song r){
		a=r.a;
		d=r.d;
	}
}
