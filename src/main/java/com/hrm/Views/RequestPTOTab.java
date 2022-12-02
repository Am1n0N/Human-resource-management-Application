package com.hrm.Views;

import com.hrm.Controllers.ContractController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.Controllers.PTOSController;
import com.hrm.DAO.ContractDataAccessService;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.DAO.PTOSDataAccessService;
import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.DAYS;

public class RequestPTOTab implements Initializable {
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    private ContractController contractController = new ContractController(new ContractDataAccessService());
    private PTOSController ptoSController = new PTOSController(new PTOSDataAccessService());
    @FXML
    DatePicker startDate, endDate;
    @FXML
    TextArea Content;
    @FXML
    Button submit;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PTO_Record PTORecord = ptoSController.getPTO_RecordByContractId(contractController.GetContractByEmpId(employeeController.getEmployee(Login.account.getId_Emp()).getId()).getId());

        if (PTORecord.getPtoAvailable()!= 0) {
            submit.setDisable(false);
        }
        submit.setOnAction(e -> {
            PTO SPTO = new PTO();
            SPTO.setStartDate(startDate.getValue());
            SPTO.setEndDate(endDate.getValue());
            SPTO.setDescription(Content.getText());
            SPTO.setPtoId(PTORecord.getId());
            SPTO.setStatus("Pending");
            int msg=0;
            if(SPTO.getStartDate().isBefore(SPTO.getEndDate()) || SPTO.getDays() < PTORecord.getPtoAvailable()) {
                msg =ptoSController.AddPTO(PTORecord, SPTO);
            }
            if (msg == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("PTO Request Sent Successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error in Sending PTO Request");
                alert.showAndWait();
            }


        });
    }
}
