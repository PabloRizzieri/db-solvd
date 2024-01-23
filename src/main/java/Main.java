import controller.EquipmentController;
import dao.IEquipmentsDAO;
import decorator.ProvidersDecorator;
import decorator.ProvidersDecoratorImplementation;
import enums.DAOType;
import enums.FactoryType;
import facade.DepartmentsFacade;
import model.Departments;
import model.Equipments;
import model.Providers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import strategy.SortByEquipmentID;
import utils.dao.DAOFactoryGenerator;
import view.ConsoleEquipmentView;
import view.EquipmentView;

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

        // -------- DECORATOR ---------
        // Create an instance of Providers
        Providers originalProviders = new Providers();
        originalProviders.setID(1);
        originalProviders.setProviderName("Example Provider");
        originalProviders.setProviderBranch("Main Branch");

        // Create a decorator for Providers
        ProvidersDecorator decorator = new ProvidersDecoratorImplementation(originalProviders);

        // Show original information
        System.out.println("Original Information: " + originalProviders.toString());

        // Show decorated info
        System.out.println(decorator.displayInfo());


        // ------- FACADE ---------
        // Create an instance of Departments
        Departments originalDepartments = new Departments();
        originalDepartments.setID(1);
        originalDepartments.setDepartmentName("Example Department");
        originalDepartments.setSupermarket_id(123);

        // Create an instance of Departments Facade
        DepartmentsFacade departmentsFacade = new DepartmentsFacade(originalDepartments);

        // Utilize the facade to interact with Departments
        System.out.println(departmentsFacade.getDepartmentInfo());

        departmentsFacade.assignTask("Manage Inventory");

    }
}
