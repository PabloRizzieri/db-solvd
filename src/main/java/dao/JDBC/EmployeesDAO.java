package dao.JDBC;

import dao.IEmployeesDAO;
import model.Employees;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.connectionPool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDAO implements IEmployeesDAO {
    private static final Logger logger = LogManager.getLogger(EmployeesDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves an Employees entity from the database based on its ID.
     *
     * @param id The ID of the Employees entity to retrieve.
     * @return an Employees object retrieved from the database.
     */
    @Override
    public Employees getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Employees employees = new Employees();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from employees where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                employees.setID(resultSet.getInt("id"));
                employees.setFirstName(resultSet.getString("first_name"));
                employees.setLastName(resultSet.getString("last_name"));
                employees.setAge(resultSet.getInt("age"));
                employees.setSupermarket_id(resultSet.getInt("supermarket_id"));
                employees.setDepartment_id(resultSet.getInt("department_id"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Employees entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return employees;
    }

    /**
     * Inserts an Employees entity into the database.
     *
     * @param t The Employees object to insert.
     */
    @Override
    public void insertEntity(Employees t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO employees (first_name, last_name, age, supermarket_id, department_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, t.getFirstName());
            preparedStatement.setString(2, t.getLastName());
            preparedStatement.setInt(3, t.getAge());
            preparedStatement.setInt(4, t.getSupermarket_id());
            preparedStatement.setInt(5, t.getDepartment_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Employees entity: {}", e.getMessage());
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
     * Updates an Employees entity in the database.
     *
     * @param t The Employees object to update.
     */
    @Override
    public void updateEntity(Employees t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE employees SET first_name=?, last_name=?, age=?, supermarket_id=?, department_id=? WHERE id=?");
            preparedStatement.setString(1, t.getFirstName());
            preparedStatement.setString(2, t.getLastName());
            preparedStatement.setInt(3, t.getAge());
            preparedStatement.setInt(4, t.getSupermarket_id());
            preparedStatement.setInt(5, t.getDepartment_id());
            preparedStatement.setInt(6, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Employees entity: {}", e.getMessage());
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
     * Removes an Employees entity from the database.
     *
     * @param t The Employees object to remove.
     */
    @Override
    public void removeEntity(Employees t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Employees entity: {}", e.getMessage());
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
     * Retrieves all Employees entities from the database.
     *
     * @return A list of Employees objects retrieved from the database.
     */
    @Override
    public List<Employees> getEntities() {
        List<Employees> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from employees");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Employees employees = new Employees();
                employees.setID(resultSet.getInt("id"));
                employees.setFirstName(resultSet.getString("first_name"));
                employees.setLastName(resultSet.getString("last_name"));
                employees.setSupermarket_id(resultSet.getInt("supermarket_id"));
                employees.setDepartment_id(resultSet.getInt("department_id"));
                list.add(employees);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Employees entities: {}", e.getMessage());
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

    // Additional methods overridden from IEmployeesDAO interface, left blank as not implemented in this class.
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
