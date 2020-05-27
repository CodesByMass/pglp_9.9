package persistance;

public abstract class Dao<T> {

  abstract void create(T t) throws Exception;


  abstract void update(T t) throws Exception;


  abstract void delete(T t) throws Exception;


  abstract T read(String nom) throws Exception;
}
