package com.hrm.Views;

import com.hrm.Controllers.EmployeeController;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPTOTab implements Initializable {
    private ObservableList<Employee> data;
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    ArrayList<Employee> employees;
    @FXML
    TableView<Employee> employeesTable;
    @FXML
    TableColumn<Employee, String> ColName, ColLastName, ColNIN, ColTitle, ColPhoneNumber;
    @FXML
    TextField startDate, endDate;
    @FXML
    TextArea Content;
    @FXML
    Button submit;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startDate.setDisable(true);
        endDate.setDisable(true);
        Content.setDisable(true);
        submit.setDisable(true);
        employees = employeeController.getEmployees();
        data = FXCollections.observableArrayList();
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        ColNIN.setCellValueFactory(new PropertyValueFactory<>("NIN"));
        ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        ColPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        employees.forEach(employee -> data.add(employee));
        employeesTable.setItems(data);
        employeesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                startDate.setDisable(false);
                endDate.setDisable(false);
                Content.setDisable(false);
                submit.setDisable(false);
                submit.setOnAction(e -> {
                    //employeeController.addPTO(newSelection.getId(), startDate.getText(), endDate.getText(), Content.getText());
                });
            }
        });

    }

}
