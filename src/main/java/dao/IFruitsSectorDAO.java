package dao;

import model.FruitsSector;

import java.util.List;

public interface IFruitsSectorDAO extends IBaseDAO{
    FruitsSector getEntityById(int id);

    void insertEntity(FruitsSector t);

    void updateEntity(FruitsSector t);

    void removeEntity(FruitsSector t);

    List<FruitsSector> getEntities();
}
