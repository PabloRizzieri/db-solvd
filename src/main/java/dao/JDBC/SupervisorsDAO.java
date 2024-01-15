package dao.JDBC;

import dao.ISupervisorsDAO;
import model.Supervisors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupervisorsDAO implements ISupervisorsDAO {
    private static final Logger logger = LogManager.getLogger(SupervisorsDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a Supervisors entity from the database based on its ID.
     *
     * @param id The ID of the Supervisors entity to retrieve.
     * @return a Supervisors object retrieved from the database.
     */
    @Override
    public Supervisors getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Supervisors supervisors = new Supervisors();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from supervisors where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                supervisors.setID(resultSet.getInt("id"));
                supervisors.setFirstName(resultSet.getString("first_name"));
                supervisors.setLastName(resultSet.getString("last_name"));
                supervisors.setDepartment_id(resultSet.getInt("department_id"));
                supervisors.setEmployee_id(resultSet.getInt("employee_id"));
                supervisors.setEmployee_Supermarket_id(resultSet.getInt("employee_supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Supervisors entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return supervisors;
    }

    /**
     * Inserts a Supervisors entity into the database.
     *
     * @param t The Supervisors object to insert.
     */
    @Override
    public void insertEntity(Supervisors t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO supervisors (first_name, last_name, department_id, employee_id, employee_supermarket_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, t.getFirstName());
            preparedStatement.setString(2, t.getLastName());
            preparedStatement.setInt(3, t.getDepartment_id());
            preparedStatement.setInt(4, t.getEmployee_id());
            preparedStatement.setInt(5, t.getEmployee_Supermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Supervisors entity: {}", e.getMessage());
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
     * Updates a Supervisors entity in the database.
     *
     * @param t The Supervisors object to update.
     */
    @Override
    public void updateEntity(Supervisors t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE supervisors SET first_name=?, last_name=?, department_id=?, employee_id=?, employee_supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getFirstName());
            preparedStatement.setString(2, t.getLastName());
            preparedStatement.setInt(3, t.getDepartment_id());
            preparedStatement.setInt(4, t.getEmployee_id());
            preparedStatement.setInt(5, t.getEmployee_Supermarket_id());
            preparedStatement.setInt(6, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Supervisors entity: {}", e.getMessage());
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
     * Removes a Supervisors entity from the database.
     *
     * @param t The Supervisors object to remove.
     */
    @Override
    public void removeEntity(Supervisors t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM supervisors WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Supervisors entity: {}", e.getMessage());
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
     * Retrieves all Supervisors entities from the database.
     *
     * @return A list of Supervisors objects retrieved from the database.
     */
    @Override
    public List<Supervisors> getEntities() {
        List<Supervisors> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from supervisors");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Supervisors supervisors = new Supervisors();
                supervisors.setID(resultSet.getInt("id"));
                supervisors.setFirstName(resultSet.getString("first_name"));
                supervisors.setLastName(resultSet.getString("last_name"));
                supervisors.setDepartment_id(resultSet.getInt("department_id"));
                supervisors.setEmployee_id(resultSet.getInt("employee_id"));
                supervisors.setEmployee_Supermarket_id(resultSet.getInt("employee_supermarket_id"));
                list.add(supervisors);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Supervisors entities: {}", e.getMessage());
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

    // Additional methods overridden from ISupervisorsDAO interface, left blank as not implemented in this class.
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
