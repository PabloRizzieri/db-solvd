package dao.JDBC;

import dao.ISupermarketProvidersDAO;
import model.Providers;
import model.SupermarketProviders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.connectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupermarketProvidersDAO implements ISupermarketProvidersDAO {
    private static final Logger logger = LogManager.getLogger(SupermarketProvidersDAO.class);
    private static final String SELECT_PROVIDERS_BY_SUPERMARKET_ID =
            "SELECT p.* FROM providers p " + "INNER JOIN supermarket_providers sp ON p.id = sp.provider_id" +
            "WHERE sp.supermarket_id = ?";
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a SupermarketProviders entity from the database based on its supermarket ID.
     *
     * @param supermarketsID The supermarkets of the SupermarketProviders entity to retrieve.
     * @return a SupermarketProviders object retrieved from the database.
     */
    @Override
    public List<Providers> getProvidersBySupermarketID(int supermarketsID) {
        List<Providers> providers = new ArrayList<>();

        Connection connection = connectionPool.retrieve();
        SupermarketProviders supermarketProviders = new SupermarketProviders();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_PROVIDERS_BY_SUPERMARKET_ID);
            preparedStatement.setInt(1, supermarketsID);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Providers provider = new Providers();
                provider.setProviderName(resultSet.getString("provider_name"));
                provider.setProviderBranch(resultSet.getString("provider_branch"));
                providers.add(provider);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving SupermarketProviders entity by supermarketID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return providers;
    }

    @Override
    public List<SupermarketProviders> getEntities() {
        return null;
    }

    private void closeAll(PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error closing ResultSet: {}", e.getMessage());
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing PreparedStatement: {}", e.getMessage());
            }
        }
    }

    // Additional methods overridden from IProvidersDAO interface, left blank as not implemented in this class.
    @Override
    public void insertEntity(Object o) {
    }

    @Override
    public void updateEntity(Object o, int i) {
    }

    @Override
    public void removeEntity(Object o) {
    }

    // Not applicable



    @Override
    public void insertEntity(SupermarketProviders t) {
        throw new RuntimeException(new Error("Not Usable"));
    }

    @Override
    public void updateEntity(SupermarketProviders t) {
        throw new RuntimeException(new Error("Not Usable"));

    }

    @Override
    public void removeEntity(SupermarketProviders t) {
        throw new RuntimeException(new Error("Not Usable"));
    }

    @Override
    public SupermarketProviders getEntityById(int id){
        throw new RuntimeException(new Error("Not Usable"));
    }

}
