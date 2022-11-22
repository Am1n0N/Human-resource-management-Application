package com.hrm.Views;


import com.hrm.Controllers.AccountController;
import com.hrm.Main;
import com.hrm.Models.Account;
import com.hrm.Models.Employee;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;


import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.hrm.Main.buildAccountController;

public class ProfileTab implements Initializable {
    private AccountController accountController = buildAccountController();
    public static Account account;
    @FXML
    ImageView profileImage;

    @FXML
    Label Name,Last_name,NIN,Address,Telephone,Email,Birthday,Hiring_date;

    @FXML
    Button ChangePwdBtn,ChangePicBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChangePwdBtn.setVisible(false);
        ChangePicBtn.setVisible(false);
        Employee employee = accountController.getEmployee(account.getId_Emp());
        Name.setText(employee.getName());
        Last_name.setText(employee.getLast_name());
        NIN.setText(employee.getNIN());
        Address.setText(employee.getAddress());
        Telephone.setText(employee.getTelephone());
        Email.setText(account.getEmail());
        Birthday.setText(employee.getDateNaissance());
        Hiring_date.setText(employee.getHiring_date());

        profileImage.setImage(accountController.getProfilePic(account.getId_Emp()));


        //popup to change password
        ChangePwdBtn.setOnAction(event -> {
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Change Password");
            dialog.setHeaderText("Change your password");
            //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
            ButtonType loginButtonType = new ButtonType("Change", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            PasswordField NewPassword = new PasswordField();
            NewPassword.setPromptText("New Password");
            PasswordField ConfirmPassword = new PasswordField();
            ConfirmPassword.setPromptText("Confirm Password");

            grid.add(new Label("New Password:"), 0, 0);
            grid.add(NewPassword, 1, 0);
            grid.add(new Label("Confirm Password:"), 0, 1);
            grid.add(ConfirmPassword, 1, 1);

            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            NewPassword.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

            Platform.runLater(() -> NewPassword.requestFocus());

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(NewPassword.getText(), ConfirmPassword.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result = dialog.showAndWait();

            result.ifPresent(usernamePassword -> {
                if (usernamePassword.getKey().equals(usernamePassword.getValue())) {
                    accountController.ChangePassword(account.getId(), usernamePassword.getKey());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Password changed successfully!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("Passwords don't match!");
                    alert.showAndWait();
                }
            });


        });
        FileChooser fileChooser = new FileChooser();
        //popup to change profile picture
        ChangePicBtn.setOnAction(event -> {
                    System.out.println("Open button clicked");
                    File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
                    if (file != null) {
                        Changepic(file);
                    }
        });




        if (account == Login.account){
            ChangePwdBtn.setVisible(true);
            ChangePicBtn.setVisible(true);
        }

    }
    private void Changepic(File file) {
        accountController.ChangeProfilePic(account.getId(),file);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Profile picture changed successfully!");
        alert.showAndWait();
    }
}

