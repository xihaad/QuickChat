package ClientPart;
import java.sql.*;

 public class SQliteConnection {
    public static Connection connector()
    {

        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Client.db");
            Statement statement=conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(name TEXT,username TEXT, password TEXT)");
            // statement.execute("INSERT INTO contacts(name,username,password) VALUES('a','b','c')");


            return conn;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

