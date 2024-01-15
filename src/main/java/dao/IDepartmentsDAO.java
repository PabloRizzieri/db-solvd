package dao;

import model.Departments;

import java.util.List;

public interface IDepartmentsDAO extends IBaseDAO{
    Departments getEntityById(int id);

    void insertEntity(Departments t);

    void updateEntity(Departments t);

    void removeEntity(Departments t);

    List<Departments> getEntities();
}
