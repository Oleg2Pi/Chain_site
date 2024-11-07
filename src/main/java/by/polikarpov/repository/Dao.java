package by.polikarpov.repository;

import java.util.List;

public interface Dao<T, K> {
    public void save(T t);
    public T find(K id);
    public List<T> findAll();
    public void update(T t);
    public void delete(T t);
}
