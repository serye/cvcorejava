package collections.map;

public interface MapCV <K,V> {

	V put(K key,V val);
	V get(K key);
	V remove(K key);
	int size(); 
	
}
