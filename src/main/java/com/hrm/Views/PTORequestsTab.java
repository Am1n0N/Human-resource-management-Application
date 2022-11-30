package com.hrm.Views;

import com.hrm.Controllers.*;
import com.hrm.DAO.*;
import com.hrm.Models.Employee;
import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PTORequestsTab implements Initializable {
    private ObservableList<Employee> data;
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    ContractController contractController = new ContractController(new ContractDataAccessService());
    private PTOSController ptoSController = new PTOSController(new PTOSDataAccessService());
    ArrayList<Employee> employees;
    @FXML
    TableView<Employee> employeesTable;
    @FXML
    TableColumn<Employee, String> ColName, ColLastName, ColNIN, ColTitle, ColPhoneNumber;
    @FXML
    TableColumn<PTO, String> start,end,Description;
    @FXML
    Label PTO,maxPTO;

    public ObservableList<PTO> data2;

    @FXML
    TableView<PTO> PTOTable;
    @FXML
    Button Accept, Reject;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                PTOTable.getItems().clear();
                ArrayList<PTO> PtoReq = new ArrayList<>();
                PTO_Record PTORecord = ptoSController.getPTOSByContractId(contractController.GetContractByEmpId(newSelection.getId()).getId());
                PTO.setText(PTORecord.getPtoAvailable() + "");
                maxPTO.setText(contractController.GetContractByEmpId(newSelection.getId()).getPtoLimit() + "");
                PTORecord.getPTOs().forEach(pto -> {
                    if (pto.getStatus().equals("Pending")) {
                        System.out.println(pto.getStatus());
                        PtoReq.add(pto);
                    }
                });
                data2 = FXCollections.observableArrayList();
                start.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
                end.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
                Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                PtoReq.forEach(pto -> data2.add(pto));
                PTOTable.setItems(data2);
                PTOTable.getSelectionModel().selectedItemProperty().addListener((obs2, oldSelection2, newSelection2) -> {
                    if (newSelection2 != null) {
                        Accept.setDisable(false);
                        Reject.setDisable(false);


                    }
                });
            }
        });
    }
}
