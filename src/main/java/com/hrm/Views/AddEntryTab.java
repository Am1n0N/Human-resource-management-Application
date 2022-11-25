package com.hrm.Views;

import com.hrm.Controllers.EntryController;
import com.hrm.Controllers.RequestController;
import com.hrm.DAO.EntryDataAccessObject;
import com.hrm.DAO.RequestDAO;
import com.hrm.DAO.RequestDataAccessObject;
import com.hrm.Models.Entry;
import com.hrm.Models.Request;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEntryTab implements Initializable {
    private EntryController entryController = new EntryController(new EntryDataAccessObject());
    private RequestController requestController = new RequestController(new RequestDataAccessObject());
    @FXML
    TextField Title;
    @FXML
    TextArea Content;
    @FXML
    Button AddEntry;
    @FXML
    CheckBox IsPublic,AllowComment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            if (Login.account.getIsAdmin().equals("false")) {
                IsPublic.setVisible(false);
                AllowComment.setVisible(false);
                AddEntry.setOnAction(actionEvent -> {
                    Request request = new Request(Login.account.getId(), Title.getText(), Content.getText());
                    requestController.addRequest(request);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Entry Sent successfully!");
                    alert.showAndWait();
                    Title.clear();
                    Content.clear();
                });
            } else {
                AddEntry.setOnAction(event -> {
                    Entry entry = new Entry(Login.account.getId(), Title.getText(), Content.getText(), IsPublic.isSelected(), AllowComment.isSelected());
                    entryController.addEntry(entry);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Entry Added successfully!");
                    alert.showAndWait();
                });
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
