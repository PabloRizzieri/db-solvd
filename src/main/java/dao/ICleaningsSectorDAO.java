package dao;

import model.CleaningsSector;

import java.util.List;

public interface ICleaningsSectorDAO extends IBaseDAO{
    CleaningsSector getEntityById(int id);

    void insertEntity(CleaningsSector t);

    void updateEntity(CleaningsSector t);

    void removeEntity(CleaningsSector t);

    List<CleaningsSector> getEntities();
}
