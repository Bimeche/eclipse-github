package poo;

import java.util.ArrayList;

public interface Searchable<T> {

	public ArrayList<T> recherche(Profil p, ArrayList<T> arr);
	public int min(int x, int y, int z);
	public int distance(String a, String b);
}
