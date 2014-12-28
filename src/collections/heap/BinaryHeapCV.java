package collections.heap;
 

@SuppressWarnings("unchecked")
public class BinaryHeapCV<T extends Comparable<T>> {
	private static final int CAPACITY = 2;

	private int size;
	private T[] heap;

	public BinaryHeapCV() {
		size = 0;
		heap = (T[]) new Comparable[CAPACITY];
	}

	public BinaryHeapCV(T[] array) {
		size = array.length;
		heap = (T[]) new Comparable[array.length + 1];
		System.arraycopy(array, 0, heap, 1, array.length);
		buildHeap();
	}

 
	private void buildHeap() {
		for (int k = size / 2; k > 0; k--)
			moveDown(k);
	}


	private void growTwice() {
		T[] old = heap;
		heap = (T[]) new Comparable[heap.length * 2];
		System.arraycopy(old, 1, heap, 1, size);
	}
	
	private void moveDown(int k) {
		T tmp = heap[k];
		int child;

		for (; 2 * k <= size; k = child) {
			child = 2 * k;

			if (child != size && heap[child].compareTo(heap[child + 1]) > 0)
				child++;

			if (tmp.compareTo(heap[child]) > 0)
				heap[k] = heap[child];
			else
				break;
		}
		heap[k] = tmp;
	}

	public T deleteMin() throws Exception {
		if (size == 0)
			throw new Exception();
		T min = heap[1];
		heap[1] = heap[size--];
		moveDown(1);
		return min;
	}

	public void insert(T x) {
		if (size == heap.length - 1)
			growTwice(); 
		int pos = ++size;
		for (; pos > 1 && x.compareTo(heap[pos / 2]) < 0; pos = pos / 2)
			heap[pos] = heap[pos / 2];
		heap[pos] = x;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int k = 1; k <= size; k++)
			builder.append(heap[k]).append(" ");
		return builder.toString();
	}

}