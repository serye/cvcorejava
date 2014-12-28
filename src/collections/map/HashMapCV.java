package collections.map;

import java.util.Iterator;
import java.util.LinkedList;

public class HashMapCV<K, V> implements MapCV<K, V> {

	int size;
	final int INIT_CAPACITY = 32;
	public LinkedList<Entry<K, V>>[] arr;

	@SuppressWarnings("unchecked")
	public HashMapCV() {
		this.size = 0;
		arr = new LinkedList[INIT_CAPACITY];
	}

	int indexFor(int hash, int arrSize) {
		return hash & (arrSize - 1);
	}

	private int hash(K key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	@Override
	public V put(K key, V val) {
		if (null == key)
			return putForNullKey(val);
		int hash = hash(key);
		int id = indexFor(hash, arr.length);
		if (null == arr[id] || arr[id].isEmpty())
			arr[id] = new LinkedList<HashMapCV.Entry<K, V>>();
		arr[id].addFirst(new Entry<K, V>(hash, key, val));
		size++;
		//TODO check for threshold and resize if necessary
		return val;
	}

	private V putForNullKey(V val) {
		if (null == arr[0] || arr[0].isEmpty()) {
			arr[0] = new LinkedList<HashMapCV.Entry<K, V>>();
			arr[0].addFirst(new Entry<K, V>(0, null, val));
		} else {
			for (Entry<K, V> e : arr[0])
				if (null == e.key)
					e.val = val;
		}
		return val;
	}

	@Override
	public V get(K key) {
		if (key == null)
			return getForNullKey();
		int hash = hash(key);
		LinkedList<Entry<K, V>> ll = arr[indexFor(hash, arr.length)];

		if (null == ll || ll.isEmpty())
			return null;

		for (Entry<K, V> e : ll) {
			Object k;
			if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
				return e.val;
		}
		return null;
	}

	private V getForNullKey() {
		for (Entry<K, V> e : arr[0])
			if (null == e.key)
				return e.val;
		return null;
	}

	@Override
	public V remove(K key) {
		int hash = null == key ? 0 : hash(key);
		LinkedList<Entry<K, V>> ll = arr[indexFor(hash, arr.length)];

		if (null == ll || ll.isEmpty())
			return null;

		Iterator<Entry<K, V>> iter = ll.iterator();
		while (iter.hasNext()) {
			Entry<K, V> e = iter.next();
			if (e.key == key) {
				iter.remove();
				return e.val;
			}
		}

		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	static class Entry<K, V> {
		final K key;
		V val;
		final int hash;

		public Entry(int hash, K key, V val) {
			this.key = key;
			this.val = val;
			this.hash = hash;
		}

		@Override
		public String toString() {
			return (null == key ? "null" : key.toString()) + "-->"
					+ (null == val ? "null" : val.toString());
		}
	}

}
