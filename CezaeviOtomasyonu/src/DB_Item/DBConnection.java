
package DB_Item;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    Connection c = null;

    public DBConnection() {
    }
    
    public Connection connDB(){
        try {
            this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cezaevi?user=root&password=mertcanakdas");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
}
