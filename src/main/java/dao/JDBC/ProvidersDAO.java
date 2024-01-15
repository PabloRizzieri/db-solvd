package dao.JDBC;

import dao.IProvidersDAO;
import model.Providers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvidersDAO implements IProvidersDAO {
    private static final Logger logger = LogManager.getLogger(ProvidersDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a Providers entity from the database based on its ID.
     *
     * @param id The ID of the Providers entity to retrieve.
     * @return a Providers object retrieved from the database.
     */
    @Override
    public Providers getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Providers providers = new Providers();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from providers where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                providers.setID(resultSet.getInt("id"));
                providers.setProviderName(resultSet.getString("provider_name"));
                providers.setProviderBranch(resultSet.getString("provider_branch"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Providers entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return providers;
    }

    /**
     * Inserts a Providers entity into the database.
     *
     * @param t The Providers object to insert.
     */
    @Override
    public void insertEntity(Providers t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO providers (provider_name, provider_branch) VALUES (?, ?)");
            preparedStatement.setString(1, t.getProviderName());
            preparedStatement.setString(2, t.getProviderBranch());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Providers entity: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing PreparedStatement: {}", e.getMessage());
            }
        }
    }

    /**
     * Updates a Providers entity in the database.
     *
     * @param t The Providers object to update.
     */
    @Override
    public void updateEntity(Providers t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE providers SET provider_name=?, provider_branch=? WHERE id=?");
            preparedStatement.setString(1, t.getProviderName());
            preparedStatement.setString(2, t.getProviderBranch());
            preparedStatement.setInt(3, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Providers entity: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            try {
                assert preparedStatement != null;
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("Error closing PreparedStatement: {}", e.getMessage());
            }
        }
    }

    /**
     * Removes a Providers entity from the database.
     *
     * @param t The Providers object to remove.
     */
    @Override
    public void removeEntity(Providers t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM providers WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Providers entity: {}", e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("Error closing PreparedStatement: {}", e.getMessage());
            }
            connectionPool.putBack(connection);
        }
    }

    /**
     * Retrieves all Providers entities from the database.
     *
     * @return A list of Providers objects retrieved from the database.
     */
    @Override
    public List<Providers> getEntities() {
        List<Providers> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from providers");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Providers providers = new Providers();
                providers.setID(resultSet.getInt("id"));
                providers.setProviderName(resultSet.getString("provider_name"));
                providers.setProviderBranch(resultSet.getString("provider_branch"));
                list.add(providers);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Providers entities: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }
        return list;
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

}
