package utils;

import dao.IBaseDAO;
import enums.DAOType;

/**
 * An abstract factory class defining the contract for getting specific DAO factories.
 */
public abstract class AbstractFactory {

    /**
     * Retrieves the specific DAO factory based on the provided DAOType.
     *
     * @param factory The type of DAO factory to retrieve (Employee, Supermarket, etc.).
     * @return An instance of IBaseDao based on the provided DAOType.
     */
    public abstract IBaseDAO getFactory(DAOType factory);
}
