package dao.JDBC;

import dao.IProductsDAO;
import model.Products;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO implements IProductsDAO {
    private static final Logger logger = LogManager.getLogger(ProductsDAO.class);
    ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Retrieves a Products entity from the database based on its ID.
     *
     * @param id The ID of the Products entity to retrieve.
     * @return a Products object retrieved from the database.
     */
    @Override
    public Products getEntityById(int id) {
        Connection connection = connectionPool.retrieve();
        Products products = new Products();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from products where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                products.setID(resultSet.getInt("id"));
                products.setSupermarket_id(resultSet.getInt("supermarket_id"));
                products.setProvider_id(resultSet.getInt("provider_id"));
                products.setProductName(resultSet.getString("product_name"));
                products.setCategory(resultSet.getString("category"));
                products.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Products entity by ID: {}", e.getMessage());
        } finally {
            connectionPool.putBack(connection);
            closeAll(preparedStatement, resultSet);
        }

        return products;
    }

    /**
     * Inserts a Products entity into the database.
     *
     * @param t The Products object to insert.
     */
    @Override
    public void insertEntity(Products t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO products (supermarket_id, provider_id, product_name, category, price) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, t.getSupermarket_id());
            preparedStatement.setInt(2, t.getProvider_id());
            preparedStatement.setString(3, t.getProductName());
            preparedStatement.setString(4, t.getCategory());
            preparedStatement.setDouble(5, t.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error inserting Products entity: {}", e.getMessage());
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
     * Updates a Products entity in the database.
     *
     * @param t The Products object to update.
     */
    @Override
    public void updateEntity(Products t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE products SET supermarket_id=?, provider_id=?, product_name=?, category=?, price=? WHERE id=?");
            preparedStatement.setInt(1, t.getSupermarket_id());
            preparedStatement.setInt(2, t.getProvider_id());
            preparedStatement.setString(3, t.getProductName());
            preparedStatement.setString(4, t.getCategory());
            preparedStatement.setDouble(5, t.getPrice());
            preparedStatement.setInt(6, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating Products entity: {}", e.getMessage());
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
     * Removes a Products entity from the database.
     *
     * @param t The Products object to remove.
     */
    @Override
    public void removeEntity(Products t) {
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM products WHERE id=?");
            preparedStatement.setInt(1, t.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting Products entity: {}", e.getMessage());
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
     * Retrieves all Products entities from the database.
     *
     * @return A list of Products objects retrieved from the database.
     */
    @Override
    public List<Products> getEntities() {
        List<Products> list = new ArrayList<>();
        Connection connection = connectionPool.retrieve();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("Select * from products");
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Products products = new Products();
                products.setID(resultSet.getInt("id"));
                products.setSupermarket_id(resultSet.getInt("supermarket_id"));
                products.setProvider_id(resultSet.getInt("provider_id"));
                products.setProductName(resultSet.getString("product_name"));
                products.setCategory(resultSet.getString("category"));
                products.setPrice(resultSet.getDouble("price"));
                list.add(products);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving Products entities: {}", e.getMessage());
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

    // Additional methods overridden from IProductsDAO interface, left blank as not implemented in this class.
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
