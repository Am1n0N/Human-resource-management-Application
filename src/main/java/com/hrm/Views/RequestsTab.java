package com.hrm.Views;

import com.hrm.Controllers.EntryController;
import com.hrm.Controllers.RequestController;
import com.hrm.DAO.EntryDataAccessObject;
import com.hrm.DAO.RequestDataAccessObject;
import com.hrm.Models.Entry;
import com.hrm.Models.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RequestsTab implements Initializable {
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
            entries = requestController.getRequests();
            data2 = FXCollections.observableArrayList();
            Date.setCellValueFactory(new PropertyValueFactory<>("RequesterId"));
            Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
            Logger.setCellValueFactory(new PropertyValueFactory<>("Description"));
            data2.addAll(entries);
            EntriesTable.setItems(data2);
            EntriesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    int id = newSelection.getId();
                    Accept.setDisable(false);
                    Reject.setDisable(false);
                    Reject.setOnAction(event -> {
                        requestController.deleteRequest(newSelection);
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
                        entry.setTitle(newSelection.getTitle());
                        entry.setDescription(newSelection.getDescription());
                        entry.setEmployeeId(newSelection.getRequesterId());
                        entry.setAllowComments(false);
                        entry.setPublic(true);
                        entryController.addEntry(entry);
                        requestController.deleteRequest(newSelection);
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
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}

