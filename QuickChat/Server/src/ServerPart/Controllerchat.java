package ServerPart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controllerchat implements Initializable {

    static StringBuffer msgContainer = new StringBuffer();
    @FXML
    TextArea mesgArea;
    @FXML
    TextField mesgField;
    @FXML
    Label serverLabel;
    @FXML
    Button sendButton;

    @FXML
    public void loghandlekey()
    {

        String text1=mesgField.getText();
        boolean disableButtons=text1.isEmpty()|| text1.trim().isEmpty();
        // boolean disableButtons2=(text2.isEmpty()|| text2.trim().isEmpty());

        sendButton.setDisable(disableButtons);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new ServerPart.ServerChatThread(mesgArea,serverLabel).start();
    }

    public void send(){
        new ServerPart.SendHandling(mesgField, mesgArea).start();
    }




    public void goToLogin(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage =  (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setTitle("QUICKCHAT");
        stage.setScene(new Scene(root,450,400));
        stage.show();
    }


}
