package ServerPart;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import static ServerPart.Controllerchat.msgContainer;

public class ServerChatThread extends Thread {
    static  DataOutputStream out;
    static DataInputStream in;
    static Socket clientSocket;
    @Override
    public void run() {
        runServer();
    }

    TextArea ta;
    Label notifyLabel;
    public ServerChatThread(TextArea ta, Label notifyLabel) {
        this.ta = ta;
        this.notifyLabel = notifyLabel;
    }

    void runServer(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9988);
            System.out.println("Waiting to connect");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    notifyLabel.setText("Client is connected");
                }
            });

            out = new DataOutputStream( clientSocket.getOutputStream());
            in = new DataInputStream( clientSocket.getInputStream());

            while (true){
                msgContainer.append("Client : " +in.readUTF()+"\n");
                ta.setText(msgContainer.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void connectionStatus(){
        notifyLabel.setText("Connected");
    }
}
