package utils.connectionPool;

import java.sql.Connection;

/**
 * Factory interface for creating Connection objects.
 */
public interface ConnectionFactory {
    Connection createConnection();
}
