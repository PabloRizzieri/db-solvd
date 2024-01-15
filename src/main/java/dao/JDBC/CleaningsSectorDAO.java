package dao.JDBC;

import dao.ICleaningsSectorDAO;
import model.CleaningsSector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CleaningsSectorDAO implements ICleaningsSectorDAO {
    private static final Logger logger = LogManager.getLogger(CleaningsSectorDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a CleaningsSector entity from the database based on its ID.
     *
     * @param id The ID of the CleaningsSector entity to retrieve.
     * @return A CleaningsSector object retrieved from the database.
     */
    @Override
    public CleaningsSector getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        CleaningsSector cleaningsSector = new CleaningsSector();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from cleanings_sector where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                cleaningsSector.setID(resultSet.getInt("id"));
                cleaningsSector.setType(resultSet.getString("type"));
                cleaningsSector.setProduct_id(resultSet.getInt("product_id"));
                cleaningsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving CleaningsSector entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return cleaningsSector;
    }

    /**
     * Inserts a CleaningsSector entity into the database.
     *
     * @param t The CleaningsSector object to insert.
     */
    @Override
    public void insertEntity(CleaningsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cleanings_sector (type, product_id, products_supermarket_id) VALUES (?, ?, ?)");
            preparedStatement.setString(1, t.getType());
            preparedStatement.setInt(2, t.getProduct_id());
            preparedStatement.setInt(3, t.getProducts_Supermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting CleaningsSector entity: {}", e.getMessage());
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
     * Updates a CleaningsSector entity in the database.
     *
     * @param t The CleaningsSector object to update.
     */
    @Override
    public void updateEntity(CleaningsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE cleanings_sector SET type=?, product_id=?, products_supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getType());
            preparedStatement.setInt(2, t.getProduct_id());
            preparedStatement.setInt(3, t.getProducts_Supermarket_id());
            preparedStatement.setInt(4, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Cleanings entity: {}", e.getMessage());
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
     * Removes a CleaningsSector entity from the database.
     *
     * @param t The CleaningsSector object to remove.
     */
    @Override
    public void removeEntity(CleaningsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cleanings_sector WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting CleaningsSector entity: {}", e.getMessage());
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
     * Retrieves all CleaningsSector entities from the database.
     *
     * @return A list of CleaningsSector objects retrieved from the database.
     */
    @Override
    public List<CleaningsSector> getEntities() {
        List<CleaningsSector> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from cleanings_sector");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                CleaningsSector cleaningsSector = new CleaningsSector();
                cleaningsSector.setID(resultSet.getInt("id"));
                cleaningsSector.setType(resultSet.getString("type"));
                cleaningsSector.setProduct_id(resultSet.getInt("product_id"));
                cleaningsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
                list.add(cleaningsSector);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving CleaningsSector entities: {}", e.getMessage());
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

    // Additional methods overridden from ICleaningsSectorDAO interface, left blank as not implemented in this class.
    @Override
    public void insertEntity(Object o) {}

    @Override
    public void updateEntity(Object o, int i) {}

    @Override
    public void removeEntity(Object o) {}
}
