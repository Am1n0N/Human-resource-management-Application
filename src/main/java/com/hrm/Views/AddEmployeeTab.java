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


    @FXML
    TextField TFName,TFLastName,TFNIN,TFAddress,TFBirthday,TFPhoneNumber,TFEmail,TFPassword,TFSigningDate,TFPtoNumber,TFTitle,TFHiringDate;

    @FXML
    Button SelectPicBtn,SelectPdfBtn,CreateEmployeeBtn;

    @FXML
    CheckBox IsAdmin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Check if Texfields Not Empty:
        if(!TFName.getText().isEmpty() && !TFLastName.getText().isEmpty() && !TFNIN.getText().isEmpty() && !TFAddress.getText().isEmpty() && !TFBirthday.getText().isEmpty() && !TFPhoneNumber.getText().isEmpty() && !TFEmail.getText().isEmpty() && !TFPassword.getText().isEmpty() && !TFSigningDate.getText().isEmpty() && !TFPtoNumber.getText().isEmpty() && !TFTitle.getText().isEmpty() && !TFHiringDate.getText().isEmpty()){
            CreateEmployeeBtn.setDisable(false);
        }
        else{
            CreateEmployeeBtn.setDisable(true);
        }

        if (IsAdmin.isSelected()){
            admin = "true";
        }
        else{
            admin = "false";
        }


        CreateEmployeeBtn.setOnAction(actionEvent -> {
            //Create Employee:
            id = employeeController.AddEmployee(TFName.getText(),TFLastName.getText(),TFNIN.getText(),TFTitle.getText(),TFAddress.getText(),TFPhoneNumber.getText(),TFBirthday.getText(),TFHiringDate.getText());
            SelectPicBtn.setOnAction(actionEvent1 -> {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
                if (file != null) {
                    GetPic(id,file);
                }});
            //Create Account:
            accountController.AddAccount(TFEmail.getText(),TFPassword.getText(),admin,id);
            //Create Contract:
            SelectPdfBtn.setOnAction(actionEvent1 -> {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
                if (file != null) {
                    Contract contract = new Contract(file,TFSigningDate.getText(),Integer.parseInt(TFPtoNumber.getText()),id);
                    contractController.AddContract(contract);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Profile picture Set successfully!");
                    alert.showAndWait();
                }});
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Employee Created successfully!");
            alert.showAndWait();
        });








    }



    private void GetPic(int id,File file) {
        accountController.ChangeProfilePic(id,file);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Profile picture Set successfully!");
        alert.showAndWait();
    }
}
