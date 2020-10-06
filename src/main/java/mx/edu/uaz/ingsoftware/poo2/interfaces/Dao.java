package mx.edu.uaz.ingsoftware.poo2.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(Object id);
    List<T> getAll();
    boolean save(T dato);
    boolean update(T dato);
    boolean delete(Object id);
}
