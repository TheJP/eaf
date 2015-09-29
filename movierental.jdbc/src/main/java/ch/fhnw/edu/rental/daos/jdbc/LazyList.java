package ch.fhnw.edu.rental.daos.jdbc;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import ch.fhnw.edu.rental.daos.Dao;
import ch.fhnw.edu.rental.model.ModelBase;

public class LazyList<T extends ModelBase> extends AbstractList<T> {

	private List<T> list = null;
	private Supplier<List<T>> producer;
	private Dao<T> dao;

	public LazyList(Supplier<List<T>> producer){
		this.producer = producer;
		dao = null;
	}

	public LazyList(Supplier<List<T>> producer, Dao<T> dao){
		this.producer = producer;
		this.dao = dao;
	}

	private List<T> getList(){
//		if(list == null){
			list = producer.get();
//		}
		list = list.stream().filter(item -> !item.isDeleted()).collect(Collectors.toList());
		return list;
	}

	@Override
	public int size() { return getList().size(); }

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		if(dao == null){ throw new UnsupportedOperationException(); }
		boolean result = getList().remove(o);
		if(result){ dao.delete((T)o); }
		return result;
	}

	@Override
	public T get(int index) { return getList().get(index); }

	@Override
	public boolean add(T item) {
		if(dao == null){ throw new UnsupportedOperationException(); }
		boolean result = getList().add(item);
		if(result){ dao.saveOrUpdate(item); }
		return result;
	}

	@Override
	public T remove(int index) {
		if(dao == null){ throw new UnsupportedOperationException(); }
		T item = getList().remove(index);
		dao.delete(item);
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Iterator<T> itr = getList().iterator();
			private T next = itr.hasNext() ? itr.next() : null;

			@Override
			public boolean hasNext() {
				return next != null && !next.isDeleted();
			}

			@Override
			public T next() {
				T tmp = next;
				next = itr.hasNext() ? itr.next() : null;
				return tmp;
			}

			@Override
			public void remove() {
				if(next != null){ dao.delete(next); }
				itr.remove();
			}
		};
	}
}
