package dao;

import model.CerealsSector;

import java.util.List;

public interface ICerealsSectorDAO extends IBaseDAO{
    CerealsSector getEntityById(int id);

    void insertEntity(CerealsSector t);

    void updateEntity(CerealsSector t);

    void removeEntity(CerealsSector t);

    List<CerealsSector> getEntities();
}
