package dao.JDBC;

import dao.IMeatsSectorDAO;
import model.MeatsSector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.connectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeatsSectorDAO implements IMeatsSectorDAO {
    private static final Logger logger = LogManager.getLogger(MeatsSectorDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a MeatsSector entity from the database based on its ID.
     *
     * @param id The ID of the MeatsSector entity to retrieve.
     * @return A MeatsSector object retrieved from the database.
     */
    @Override
    public MeatsSector getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        MeatsSector meatsSector = new MeatsSector();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from meats_sector where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                meatsSector.setID(resultSet.getInt("id"));
                meatsSector.setType(resultSet.getString("type"));
                meatsSector.setExpirationDate(resultSet.getString("expiration_date"));
                meatsSector.setProduct_id(resultSet.getInt("product_id"));
                meatsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving MeatsSector entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return meatsSector;
    }

    /**
     * Inserts a MeatsSector entity into the database.
     *
     * @param t The MeatsSector object to insert.
     */
    @Override
    public void insertEntity(MeatsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO meats_sector (type, expiration_date, product_id, products_supermarket_id) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, t.getType());
            preparedStatement.setString(2, t.getExpirationDate());
            preparedStatement.setInt(3, t.getProduct_id());
            preparedStatement.setInt(4, t.getProducts_Supermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting MeatsSector entity: {}", e.getMessage());
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
     * Updates a MeatsSector entity in the database.
     *
     * @param t The MeatsSector object to update.
     */
    @Override
    public void updateEntity(MeatsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE meats_sector SET type=?, expiration_date=?, product_id=?, products_supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getType());
            preparedStatement.setString(2, t.getExpirationDate());
            preparedStatement.setInt(3, t.getProduct_id());
            preparedStatement.setInt(4, t.getProducts_Supermarket_id());
            preparedStatement.setInt(5, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Meats entity: {}", e.getMessage());
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
     * Removes a MeatsSector entity from the database.
     *
     * @param t The MeatsSector object to remove.
     */
    @Override
    public void removeEntity(MeatsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM meats_sector WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting MeatsSector entity: {}", e.getMessage());
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
     * Retrieves all MeatsSector entities from the database.
     *
     * @return A list of MeatsSector objects retrieved from the database.
     */
    @Override
    public List<MeatsSector> getEntities() {
        List<MeatsSector> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from meats_sector");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                MeatsSector meatsSector = new MeatsSector();
                meatsSector.setID(resultSet.getInt("id"));
                meatsSector.setType(resultSet.getString("type"));
                meatsSector.setExpirationDate(resultSet.getString("expiration_date"));
                meatsSector.setProduct_id(resultSet.getInt("product_id"));
                meatsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
                list.add(meatsSector);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving MeatsSector entities: {}", e.getMessage());
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

    // Additional methods overridden from IMeatsSectorDAO interface, left blank as not implemented in this class.
    @Override
    public void insertEntity(Object o) {}

    @Override
    public void updateEntity(Object o, int i) {}

    @Override
    public void removeEntity(Object o) {}
}
