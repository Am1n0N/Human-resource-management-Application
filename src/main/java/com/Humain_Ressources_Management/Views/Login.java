package com.Humain_Ressources_Management.Views;

import com.Humain_Ressources_Management.Controllers.AccountController;
import com.Humain_Ressources_Management.Controllers.Tools;
import com.Humain_Ressources_Management.DAO.AccountDataAccessService;
import com.Humain_Ressources_Management.Main;
import com.Humain_Ressources_Management.Models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    public static boolean isLoggedIn = false;
    public static int IDeff = 0;
    public static Account account;
    private AccountController accountController = new AccountController(new AccountDataAccessService());


    @FXML
    Button loginButton;

    @FXML
    TextField email;

    @FXML
    TextField pas;

    @FXML
    Label errorLabel;

    private void setError(String x){
        errorLabel.setText(x);
        errorLabel.setVisible(true);
    }


    private void Login(ActionEvent event){
        if(email.getText().trim().isEmpty()){
            setError("Email can't be empty!");
        }
        else if(pas.getText().trim().isEmpty()){
            setError("Password can't be empty!");
        }
        else {
            String parsedusername = email.getText();
            String parsedpass = pas.getText();


            if(accountController.login(parsedusername, parsedpass)){
                Stage stage = Main.getPrimaryStage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Dashboard.fxml"));
                try{
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                    stage.setResizable(false);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                }
                catch (Exception ex){
                    Tools.buildError("Unsuccessful",ex.getMessage(),"303");
                }
            } else {
                setError("Email or password is incorrect!");
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginButton.setOnAction(this::Login);
        errorLabel.setVisible(false);

    }

}
