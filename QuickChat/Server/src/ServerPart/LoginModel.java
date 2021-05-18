package ServerPart;

import java.sql.*;

 public class LoginModel {
    Connection connection;
    public LoginModel()
    {
        connection = ServerPart.SQliteConnection.connector();
        if(connection==null) System.exit(1);
    }
    public boolean isDbConnected()
    {
        try {
            return !connection.isClosed();
        }
        catch (SQLException e)
        {
            return false;
        }
    }
    public boolean LoginNow(String user,String pass) throws SQLException
    {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM contacts WHERE username = ? and password = ?";
        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user);
            preparedStatement.setString(2,pass);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
            else {
                return false;
            }

        }catch (Exception e)
        {
            return false;
        }
        finally {
            preparedStatement.close();
            resultSet.close();
        }
    }
    public boolean SignupNow(String name, String username, String password)
    {
        PreparedStatement preparedStatement=null;
        String insert="INSERT INTO contacts(name,username,password) VALUES(?,?,?)";
        try {
            preparedStatement=connection.prepareStatement(insert);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.execute();
            return true;

        }catch (Exception e)
        {

            System.out.println();
        }

        return false;
    }

}
