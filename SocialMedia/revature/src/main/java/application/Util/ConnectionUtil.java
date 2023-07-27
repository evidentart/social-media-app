package application.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {


    //local variables can be used for connectivity and make it more secure, this method is for only demo purposes.

    private static String url = "add url of database";  
    private static String username = "add database username";
    private static String password = "add database password";
 
    private static Connection connection = null;
    
    public static Connection getConnection(){

        if(connection == null){

            try {
                connection = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
      }
}
