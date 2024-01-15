package dao.JDBC;

import dao.IFruitsSectorDAO;
import model.FruitsSector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitsSectorDAO implements IFruitsSectorDAO {
    private static final Logger logger = LogManager.getLogger(FruitsSectorDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a FruitsSector entity from the database based on its ID.
     *
     * @param id The ID of the FruitsSector entity to retrieve.
     * @return A FruitsSector object retrieved from the database.
     */
    @Override
    public FruitsSector getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        FruitsSector fruitsSector = new FruitsSector();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from fruits_sector where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                fruitsSector.setID(resultSet.getInt("id"));
                fruitsSector.setType(resultSet.getString("type"));
                fruitsSector.setProduct_id(resultSet.getInt("product_id"));
                fruitsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving FruitsSector entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return fruitsSector;
    }

    /**
     * Inserts a FruitsSector entity into the database.
     *
     * @param t The FruitsSector object to insert.
     */
    @Override
    public void insertEntity(FruitsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO fruits_sector (type, product_id, products_supermarket_id) VALUES (?, ?, ?)");
            preparedStatement.setString(1, t.getType());
            preparedStatement.setInt(2, t.getProduct_id());
            preparedStatement.setInt(3, t.getProducts_Supermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting FruitsSector entity: {}", e.getMessage());
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
     * Updates a FruitsSector entity in the database.
     *
     * @param t The FruitsSector object to update.
     */
    @Override
    public void updateEntity(FruitsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE fruits_sector SET type=?, product_id=?, products_supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getType());
            preparedStatement.setInt(2, t.getProduct_id());
            preparedStatement.setInt(3, t.getProducts_Supermarket_id());
            preparedStatement.setInt(4, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Fruits entity: {}", e.getMessage());
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
     * Removes a FruitsSector entity from the database.
     *
     * @param t The FruitsSector object to remove.
     */
    @Override
    public void removeEntity(FruitsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM fruits_sector WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting FruitsSector entity: {}", e.getMessage());
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
     * Retrieves all FruitsSector entities from the database.
     *
     * @return A list of FruitsSector objects retrieved from the database.
     */
    @Override
    public List<FruitsSector> getEntities() {
        List<FruitsSector> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from fruits_sector");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                FruitsSector fruitsSector = new FruitsSector();
                fruitsSector.setID(resultSet.getInt("id"));
                fruitsSector.setType(resultSet.getString("type"));
                fruitsSector.setProduct_id(resultSet.getInt("product_id"));
                fruitsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
                list.add(fruitsSector);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving FruitsSector entities: {}", e.getMessage());
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

    // Additional methods overridden from IFruitsSector interface, left blank as not implemented in this class.
    @Override
    public void insertEntity(Object o) {}

    @Override
    public void updateEntity(Object o, int i) {}

    @Override
    public void removeEntity(Object o) {}
}
