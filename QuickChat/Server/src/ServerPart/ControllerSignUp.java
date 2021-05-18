package ServerPart;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerSignUp{
    public ServerPart.LoginModel loginModel=new ServerPart.LoginModel();


    @FXML
    private TextField txtname;
    @FXML
    private TextField txtusername1;
    @FXML
    private TextField txtpassword1;
    @FXML
    private TextField txtretypepassword1;
    @FXML
    private Label mylabel2;
    @FXML
    private Button signupbtn;


    @FXML
    public void signuphandlekey()
    {

        String text1=txtname.getText();
        String text2=txtusername1.getText();
        String text3=txtpassword1.getText();
        String text4=txtretypepassword1.getText();
        boolean disableButtons=(text1.isEmpty()|| text1.trim().isEmpty()) || (text2.isEmpty()|| text2.trim().isEmpty()) ||
                (text3.isEmpty() || text3.trim().isEmpty()) || (text4.isEmpty() || text4.trim().isEmpty());
        // boolean disableButtons2=(text2.isEmpty()|| text2.trim().isEmpty());

        signupbtn.setDisable(disableButtons);

    }


    public void SignupCheck(ActionEvent event) throws IOException {



            if (txtpassword1.getText().equals(txtretypepassword1.getText())) {
                if (loginModel.SignupNow(txtname.getText(), txtusername1.getText(), txtpassword1.getText())) {

                    // mylabel1.setText("Valid Username and Password");
                    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("QUICKCHAT");
                    stage.setScene(new Scene(root, 450, 400));
                    stage.show();

                } else {
                    // mylabel1.setText("Invalid Username and Password");
                }
            } else {
                mylabel2.setText("Password is Incorrect");
            }
        }





    public void goToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage =  (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("QUICKCHAT");
        stage.setScene(new Scene(root,450,400));
        stage.show();
    }

}