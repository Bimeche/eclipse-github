package poo;

public class Rech_alb {
	protected Album a;
	protected int d;
	
	public Rech_alb(){};
	
	public Rech_alb(Album album, int x){
		a = album;
		d = x;
	}
	
	public Rech_alb(Rech_alb r){
		a=r.a;
		d=r.d;
	}
}
