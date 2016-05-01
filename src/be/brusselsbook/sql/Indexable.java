package be.brusselsbook.sql;

public interface Indexable<T> {

	T withId(Long id);

	T create(Object... objects);
}
