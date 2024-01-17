package dao.JDBC;

import dao.IEquipmentsDAO;
import model.Employees;
import model.Equipments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentsDAO implements IEquipmentsDAO {
    private static final Logger logger = LogManager.getLogger(EquipmentsDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves an Equipments entity from the database based on its ID.
     *
     * @param id The ID of the Equipments entity to retrieve.
     * @return an Equipments object retrieved from the database.
     */
    @Override
    public Equipments getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Equipments.Builder equipmentsBuilder = new Equipments.Builder();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from equipments where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                equipmentsBuilder
                        .withID(resultSet.getInt("id"))
                        .withEquipmentName(resultSet.getString("name"))
                        .withSupermarketId(resultSet.getInt("supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Equipments entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return equipmentsBuilder.build();
    }

    /**
     * Inserts an Equipments entity into the database.
     *
     * @param t The Equipments object to insert.
     */
    @Override
    public void insertEntity(Equipments t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO equipments (name, supermarket_id) VALUES (?, ?)");
            preparedStatement.setString(1, t.getEquipmentName());
            preparedStatement.setInt(2, t.getSupermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Equipments entity: {}", e.getMessage());
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
     * Updates an Equipments entity in the database.
     *
     * @param t The Equipments object to update.
     */
    @Override
    public void updateEntity(Equipments t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE equipments SET name=?, supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getEquipmentName());
            preparedStatement.setInt(2, t.getSupermarket_id());
            preparedStatement.setInt(3, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Equipment entity: {}", e.getMessage());
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
     * Removes an Equipments entity from the database.
     *
     * @param t The Equipments object to remove.
     */
    @Override
    public void removeEntity(Equipments t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM equipments WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Equipments entity: {}", e.getMessage());
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
     * Retrieves all Equipments entities from the database.
     *
     * @return A list of Equipments objects retrieved from the database.
     */
    @Override
    public List<Equipments> getEntities() {
        List<Equipments> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from equipments");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Equipments.Builder equipmentBuilder = new Equipments.Builder();
                equipmentBuilder
                        .withID(resultSet.getInt("id"))
                        .withEquipmentName(resultSet.getString("name"))
                        .withSupermarketId(resultSet.getInt("supermarket_id"));
                list.add(equipmentBuilder.build());
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Equipments entities: {}", e.getMessage());
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

    // Additional methods overridden from IEquipmentsDAO interface, left blank as not implemented in this class.
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
