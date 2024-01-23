package dao.JDBC;

import dao.ICerealsSectorDAO;
import model.CerealsSector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.connectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CerealsSectorDAO implements ICerealsSectorDAO {
    private static final Logger logger = LogManager.getLogger(CerealsSectorDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a CerealSector entity from the database based on its ID.
     *
     * @param id The ID of the CerealSector entity to retrieve.
     * @return A CerealSector object retrieved from the database.
     */
    @Override
    public CerealsSector getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        CerealsSector cerealsSector = new CerealsSector();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from cereal_sector where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                cerealsSector.setID(resultSet.getInt("id"));
                cerealsSector.setFlavour(resultSet.getString("flavour"));
                cerealsSector.setProduct_id(resultSet.getInt("product_id"));
                cerealsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving CerealSector entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return cerealsSector;
    }

    /**
     * Inserts a CerealSector entity into the database.
     *
     * @param t The CerealSector object to insert.
     */
    @Override
    public void insertEntity(CerealsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cereal_sector (flavour, product_id, products_supermarket_id) VALUES (?, ?, ?)");
            preparedStatement.setString(1, t.getFlavour());
            preparedStatement.setInt(2, t.getProduct_id());
            preparedStatement.setInt(3, t.getProducts_Supermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting CerealSector entity: {}", e.getMessage());
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
     * Updates a CerealSector entity in the database.
     *
     * @param t The CerealSector object to update.
     */
    @Override
    public void updateEntity(CerealsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE cereal_sector SET flavour=?, product_id=?, products_supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getFlavour());
            preparedStatement.setInt(2, t.getProduct_id());
            preparedStatement.setInt(3, t.getProducts_Supermarket_id());
            preparedStatement.setInt(4, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating CerealSector entity: {}", e.getMessage());
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
     * Removes a CerealSector entity from the database.
     *
     * @param t The CerealSector object to remove.
     */
    @Override
    public void removeEntity(CerealsSector t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cereal_sector WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting CerealSector entity: {}", e.getMessage());
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
     * Retrieves all CerealSector entities from the database.
     *
     * @return A list of CerealSector objects retrieved from the database.
     */
    @Override
    public List<CerealsSector> getEntities() {
        List<CerealsSector> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from cereal_sector");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                CerealsSector cerealsSector = new CerealsSector();
                cerealsSector.setID(resultSet.getInt("id"));
                cerealsSector.setFlavour(resultSet.getString("flavour"));
                cerealsSector.setProduct_id(resultSet.getInt("product_id"));
                cerealsSector.setProducts_Supermarket_id(resultSet.getInt("products_supermarket_id"));
                list.add(cerealsSector);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving CerealSector entities: {}", e.getMessage());
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

    // Additional methods overridden from ICerealSectorDAO interface, left blank as not implemented in this class.
    @Override
    public void insertEntity(Object o) {}

    @Override
    public void updateEntity(Object o, int i) {}

    @Override
    public void removeEntity(Object o) {}
}
