package com.hrm.Views;


import com.hrm.Controllers.AccountController;
import com.hrm.Controllers.Tools;
import com.hrm.Main;
import com.hrm.Models.Account;
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

import static com.hrm.Main.buildAccountController;

public class Login implements Initializable {

    public static boolean isLoggedIn = false;
    public static int IDeff = 0;
    public static Account account;
    private AccountController accountController = buildAccountController();



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
    @FXML
    private void Login(ActionEvent event)  {
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
                System.out.println("Logged in!");
                Stage stage = Main.getPrimaryStage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Dashboard.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                    stage.setResizable(false);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();
                } catch (Exception ex){
                    System.out.println("Error loading Dashboard");
                    System.out.println(ex.getMessage());
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
