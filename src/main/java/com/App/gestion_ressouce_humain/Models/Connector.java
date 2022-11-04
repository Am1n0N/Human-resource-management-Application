package Models;
import java.sql.*;
public class Connector {
    private Connection conn;
    public Connection connect() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hrm","root","");

        }catch(Exception ex){
            System.out.println(ex);
        }
        return conn;
    }
}
