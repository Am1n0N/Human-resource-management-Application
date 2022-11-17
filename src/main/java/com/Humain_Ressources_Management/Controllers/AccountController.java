package com.Humain_Ressources_Management.Controllers;

import com.Humain_Ressources_Management.DAO.AccountDAO;
import com.Humain_Ressources_Management.Models.Account;
import com.Humain_Ressources_Management.Models.Employee;
import com.Humain_Ressources_Management.Views.Login;

public class AccountController {
    private final AccountDAO accountDAO;

    public AccountController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean login(String Email, String password) {
        if (accountDAO.login(Email, password) != 0) {
            Login.account = accountDAO.GetAccount(accountDAO.login(Email, password));
            return true;
        }
        return false;
    }

    public int ChangePassword(int id, String password) {
        return accountDAO.ChangePassword(id, password);
    }

    public int ChangeEmail(int id, String Email) {
        return accountDAO.ChangeEmail(id, Email);
    }

    public int signup(Account account, Employee employee) {
        return accountDAO.signup(account, employee);
    }

    public int AddAccount(String Email, String password, String isAdmin, int id_Emp) {
        return accountDAO.AddAccount(Email, password, isAdmin, id_Emp);
    }

    public int UpdateAccount(int id, String Email, String password, String isAdmin, int id_Emp) {
        return accountDAO.UpdateAccount(id, Email, password, isAdmin, id_Emp);
    }

    public int DeleteAccount(int id) {
        return accountDAO.DeleteAccount(id);
    }

    public Account GetAccount(int id) {
        return accountDAO.GetAccount(id);
    }

    public Employee getEmployee(int id) {
        return accountDAO.getEmployee(id);
    }


}
