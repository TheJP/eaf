package ch.fhnw.edu.rental.daos.jdbc;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ch.fhnw.edu.rental.model.ModelBase;

public class LazyList<T extends ModelBase> implements List<T> {

	private List<T> list = null;
	private Supplier<List<T>> producer;

	public LazyList(Supplier<List<T>> producer){
		this.producer = producer;
	}

	private List<T> getList(){
		if(list == null){
			list = producer.get();
		}
		list = list.stream().filter(item -> !item.isDeleted()).collect(Collectors.toList());
		return list;
	}

	@Override
	public int size() { return getList().size(); }

	@Override
	public boolean isEmpty() { return getList().isEmpty(); }

	@Override
	public boolean contains(Object o) { return getList().contains(o); }

	@Override
	public Iterator<T> iterator() { return Collections.unmodifiableList(getList()).iterator(); }

	@Override
	public Object[] toArray() { return getList().toArray(); }

	@Override
	public <T2> T2[] toArray(T2[] a) { return getList().toArray(a); }

	@Override
	public boolean add(T e) { throw new UnsupportedOperationException(); }

	@Override
	public boolean remove(Object o) { throw new UnsupportedOperationException(); }

	@Override
	public boolean containsAll(Collection<?> c) { return getList().containsAll(c); }

	@Override
	public boolean addAll(Collection<? extends T> c) { throw new UnsupportedOperationException(); }

	@Override
	public boolean addAll(int index, Collection<? extends T> c) { throw new UnsupportedOperationException(); }

	@Override
	public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }

	@Override
	public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }

	@Override
	public void clear() { throw new UnsupportedOperationException(); }

	@Override
	public T get(int index) { return getList().get(index); }

	@Override
	public T set(int index, T element) { throw new UnsupportedOperationException(); }

	@Override
	public void add(int index, T element) { throw new UnsupportedOperationException(); }

	@Override
	public T remove(int index) { throw new UnsupportedOperationException(); }

	@Override
	public int indexOf(Object o) { return getList().indexOf(o); }

	@Override
	public int lastIndexOf(Object o) { return getList().lastIndexOf(o); }

	@Override
	public ListIterator<T> listIterator() { return Collections.unmodifiableList(getList()).listIterator(); }

	@Override
	public ListIterator<T> listIterator(int index) { return Collections.unmodifiableList(getList()).listIterator(index); }

	@Override
	public List<T> subList(int fromIndex, int toIndex) { return Collections.unmodifiableList(getList()).subList(fromIndex, toIndex); }

}
