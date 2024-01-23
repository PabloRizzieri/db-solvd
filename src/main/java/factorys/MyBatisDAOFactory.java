package factorys;

import dao.IBaseDAO;
import dao.MyBatis.*;
import enums.DAOType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.abstractFactory.AbstractFactory;

public class MyBatisDAOFactory extends AbstractFactory {
    private static Logger logger = LogManager.getLogger(MyBatisDAOFactory.class);

    /**
     * Creates a DAO instance based on the provided DAOType.
     *
     * @param DAO The type of DAO to create (Supermarket, Product, Employee).
     * @return An instance of IBaseDao based on the provided DAOType.
     */
    public static IBaseDAO createDAO(DAOType DAO) {
        switch (DAO) {
            case Supermarkets:
                return new SupermarketsDAO();
            case Employees:
                return new EmployeesDAO();
            case Products:
                return new ProductsDAO();
            case Departments:
                return new DepartmentsDAO();
            case Equipments:
                return new EquipmentsDAO();
            case Providers:
                return new ProvidersDAO();
            case SupermarketProviders:
                return new SupermarketProvidersDAO();
            case Supervisors:
                return new SupervisorsDAO();
            case MeatsSector:
                return new MeatsSectorDAO();
            case FruitsSector:
                return new FruitsSectorDAO();
            case CleaningsSector:
                return new CleaningsSectorDAO();
            case CerealsSector:
                return new CerealsSectorDAO();
            default:
                logger.error("Unexpected value: " + DAO);
                return null;
        }
    }

    /**
     * Gets the factory based on the provided DAOType.
     *
     * @param factory The type of DAO to get (Supermarket, Product, Employee, etc).
     * @return An instance of IBaseDao based on the provided DAOType.
     */
    @Override
    public IBaseDAO getFactory(DAOType factory) {
        return createDAO(factory);
    }
}
