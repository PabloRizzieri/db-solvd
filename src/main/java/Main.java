import controller.EquipmentController;
import dao.IEquipmentsDAO;
import enums.DAOType;
import enums.FactoryType;
import model.Equipments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import strategy.SortByEquipmentID;
import utils.DAOFactoryGenerator;
import view.ConsoleEquipmentView;
import view.EquipmentView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        // Proxy Pattern
        IEquipmentsDAO equipmentsDAO = (IEquipmentsDAO) DAOFactoryGenerator.createFactory(FactoryType.MYBATIS).getFactory(DAOType.Equipments);
        System.out.println(equipmentsDAO.getEntities());

        // Builder Pattern
        Equipments equipment4 = new Equipments.Builder()
                .withID(4)
                .withEquipmentName("Pencils")
                .withSupermarketId(1)
                .build();

        Equipments equipment1 = new Equipments.Builder()
                .withID(1)
                .withEquipmentName("Register")
                .withSupermarketId(1)
                .build();

        Equipments equipment2 = new Equipments.Builder()
                .withID(2)
                .withEquipmentName("Freezer")
                .withSupermarketId(2)
                .build();

        Equipments equipment3 = new Equipments.Builder()
                .withID(3)
                .withEquipmentName("Tables")
                .withSupermarketId(3)
                .build();

        // Equipment Listener
        Equipments.EquipmentListener equipmentListener = new Equipments.EquipmentListener() {
            @Override
            public void onEquipmentChanged(Equipments equipment) {
                System.out.println("Event: New Equipment created: " + equipment);
            }
        };

        equipmentListener.onEquipmentChanged(equipment1);

        // MVC Pattern
        List<Equipments> equipmentsList = new ArrayList<>();
        equipmentsList.add(equipment4);
        equipmentsList.add(equipment1);
        equipmentsList.add(equipment2);
        equipmentsList.add(equipment3);

        EquipmentView equipmentView = new ConsoleEquipmentView(); // Here we can implement different approaches for the view (Console, GUI, Web)
        EquipmentController equipmentController = new EquipmentController(equipmentView, equipmentsList);


        // Display details of Equipment by ID
        equipmentController.displayEquipmentDetails(2);

        // Display all the equipments
        equipmentController.displayAllEquipments();

        // Use a specific sort strategy (this can be change dynamically)
        equipmentController.setSortStrategy(new SortByEquipmentID());

        // Display the sorted Equipments
        equipmentController.displaySortEquipments();

    }
}
