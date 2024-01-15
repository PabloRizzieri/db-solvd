package dao;

import model.SupermarketProviders;

import java.util.List;

public interface ISupermarketProvidersDAO<T> extends IBaseDAO{
    SupermarketProviders getEntityById(int id);

    List<T> getProvidersBySupermarketID(int id);

    void insertEntity(SupermarketProviders t);

    void updateEntity(SupermarketProviders t);

    void removeEntity(SupermarketProviders t);

    List<SupermarketProviders> getEntities();
}
