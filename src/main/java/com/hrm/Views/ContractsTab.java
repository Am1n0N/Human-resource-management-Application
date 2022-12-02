package com.hrm.Views;

import com.hrm.Controllers.ContractController;
import com.hrm.Controllers.EmployeeController;
import com.hrm.DAO.ContractDataAccessService;
import com.hrm.DAO.EmployeeDataAccessService;
import com.hrm.Main;
import com.hrm.Models.Contract;
import com.hrm.Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContractsTab implements Initializable {
    private ObservableList<Employee> data;
    private final EmployeeController employeeController = new EmployeeController(new EmployeeDataAccessService());
    private final ContractController contractController = new ContractController(new ContractDataAccessService());

    private ArrayList<Employee> employees;
    @FXML
    private TableView<Employee> employeesTable;
    private File pdf;
    @FXML
    private TextField TFPtoNumber;
    @FXML
    private DatePicker TFSigningDate;
    @FXML
    private TableColumn<Employee, String> ColName, ColLastName, ColNIN, ColTitle, ColAddress, ColPhoneNumber;
    @FXML
    private Button Btn,SelectPdfBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        employeesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int id = newSelection.getId();
                Contract contract = contractController.GetContractByEmpId(id);
                System.out.println(contract.getSigndate()+" "+contract.getPtoLimit());
                //set values
                TFPtoNumber.setText(String.valueOf(contract.getPtoLimit()));
                TFSigningDate.setValue(LocalDate.parse(contract.getSigndate()));
                pdf = contract.getPdf();
                SelectPdfBtn.setOnAction(actionEvent1 -> {
                    FileChooser fileChooser = new FileChooser();
                    pdf = fileChooser.showOpenDialog(Main.getPrimaryStage());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Pdf Set successfully!");
                    alert.showAndWait();
                });
                Btn.setOnAction(actionEvent -> {
                    if(TFPtoNumber.getText().isEmpty() || TFSigningDate.getValue() == null || pdf == null){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill all fields!");
                        alert.showAndWait();}
                    else {
                        contractController.UpdateContract(new Contract(pdf, TFSigningDate.getValue().toString(), Integer.parseInt(TFPtoNumber.getText()), id));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Dialog");
                        alert.setHeaderText(null);
                        alert.setContentText("Contract Updated successfully!");
                        alert.showAndWait();
                    }
                });
                //clear fields
                TFPtoNumber.clear();
                TFSigningDate.setValue(null);
                pdf = null;
            }
    });
    }
}
