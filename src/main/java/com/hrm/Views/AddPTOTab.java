package com.hrm.Views;

import com.hrm.Controllers.ContractController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.Controllers.PTOSController;
import com.hrm.DAO.ContractDataAccessService;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.DAO.PTOSDataAccessService;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPTOTab implements Initializable {
    private ObservableList<Employee> data;
    EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    PTOSController ptoSController = new PTOSController(new PTOSDataAccessService());

    ContractController contractController = new ContractController(new ContractDataAccessService());
    ArrayList<Employee> employees;
    @FXML
    TableView<Employee> employeesTable;
    @FXML
    TableColumn<Employee, String> ColName, ColLastName, ColNIN, ColTitle, ColPhoneNumber;
    @FXML
    DatePicker startDate, endDate;
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

                PTO_Record PTORecord = ptoSController.getPTO_RecordByContractId(contractController.GetContractByEmpId(newSelection.getId()).getId());
                startDate.setDisable(false);
                endDate.setDisable(false);
                Content.setDisable(false);

                if (PTORecord.getPtoAvailable()!= 0) {
                    submit.setDisable(false);
                }
                submit.setOnAction(e -> {
                    System.out.println(startDate.getValue().toString());
                    PTO SPTO = new PTO();
                    SPTO.setStartDate(startDate.getValue());
                    SPTO.setEndDate(endDate.getValue());
                    SPTO.setDescription(Content.getText());
                    SPTO.setPtoId(PTORecord.getId());
                    SPTO.setStatus("Approved");
                    int msg=0;
                    if(SPTO.getStartDate().isBefore(SPTO.getEndDate()) || SPTO.getDays() < PTORecord.getPtoAvailable()){
                         msg = ptoSController.AddPTO(PTORecord,SPTO);
                    }

                    if (msg == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText("PTO Added Successfully");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("PTO Not Added");
                        alert.showAndWait();
                    }
                });
            }
        });

    }

}
