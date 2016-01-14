package poo;

public class Instrument {
	private String nom;
	private int id;
	
	public Instrument(String n, int instr){
		nom=n;
		id=instr;
	}
	
	public String toString(){
		return nom;
	}
}
