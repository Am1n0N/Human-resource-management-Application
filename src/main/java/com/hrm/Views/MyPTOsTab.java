package com.hrm.Views;

import com.hrm.Controllers.ContractController;
import com.hrm.Controllers.PTOSController;
import com.hrm.DAO.ContractDataAccessService;
import com.hrm.DAO.PTOSDataAccessService;
import com.hrm.Models.PTO;
import com.hrm.Models.PTO_Record;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MyPTOsTab implements Initializable {
    private final ContractController contractController = new ContractController(new ContractDataAccessService());
    private final PTOSController ptoSController = new PTOSController(new PTOSDataAccessService());
    private ObservableList<PTO> data2;
    @FXML
    TableView<PTO> PTOTable;
    @FXML
    TableColumn<PTO, String> start,end,Description;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
        ArrayList<PTO> PtoReq;
        PTO_Record PTORecord = ptoSController.getPTO_RecordByContractId(contractController.GetContractByEmpId(Login.account.getId_Emp()).getId(), "Approved");
        data2 = FXCollections.observableArrayList();
        start.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        end.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
        Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        PtoReq = PTORecord.getPTOs();
        PtoReq.forEach(pto -> data2.add(pto));
        PTOTable.setItems(data2);
        } catch (RuntimeException e) {
            System.out.println(e);
        }
    }
}

