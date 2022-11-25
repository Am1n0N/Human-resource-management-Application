package com.hrm.Views;

import com.hrm.Controllers.EntryController;
import com.hrm.DAO.EntryDataAccessObject;
import com.hrm.Models.Employee;
import com.hrm.Models.Entry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EntriesTab implements Initializable {
    public ObservableList<Entry> data2;
    private ArrayList<Entry> entries;
    private EntryController entryController = new EntryController(new EntryDataAccessObject());
    @FXML
    TableView<Entry> EntriesTable;
    @FXML
    TableColumn<Entry, String> Date, Title, Description;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            entries = entryController.getEntries();
            data2 = FXCollections.observableArrayList();
            Date.setCellValueFactory(new PropertyValueFactory<>("date"));
            Title.setCellValueFactory(new PropertyValueFactory<>("title"));
            Description.setCellValueFactory(new PropertyValueFactory<>("description"));
            data2.addAll(entries);
            EntriesTable.setItems(data2);
            EntriesTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    int id = newSelection.getId();
                    Dashboard.removeTab("Entry", Dashboard.corePane);
                    EntryTab.entry = entryController.getEntry(id);
                    Dashboard.addTab("Entry", "Employee.png", Dashboard.corePane);
                }
            });
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
