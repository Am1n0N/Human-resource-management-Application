package com.hrm.DAO;


import com.hrm.Models.Account;
import com.hrm.Models.Employee;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;

public interface AccountDAO extends DAO {
    int AddAccount(String Email, String password, String isAdmin, int id_Emp);
    int UpdateAccount(int id, String Email, String password, String isAdmin, int id_Emp);
    int DeleteAccount(int id);
    Account GetAccount(int id);
    Employee getEmployee(int id);

    int login(String Email, String password);

    int ChangePassword(int id, String password);

    int ChangeEmail(int id, String Email);

    int signup(Account account, Employee employee);

    void ChangeProfilePic(int id, File file);

    Image getProfilePic(int id);
}
