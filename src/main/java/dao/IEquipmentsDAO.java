package dao;

import model.Equipments;

import java.util.List;

public interface IEquipmentsDAO extends IBaseDAO{
    Equipments getEntityById(int id);

    void insertEntity(Equipments t);

    void updateEntity(Equipments t);

    void removeEntity(Equipments t);

    List<Equipments> getEntities();
}
