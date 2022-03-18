package thanhha.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper implements Serializable {
    public static Connection makeConnection() throws NamingException, SQLException {
        //1. Get current context
        Context context = new InitialContext();
        //2. Get container context
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //3. Get DataSource from container
        DataSource dataSource = (DataSource) tomcatContext.lookup("DSBlink");
        //4. Get connection
        Connection connection = dataSource.getConnection();
        return connection;
    }

        public static void closeConnection(Connection connection, ResultSet resultSet, PreparedStatement statement)
            throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
