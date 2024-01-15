package dao.JDBC;

import dao.ISupermarketsDAO;
import model.Supermarkets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupermarketsDAO implements ISupermarketsDAO {
    private static final Logger logger = LogManager.getLogger(SupermarketsDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a Supermarkets entity from the database based on its ID.
     *
     * @param id The ID of the Supermarkets entity to retrieve.
     * @return a Supermarkets object retrieved from the database.
     */
    @Override
    public Supermarkets getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Supermarkets supermarkets = new Supermarkets();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from supermarkets where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                supermarkets.setID(resultSet.getInt("id"));
                supermarkets.setName(resultSet.getString("supermarket_name"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Supermarkets entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return supermarkets;
    }

    /**
     * Inserts a Supermarkets entity into the database.
     *
     * @param t The Supermarkets object to insert.
     */
    @Override
    public void insertEntity(Supermarkets t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO supermarkets (supermarket_name) VALUES (?)");
            preparedStatement.setString(1, t.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Supermarkets entity: {}", e.getMessage());
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
     * Updates a Supermarkets entity in the database.
     *
     * @param t The Supermarkets object to update.
     */
    @Override
    public void updateEntity(Supermarkets t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE supermarkets SET supermarket_name=? WHERE id=?");
            preparedStatement.setString(1, t.getName());
            preparedStatement.setInt(2, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Supermarkets entity: {}", e.getMessage());
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
     * Removes a Supermarkets entity from the database.
     *
     * @param t The Supermarkets object to remove.
     */
    @Override
    public void removeEntity(Supermarkets t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM supermarkets WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Supermarkets entity: {}", e.getMessage());
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
     * Retrieves all Supermarkets entities from the database.
     *
     * @return A list of Supermarkets objects retrieved from the database.
     */
    @Override
    public List<Supermarkets> getEntities() {
        List<Supermarkets> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from supermarkets");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Supermarkets supermarkets = new Supermarkets();
                supermarkets.setID(resultSet.getInt("id"));
                supermarkets.setName(resultSet.getString("supermarket_name"));
                list.add(supermarkets);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Supermarkets entities: {}", e.getMessage());
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

    // Additional methods overridden from ISupermarketsDAO interface, left blank as not implemented in this class.
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
