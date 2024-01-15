package dao;


import model.Employees;

import java.util.List;

public interface IEmployeesDAO extends IBaseDAO{
    Employees getEntityById(int id);

    void insertEntity(Employees t);

    void updateEntity(Employees t);

    void removeEntity(Employees t);

    List<Employees> getEntities();
}
