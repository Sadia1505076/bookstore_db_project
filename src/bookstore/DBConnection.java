
package bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String username;
    private String password;
    private final String CONN_STRING = "jdbc:oracle:thin:@localhost:1521:orcl";
    public Connection connection = null;
    //private static OracleDBMS instance = null;
     public DBConnection()
    {
        this.username = "SADIA";
        this.password = "sadia";
    }

    public DBConnection(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(CONN_STRING, username, password);
            } catch (SQLException e)
            {
                System.out.println("Connection Failed! Check it from console");
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void closeConnection()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
