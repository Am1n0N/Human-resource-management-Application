package com.hrm.DAO;




import com.hrm.Models.Account;
import com.hrm.Models.Employee;

import java.util.List;

public interface EmployeeDAO extends DAO {
    int AddEmployee(String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  Double salary, String hiring_date);
    Employee getEmployee(int id);
    List<Employee> getEmployees();
    int UpdateEmployee(int id, String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  Double salary, String hiring_date);
    int DeleteEmployee(int id);
    Account getAccount(int id);
    Employee SearchEmployee(String name);
}
