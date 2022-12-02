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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PTORequestsTab implements Initializable {
    private ObservableList<Employee> data;
    private EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    private ContractController contractController = new ContractController(new ContractDataAccessService());
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

    private ObservableList<PTO> data2;

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
                PTO_Record PTORecord = ptoSController.getPTO_RecordByContractId(contractController.GetContractByEmpId(newSelection.getId()).getId(), "Pending");
                if(PTORecord!=null){
                    PTO.setText(PTORecord.getPtoAvailable() + "");
                    maxPTO.setText(contractController.GetContractByEmpId(newSelection.getId()).getPtoLimit() + "");
                    PTORecord.getPTOs().forEach(pto -> {
                        if (pto.getStatus().equals("Pending")) {
                            PtoReq.add(pto);
                        }
                    });
                }
                data2 = FXCollections.observableArrayList();
                start.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
                end.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
                Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                PtoReq.forEach(pto -> data2.add(pto));
                PTOTable.setItems(data2);
                PTOTable.getSelectionModel().selectedItemProperty().addListener((obs2, oldSelection2, newSelection2) -> {
                    if (newSelection2 != null) {
                        assert PTORecord != null;
                        if (PTORecord.getPtoAvailable()!=0) {
                            Accept.setDisable(false);
                        }
                        Reject.setDisable(false);
                        Accept.setOnAction(actionEvent -> {
                            int msg = ptoSController.ApprovePTO(PTORecord.getId(),newSelection2);
                            if (msg == 1) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText("PTO Request Approved");
                                alert.showAndWait();
                                Dashboard.removeTab("PTO Request",Dashboard.corePane);
                                Dashboard.addTab("PTO Request","Employee.png",Dashboard.corePane);
                            }else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("PTO Request Not Approved");
                                alert.showAndWait();
                            }});
                        Reject.setOnAction(actionEvent1 -> {
                            int msg = 0;
                            if(newSelection2.getStartDate().isBefore(newSelection2.getEndDate()) || newSelection2.getDays() < PTORecord.getPtoAvailable()){
                                msg = ptoSController.RejectPTO(PTORecord.getId(),newSelection2);
                            }
                            if (msg == 1) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Success");
                                alert.setHeaderText("PTO Request Rejected");
                                alert.showAndWait();
                                Dashboard.removeTab("PTO Request",Dashboard.corePane);
                                Dashboard.addTab("PTO Request","Employee.png",Dashboard.corePane);
                            }else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("PTO Request Not Rejected");
                                alert.showAndWait();
                            }});
                    }
                });
            }
        });
    }
}
