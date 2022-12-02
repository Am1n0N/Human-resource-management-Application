package com.hrm.Views;


import com.hrm.Controllers.EmployeeController;
import com.hrm.Controllers.PTOSController;
import com.hrm.Controllers.RequestController;
import com.hrm.DAO.PTOSDataAccessService;
import com.hrm.DAO.RequestDataAccessObject;
import com.hrm.Models.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.hrm.Main.buildEmployeeController;

public class AcceuilTab implements Initializable {

    private final EmployeeController employeeController = buildEmployeeController();
    private final PTOSController ptosController = new PTOSController(new PTOSDataAccessService());
    private final RequestController requestController = new RequestController(new RequestDataAccessObject());

    @FXML
    private Label NbStaff,NbPTO,NbEntries;

    @FXML
    private TextField SearchField;

    @FXML
    private Button SearchBtn;

    @FXML
    Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SearchBtn.setOnAction(this::Search);
    }

    @FXML
    private void Search(ActionEvent actionEvent) {
        if (!Objects.equals(Login.account.getIsAdmin(), "true")){
            NbEntries.setVisible(false);
            NbPTO.setVisible(false);
            NbStaff.setVisible(false);
            pane.setVisible(false);
        }
        NbStaff.setText(String.valueOf(employeeController.getEmployeesCount()));
        NbPTO.setText(String.valueOf(ptosController.getPendingPTOsCount()));
        NbEntries.setText(String.valueOf(requestController.getRequestsCount()));
        Employee employee = employeeController.SearchEmployee(SearchField.getText());
        if(employee != null){
            Dashboard.removeTab("Profile", Dashboard.corePane);
            ProfileTab.account = employeeController.getAccount(employee.getId());
            Dashboard.addTab("Profile","Employee.png",Dashboard.corePane);
        }
    }
}
