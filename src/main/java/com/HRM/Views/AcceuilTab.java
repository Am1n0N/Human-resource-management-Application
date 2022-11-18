package com.hrm.Views;

import com.hrm.Controllers.AccountController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.DAO.AccountDataAccessService;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.Models.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceuilTab implements Initializable {
    @FXML
    private Label NbStaff,NbPTO,NbEntries;

    @FXML
    private TextField SearchField;

    @FXML
    private Button SearchBtn;

    private EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SearchBtn.setOnAction(this::Search);
    }

    @FXML
    private void Search(ActionEvent actionEvent) {
        Employee employee = employeeController.SearchEmployee(SearchField.getText());
        if(employee != null){
            Dashboard.removeTab("Profile", Dashboard.corePane);
            ProfileTab.account = employeeController.getAccount(employee.getId());
            Dashboard.addTab("Profile","Employee.png",Dashboard.corePane);
        }
    }
}
