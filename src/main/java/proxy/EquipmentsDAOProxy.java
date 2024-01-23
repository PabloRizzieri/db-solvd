package proxy;

import dao.IEquipmentsDAO;
import dao.JDBC.EquipmentsDAO;
import model.Equipments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class EquipmentsDAOProxy implements IEquipmentsDAO {
    private EquipmentsDAO realEquipmentDAO = new EquipmentsDAO();
    private static final Logger LOGGER = LogManager.getLogger(EquipmentsDAOProxy.class);

    @Override
    public Equipments getEntityById(int id){
        LOGGER.info("Proxy: Performing additional operations before retrieving Equipments by ID");
        System.out.println("Proxy");
        Equipments equipment = realEquipmentDAO.getEntityById(id);
        LOGGER.info("Proxy: Performing additional operations after retrieving Equipments by ID");
        return equipment;
    }

    @Override
    public void insertEntity(Equipments equipments){
        LOGGER.info("Proxy: Performing additional operations before inserting Equipments");
        realEquipmentDAO.insertEntity(equipments);
        LOGGER.info("Proxy: Performing additional operations after inserting Equipments");
    }

    @Override
    public void updateEntity(Equipments equipments){
        LOGGER.info("Proxy: Performing additional operations before updating Equipments");
        realEquipmentDAO.updateEntity(equipments);
        LOGGER.info("Proxy: Performing additional operations after updating Equipments");
    }

    @Override
    public void removeEntity(Equipments equipments){
        LOGGER.info("Proxy: Performing additional operations before deleting Equipments");
        realEquipmentDAO.removeEntity(equipments);
        LOGGER.info("Proxy: Performing additional operations after deleting Equipments");
    }

    public List<Equipments> getEntities(){
        LOGGER.info("Proxy: Performing additional operations before retrieving all Equipment entities");
        List<Equipments> equipmentList = realEquipmentDAO.getEntities();
        LOGGER.info("Proxy: Performing additional operations after retrieving all Equipment entities");
        return equipmentList;
    }

    @Override
    public void insertEntity(Object o) {
    }

    @Override
    public void updateEntity(Object o, int i) {
    }

    @Override
    public void removeEntity(Object o) {
    }

}
