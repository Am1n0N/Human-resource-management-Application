package com.hrm.DAO;




import com.hrm.Models.Account;
import com.hrm.Models.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends DAO {
    public int AddEmployee(String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance, String hiring_date, File image);
    public Employee getEmployee(int id);
    public ArrayList<Employee> getEmployees();
    public int UpdateEmployee(int id, String name, String last_name, String NIN, String title, String address, String telephone);
    public int DeleteEmployee(int id);
    public Account getAccount(int id);
    public Employee SearchEmployee(String name);
    public int getEmployeesCount();
}
