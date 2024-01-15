package dao;

import model.Providers;

import java.util.List;

public interface IProvidersDAO extends IBaseDAO{
    Providers getEntityById(int id);

    void insertEntity(Providers t);

    void updateEntity(Providers t);

    void removeEntity(Providers t);

    List<Providers> getEntities();
}