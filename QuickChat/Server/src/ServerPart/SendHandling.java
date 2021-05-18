package ServerPart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import static ServerPart.ServerChatThread.out;
import static ServerPart.Controllerchat.msgContainer;

public class SendHandling extends Thread {
    TextField msgField;
    TextArea ta;
    public SendHandling(TextField tf, TextArea ta) {
        this.msgField = tf;
        this.ta = ta;
    }

    @Override
    public void run() {
        try {
            out.writeUTF(msgField.getText());
            msgContainer.append("Server: "+msgField.getText()+"\n");
            ta.setText(String.valueOf(msgContainer));
            msgField.setText(" ");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
