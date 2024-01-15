package dao;

import model.Products;

import java.util.List;

public interface IProductsDAO extends IBaseDAO{
    Products getEntityById(int id);

    void insertEntity(Products t);

    void updateEntity(Products t);

    void removeEntity(Products t);

    List<Products> getEntities();
}
