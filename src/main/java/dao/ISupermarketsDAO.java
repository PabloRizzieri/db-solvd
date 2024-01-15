package dao;

import model.Supermarkets;

import java.util.List;

public interface ISupermarketsDAO extends IBaseDAO{
    Supermarkets getEntityById(int id);

    void insertEntity(Supermarkets t);

    void updateEntity(Supermarkets t);

    void removeEntity(Supermarkets t);

    List<Supermarkets> getEntities();
}



