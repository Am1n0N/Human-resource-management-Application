package com.hrm.Views;

import com.hrm.Controllers.AccountController;
import com.hrm.Controllers.ContractController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.Controllers.PTOSController;
import com.hrm.DAO.PTOSDataAccessService;
import com.hrm.Main;
import com.hrm.Models.Contract;
import com.hrm.Models.PTO_Record;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddEmployeeTab implements Initializable {

    private EmployeeController employeeController = Main.buildEmployeeController();
    private AccountController accountController = Main.buildAccountController();
    private ContractController contractController = Main.buildContractController();

    private PTOSController ptoSController = new PTOSController(new PTOSDataAccessService());
    private int id;
    String admin ;

    private File file,image;

    @FXML
    TextField TFName,TFLastName,TFNIN,TFAddress,TFPhoneNumber,TFEmail,TFPassword,TFPtoNumber,TFTitle;

    @FXML
    DatePicker TFHiringDate,TFSigningDate,TFBirthday;

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
                id = employeeController.AddEmployee(TFName.getText(),TFLastName.getText(),TFNIN.getText(),TFTitle.getText(),TFAddress.getText(),TFPhoneNumber.getText(),Date.valueOf(TFBirthday.getValue()).toString(),Date.valueOf(TFHiringDate.getValue()).toString(),image);
            }
            //Create Account:
            accountController.AddAccount(TFEmail.getText(),TFPassword.getText(),admin,id);
            //Create Contract:
            if (file != null) {
                Contract contract = new Contract(file, Date.valueOf(TFSigningDate.getValue()).toString(), Integer.parseInt(TFPtoNumber.getText()), id);
                int Cid = contractController.AddContract(contract);
                PTO_Record pto_record = new PTO_Record();
                pto_record.setContractId(Cid);
                pto_record.setPtoAvailable(Integer.parseInt(TFPtoNumber.getText()));
                pto_record.setPtoUsed(0);
                ptoSController.AddPTO_Record(pto_record);
            }
            if (id != -1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Employee Created successfully!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Employee already exists!");
                alert.showAndWait();
            }
            //Clear Fields:
            TFName.clear();
            TFLastName.clear();
            TFNIN.clear();
            TFAddress.clear();
            TFPhoneNumber.clear();
            TFEmail.clear();
            TFPassword.clear();
            TFPtoNumber.clear();
            TFTitle.clear();

        });

    }

}
