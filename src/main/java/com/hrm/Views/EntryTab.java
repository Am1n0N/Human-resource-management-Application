package com.hrm.Views;

import com.hrm.Controllers.EntryController;
import com.hrm.DAO.EntryDataAccessObject;
import com.hrm.Models.Entry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EntryTab implements Initializable {
    public static Entry entry;
    private EntryController entryController = new EntryController(new EntryDataAccessObject());
    @FXML
    Label LDate, LTitle, LDescription;

    @FXML
    Button  DeleteBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
        LDate.setText(entry.getDate());
        LTitle.setText(entry.getTitle());
        LDescription.setText(entry.getDescription());
        if(Login.account.getIsAdmin().equals("false")){
            DeleteBtn.setVisible(false);
        }
        DeleteBtn.setOnAction(this::Delete);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }}

    private void Delete(ActionEvent actionEvent) {
        entryController.deleteEntry(entry);
    }

}
