package dao;

import model.Supervisors;

import java.util.List;

public interface ISupervisorsDAO extends IBaseDAO{
    Supervisors getEntityById(int id);

    void insertEntity(Supervisors t);

    void updateEntity(Supervisors t);

    void removeEntity(Supervisors t);

    List<Supervisors> getEntities();
}
