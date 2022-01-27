package tests.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbUtils {

    private Properties jdbcProps;

    public DbUtils(Properties props){
        jdbcProps=props;
    }

    private  Connection instance=null;

    private Connection getNewConnection() throws ClassNotFoundException {
        String driver = jdbcProps.getProperty("jdbc.driver");
        String url=jdbcProps.getProperty("jdbc.url");
        String user=jdbcProps.getProperty("jdbc.user");
        String pass=jdbcProps.getProperty("jdbc.pass");
        Connection con=null;
        try {
            Class.forName(driver);
            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error getting connection {}" + e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error DB " + e);
        }
        return instance;
    }

    public void closeConnection() {
        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
                System.out.println("connection successfully closed!");
            }
            else {
                System.out.println("connection was already closed or was null");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}