package poo;

public class Rythme implements Comparable<Rythme>{
	int BPM;
	
	public Rythme(int i){
		BPM = i;
	}
	
	public float comparer(Rythme r){
		return (float)(100-Math.abs((r.BPM-BPM)))/100;
		
	}
}
