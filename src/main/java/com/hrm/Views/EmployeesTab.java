package com.hrm.Views;

import com.hrm.Controllers.EmployeeController;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.Main;
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

public class EmployeesTab implements Initializable {
    private ObservableList<Employee> data;
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    ArrayList<Employee> employees;
    @FXML
    TableView<Employee> employeesTable;
    @FXML
    TableColumn<Employee, String> ColName, ColLastName, ColNIN, ColTitle, ColAddress, ColPhoneNumber;

    @FXML
    TextField TFName, TFLastName, TFNIN, TFTitle, TFAddress, TFPhoneNumber;

    @FXML
    Button  ModifyBtn, DeleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            ModifyBtn.setDisable(true);
            DeleteBtn.setDisable(true);
            employees = employeeController.getEmployees();
            data = FXCollections.observableArrayList();
            ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
            ColLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
            ColNIN.setCellValueFactory(new PropertyValueFactory<>("NIN"));
            ColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            ColPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("telephone"));
            employees.forEach(employee -> data.add(employee));
            employeesTable.setItems(data);
            //get id from tablerow
            employeesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    int id = newSelection.getId();
                    //fill textfields with selected row data
                    TFName.setText(newSelection.getName());
                    TFLastName.setText(newSelection.getLast_name());
                    TFNIN.setText(newSelection.getNIN());
                    TFTitle.setText(newSelection.getTitle());
                    TFAddress.setText(newSelection.getAddress());
                    TFPhoneNumber.setText(newSelection.getTelephone());
                    ModifyBtn.setDisable(false);
                    DeleteBtn.setDisable(false);
                    ModifyBtn.setOnAction(e -> {
                        employeeController.UpdateEmployee(id, TFName.getText(), TFLastName.getText(), TFNIN.getText(), TFTitle.getText(), TFAddress.getText(), TFPhoneNumber.getText());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Employee Modified successfully!");
                        alert.showAndWait();
                    });
                    DeleteBtn.setOnAction(e -> {
                        employeeController.DeleteEmployee(id);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Employee Deleted successfully!");
                        alert.showAndWait();
                    });
                }
            });

    }
}