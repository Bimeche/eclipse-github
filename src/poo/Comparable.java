package poo;

public interface Comparable<T> {
	/**
	 * Chaque classe implémentant l'interface Comparable devra 
	 * pouvoir rendre un pourcentage (float) de ressemblance
	 * @param element
	 * @return Le pourcentage de ressemblance
	 */
	public float comparer(T element);
}
