package com.hrm.Views;

import com.hrm.Controllers.AccountController;
import com.hrm.Controllers.ContractController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.Main;
import com.hrm.Models.Contract;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeTab implements Initializable {

    private EmployeeController employeeController = Main.buildEmployeeController();
    private AccountController accountController = Main.buildAccountController();
    private ContractController contractController = Main.buildContractController();
    private int id;
    String admin ;

    private File file,image;

    @FXML
    TextField TFName,TFLastName,TFNIN,TFAddress,TFBirthday,TFPhoneNumber,TFEmail,TFPassword,TFSigningDate,TFPtoNumber,TFTitle,TFHiringDate;

    @FXML
    Button SelectPicBtn,SelectPdfBtn,CreateEmployeeBtn;

    @FXML
    CheckBox IsAdmin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (IsAdmin.isSelected()){
            admin = "true";
        }
        else{
            admin = "false";
        }
        SelectPicBtn.setOnAction(actionEvent1 -> {
            FileChooser fileChooser = new FileChooser();
            image = fileChooser.showOpenDialog(Main.getPrimaryStage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Profile picture Set successfully!");
            alert.showAndWait();
        });
        SelectPdfBtn.setOnAction(actionEvent1 -> {
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(Main.getPrimaryStage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Pdf Set successfully!");
            alert.showAndWait();
         });
        CreateEmployeeBtn.setOnAction(actionEvent -> {
            //Create Employee:
            if (image != null) {
                employeeController.AddEmployee(TFName.getText(),TFLastName.getText(),TFNIN.getText(),TFTitle.getText(),TFAddress.getText(),TFPhoneNumber.getText(),TFBirthday.getText(),TFHiringDate.getText(),image);
            }
            //Create Account:
            accountController.AddAccount(TFEmail.getText(),TFPassword.getText(),admin,id);
            //Create Contract:
            if (file != null) {
                Contract contract = new Contract(file, TFSigningDate.getText(), Integer.parseInt(TFPtoNumber.getText()), id);
                contractController.AddContract(contract);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Employee Created successfully!");
            alert.showAndWait();
        });

    }

}
