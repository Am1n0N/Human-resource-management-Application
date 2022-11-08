package com.App.gestion_ressources_humaines.Views;

import com.App.gestion_ressources_humaines.Models.Compte;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static com.App.gestion_ressources_humaines.Controllers.Outils.decrypter;

public class LoginPage extends Application {
    @FXML private TextField EmailText,EmailPassword;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void login(ActionEvent event){
        Parent root;
        Compte compte = new Compte(EmailText.getText());
        if(EmailPassword.getText().equals(compte.getPassword())){
            try {
                root = FXMLLoader.load(MainPage.class.getResource("MainPage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("HRM");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Login Failed");
        }
    }
}
