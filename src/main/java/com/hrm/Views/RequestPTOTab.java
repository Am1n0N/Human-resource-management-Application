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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestPTOTab implements Initializable {
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    private ContractController contractController = new ContractController(new ContractDataAccessService());
    private PTOSController ptoSController = new PTOSController(new PTOSDataAccessService());
    @FXML
    TextField startDate, endDate;
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
            SPTO.setStartDate(startDate.getText());
            SPTO.setEndDate(endDate.getText());
            SPTO.setDescription(Content.getText());
            SPTO.setPtoId(PTORecord.getId());
            SPTO.setStatus("Pending");
            ptoSController.AddPTO(SPTO);
        });
    }
}
