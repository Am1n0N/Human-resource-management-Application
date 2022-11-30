package com.hrm.Views;

import com.hrm.Controllers.EmployeeController;
import com.hrm.Controllers.EntryController;
import com.hrm.Controllers.RequestController;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.DAO.EntryDataAccessObject;
import com.hrm.DAO.RequestDataAccessObject;
import com.hrm.Models.Employee;
import com.hrm.Models.Entry;
import com.hrm.Models.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RequestsTab implements Initializable {
    private ObservableList<Employee> data;
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    ArrayList<Employee> employees;
    @FXML
    TableView<Employee> employeesTable;
    @FXML
    TableColumn<Employee, String> ColName, ColLastName, ColNIN, ColTitle, ColPhoneNumber;

    public ObservableList<Request> data2;
    private ArrayList<Request> entries;
    private RequestController requestController = new RequestController(new RequestDataAccessObject());
    private EntryController entryController = new EntryController(new EntryDataAccessObject());
    @FXML
    TableView<Request> EntriesTable;
    @FXML
    Button Accept, Reject;
    @FXML
    TableColumn<Request, String> Date, Title, Logger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Accept.setDisable(true);
            Reject.setDisable(true);
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
                    int id = newSelection.getId();
                    entries = requestController.getRequests(id);
                    data2 = FXCollections.observableArrayList();
                    Date.setCellValueFactory(new PropertyValueFactory<>("RequesterId"));
                    Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
                    Logger.setCellValueFactory(new PropertyValueFactory<>("Description"));
                    data2.addAll(entries);
                    EntriesTable.setItems(data2);
                    EntriesTable.getSelectionModel().selectedItemProperty().addListener((obs1, oldSelection1, newSelection1) -> {
                        if (newSelection1 != null) {
                            Accept.setDisable(false);
                            Reject.setDisable(false);
                            Reject.setOnAction(event -> {
                                requestController.deleteRequest(newSelection1);
                                Dashboard.removeTab("Requests", Dashboard.corePane);
                                Dashboard.addTab("Requests", "Employee.png", Dashboard.corePane);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("Request Rejected Successfully");
                                alert.showAndWait();
                            });
                            Accept.setOnAction(event -> {

                                //transform request to entry
                                Entry entry = new Entry();
                                entry.setTitle(newSelection1.getTitle());
                                entry.setDescription(newSelection1.getDescription());
                                entry.setEmployeeId(newSelection1.getRequesterId());
                                entry.setAllowComments(false);
                                entry.setPublic(true);
                                entryController.addEntry(entry);
                                requestController.deleteRequest(newSelection1);
                                Dashboard.removeTab("Requests", Dashboard.corePane);
                                Dashboard.addTab("Requests", "Employee.png", Dashboard.corePane);
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Dialog");
                                alert.setHeaderText(null);
                                alert.setContentText("Request Approved Successfully");
                                alert.showAndWait();
                            });
                        }
                    });
                }
            });
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}