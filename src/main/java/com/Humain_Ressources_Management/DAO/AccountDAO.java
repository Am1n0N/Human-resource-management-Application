package com.Humain_Ressources_Management.DAO;

import com.Humain_Ressources_Management.Models.Account;
import com.Humain_Ressources_Management.Models.Employee;

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
}
