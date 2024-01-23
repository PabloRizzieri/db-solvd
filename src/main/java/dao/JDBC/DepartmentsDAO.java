package dao.JDBC;

import dao.IDepartmentsDAO;
import model.Departments;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.connectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentsDAO implements IDepartmentsDAO {
    private static final Logger logger = LogManager.getLogger(DepartmentsDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a Departments entity from the database based on its ID.
     *
     * @param id The ID of the Departments entity to retrieve.
     * @return A Departments object retrieved from the database.
     */
    @Override
    public Departments getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Departments departments = new Departments();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from departments where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                departments.setID(resultSet.getInt("id"));
                departments.setDepartmentTask(resultSet.getString("task"));
                departments.setDepartmentName(resultSet.getString("name"));
                departments.setSupermarket_id(resultSet.getInt("supermarket_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Departments entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return departments;
    }

    /**
     * Inserts a Departments entity into the database.
     *
     * @param t The Departments object to insert.
     */
    @Override
    public void insertEntity(Departments t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO departments (task, name, supermarket_id) VALUES (?, ?, ?)");
            preparedStatement.setString(1, t.getDepartmentTask());
            preparedStatement.setString(2, t.getDepartmentName());
            preparedStatement.setInt(3, t.getSupermarket_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Departments entity: {}", e.getMessage());
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
     * Updates a Departments entity in the database.
     *
     * @param t The Departments object to update.
     */
    @Override
    public void updateEntity(Departments t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE departments SET task=?, name=?, supermarket_id=? WHERE id=?");
            preparedStatement.setString(1, t.getDepartmentTask());
            preparedStatement.setString(2, t.getDepartmentName());
            preparedStatement.setInt(3, t.getSupermarket_id());
            preparedStatement.setInt(4, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Departments entity: {}", e.getMessage());
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
     * Removes a Departments entity from the database.
     *
     * @param t The Departments object to remove.
     */
    @Override
    public void removeEntity(Departments t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM departments WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Departments entity: {}", e.getMessage());
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
     * Retrieves all Departments entities from the database.
     *
     * @return A list of Departments objects retrieved from the database.
     */
    @Override
    public List<Departments> getEntities() {
        List<Departments> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from departments");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Departments departments = new Departments();
                departments.setID(resultSet.getInt("id"));
                departments.setDepartmentTask(resultSet.getString("task"));
                departments.setDepartmentName(resultSet.getString("name"));
                departments.setSupermarket_id(resultSet.getInt("supermarket_id"));
                list.add(departments);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Departments entities: {}", e.getMessage());
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

    // Additional methods overridden from IDepartmentsDAO interface, left blank as not implemented in this class.
    @Override
    public void insertEntity(Object o) {}

    @Override
    public void updateEntity(Object o, int i) {}

    @Override
    public void removeEntity(Object o) {}
}
