package dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {

    T getEntityById(int id) throws SQLException;

    void insertEntity(T t);

    void updateEntity(T t, int i);

    void removeEntity(T t);

    List<T> getEntities();
}
