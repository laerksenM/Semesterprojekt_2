package org.example;

import javafx.fxml.FXML;
import java.io.IOException;

public class Startside {

    @FXML
    public void start() throws IOException {

        // START KNAP
        // her der skal vi have at vi starter for porten, vi skal have vores data ind og lave en dynamisk.
        //med det data der ruller ind
        // slut og gem skal fungere som
    }


    public void logud() throws IOException {
        App.setRoot("Login");
        //her kommer vi tilbage til vores startside.
    }

    public void slut() throws IOException {
        // her skal vi have at den slutter og gemmer
    }

    public void sog() throws IOException {
        // Her der skal vi søge på et CPR, som vi har gemt nede i i databasen.
    }
}

