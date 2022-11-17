package com.hrm.Views;

import com.hrm.Controllers.AccountController;
import com.hrm.DAO.AccountDataAccessService;
import com.hrm.Models.Account;
import com.hrm.Models.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileTab implements Initializable {

    public static Account account;
    private AccountController accountController = new AccountController(new AccountDataAccessService());
    @FXML
    ImageView profileImage;

    @FXML
    Label Name,Last_name,NIN,Address,Telephone,Email,Birthday,Hiring_date;

    @FXML
    Button ChangePwdBtn,ChangePicBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee = accountController.getEmployee(account.getId_Emp());
        Name.setText(employee.getName());
        Last_name.setText(employee.getLast_name());
        NIN.setText(employee.getNIN());
        Address.setText(employee.getAddress());
        Telephone.setText(employee.getTelephone());
        Email.setText(account.getEmail());
        Birthday.setText(employee.getDateNaissance());
        Hiring_date.setText(employee.getHiring_date());

        if (account == Login.account){
            ChangePwdBtn.setVisible(true);
            ChangePicBtn.setVisible(true);
        }

    }
}

