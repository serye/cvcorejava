package collections.list;

import java.util.Iterator;

public class ArrayListCV<E> implements ListCV<E> {

	private int size;
	private E[] arr;

	@Override
	public boolean add(E e) {
		arr[size++] = e;
		return true;
	}

	@Override
	public boolean contains(E e) {
		return indexOf(e) >= 0;
	}

	@Override
	public E remove(int i) {
		rangeCheck(i);
		E oldValue = (E) arr[i];
		int numMoved = size - i - 1;
		if (numMoved > 0)
			System.arraycopy(arr, i + 1, arr, i, numMoved);
		arr[--size] = null;
		return oldValue;
	}

	@Override
	public boolean remove(Object e) {
		if (e == null) {
			for (int i = 0; i < size; i++)
				if (arr[i] == null) {
					fastRemove(i);
					return true;
				}
		} else {
			for (int index = 0; index < size; index++)
				if (e.equals(arr[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}

	private void fastRemove(int i) {
		int numMoved = size - i - 1;
		if (numMoved > 0)
			System.arraycopy(arr, i + 1, arr, i, numMoved);
		arr[--size] = null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int i) {
		rangeCheck(i);
		return (E) arr[i];
	}

	@Override
	public E set(int i, E e) {
		rangeCheck(i);
		E oldValue = (E) arr[i];
		arr[i] = e;
		return oldValue;
	}

	@Override
	public int indexOf(E e) {
		if (e == null) {
			for (int i = 0; i < size; i++)
				if (arr[i] == null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (e.equals(arr[i]))
					return i;
		}
		return -1;
	}

	private void rangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

}
