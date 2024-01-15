package dao;


import model.MeatsSector;

import java.util.List;

public interface IMeatsSectorDAO extends IBaseDAO{
    MeatsSector getEntityById(int id);

    void insertEntity(MeatsSector t);

    void updateEntity(MeatsSector t);

    void removeEntity(MeatsSector t);

    List<MeatsSector> getEntities();
}