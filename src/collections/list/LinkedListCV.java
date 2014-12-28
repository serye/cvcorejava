package collections.list;

import java.util.Iterator; 
import java.util.NoSuchElementException;

public class LinkedListCV<E> implements ListCV<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;

	public static class Node<E> {
		E item;
		Node<E> next;

		public Node(E elem, Node<E> next) {
			this.item = elem;
			this.next = next;
		}

		@Override
		public String toString() {
			return null == item ? "null" : item.toString();
		}

	}

	@Override
	public boolean add(E e) {
		final Node<E> l = tail;
		final Node<E> newNode = new Node<E>(e, null);
		tail = newNode;
		if (null == l)
			head = newNode;
		else
			l.next = newNode;
		size++;
		return true;
	}

	@Override
	public boolean contains(E e) {
		return indexOf(e) != -1;
	}

	@Override
	public E remove(int i) {
		rangeCheck(i);
		Node<E> x = getNodeById(i);
		final Node<E> prev = 0 < i ? getNodeById(i - 1) : null;
		unlink(x, prev);
		return x.item;
	}

	@Override
	public boolean remove(Object e) {
		int id = -1;
		if (null == e) {
			for (Node<E> x = head; null != x; x = x.next) {
				id++;
				if (null == x.item) {
					unlink(x, getNodeById(--id));
					return true;
				}
			}
		} else
			for (Node<E> x = head; null != x; x = x.next) {
				id++;
				if (e.equals(x.item)) {
					unlink(x, getNodeById(--id));
					return true;
				}
			}

		return false;
	}

	private E unlink(Node<E> x, Node<E> prev) {
		final E element = x.item;
		final Node<E> next = x.next;

		if (prev == null)
			head = next;
		else
			prev.next = next;

		if (next == null)
			tail = prev;
		else
			x.next = null;

		x.item = null;
		size--;
		return element;
	}

	@Override
	public E get(int i) {
		rangeCheck(i);
		return getNodeById(i).item;
	}

	private Node<E> getNodeById(int i) {
		Node<E> x = i >= 0 ? head : null;
		for (int id = 0; id < i; id++)
			x = x.next;
		return x;
	}

	@Override
	public E set(int i, E e) {
		rangeCheck(i);
		Node<E> x = head;
		for (int id = 0; id < i; id++)
			x = x.next;
		x.item = e;
		return x.item;
	}

	@Override
	public int indexOf(E e) {
		int index = 0;
		if (null == e) {
			for (Node<E> x = head; null != x; x = x.next) {
				if (null == x.item)
					return index;
				index++;
			}
		} else {
			for (Node<E> x = tail; null != x; x = x.next) {
				if (e.equals(x.item))
					return index;
				index++;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	void rangeCheck(int i) {
		if (i >= size || i < 0)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Node<E> x = head; null != x; x = x.next)
			builder.append("[").append(x.toString()).append("] ");
		return builder.toString() + "......" + size;
	}

	@Override
	public Iterator<E> iterator() {
		return new Listerator(0);
	}

	public class Listerator implements Iterator<E> {
		private Node<E> lastReturned = null;
		private Node<E> next;
		private int nextIndex;

		Listerator(int index) {
			next = (index == size) ? null : getNodeById(index);
			nextIndex = index;
		}

		public boolean hasNext() {
			return nextIndex < size;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			lastReturned = next;
			next = next.next;
			nextIndex++;
			return lastReturned.item;
		}

	}

}
