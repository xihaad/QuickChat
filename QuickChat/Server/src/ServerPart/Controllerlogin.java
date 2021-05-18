package ServerPart;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controllerlogin implements Initializable {

    public ServerPart.LoginModel loginModel=new ServerPart.LoginModel();

    @FXML
    private Label mylabel1;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField txtpassword;
    @FXML
    private Button loginbtn;


    @FXML
    public void loghandlekey()
    {

        String text1=txtusername.getText();
         String text2=txtpassword.getText();
        boolean disableButtons=(text1.isEmpty()|| text1.trim().isEmpty()) || (text2.isEmpty()|| text2.trim().isEmpty());
        // boolean disableButtons2=(text2.isEmpty()|| text2.trim().isEmpty());

        loginbtn.setDisable(disableButtons);

    }


    
    public void LoginCheck(ActionEvent event)
    {
       // String text1=txtusername.getText();
       // String text2=txtpassword.getText();
      //  if((text1.isEmpty() || text1.trim().isEmpty() || text2.isEmpty() || text2.trim().isEmpty())){
        try{
            if(loginModel.LoginNow(txtusername.getText(),txtpassword.getText()))
            {

                Parent root = FXMLLoader.load(getClass().getResource("chatserver.fxml"));
                Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("QUICKCHAT");
                stage.setScene(new Scene(root,450,400));
                stage.show();
            }
            else {
                mylabel1.setText("Invalid Username and Password");
            }
        }catch (SQLException | IOException e)
        {
            e.printStackTrace();
          //  mylabel1.setText("Invalid Username and Password");
        }
    }

    public void goToSignup(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Stage stage =  (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("QUICKCHAT");
        stage.setScene(new Scene(root,450,400));
        stage.show();
    }

    public void goToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage =  (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("QUICKCHAT");
        stage.setScene(new Scene(root,450,400));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(loginModel.isDbConnected())
        {

        //    mylabel1.setText("Connected to database");
        }
        else
        {

            mylabel1.setText("NOt connected");
        }

    }

}
