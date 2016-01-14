package poo;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Artiste> arr_art = init.recuperer_artistes();
		ArrayList<Artiste> res = new ArrayList<Artiste>();
		Artiste rech = new Artiste();
		Artiste hey = new Artiste();
		int result;
		
		
		for(Artiste a: arr_art){
			result = hey.distance("Madonna",a.getNom());
			System.out.println("Madonna - " + a.getNom() + ": " + result);
		}
		
		
		res = rech.recherche("Madonna", arr_art);
		for(Artiste a: res){
			System.out.println("Madonna - " + a.getNom());
		}
	}

}
