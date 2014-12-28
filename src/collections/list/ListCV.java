package collections.list;

public interface ListCV<E> extends Iterable<E> {

	public boolean add(E e);

	public boolean contains(E e);

	public E remove(int i);

	public boolean remove(Object e);

	public int size();

	public E get(int i);

	public E set(int i, E e);
	
	int indexOf(E e);

}
