package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    App app = new App();

    @FXML
    TextField Brugernavn;
    @FXML
    PasswordField Kodeord;

    @FXML
    private void LoginKnap() throws IOException {
        App.setRoot("secondary");

        System.out.println(Brugernavn.getText());
        System.out.println(Kodeord.getText());



    }
}
