package com.hrm.Controllers;



import com.hrm.DAO.EmployeeDAO;
import com.hrm.Models.Account;
import com.hrm.Models.Employee;

import java.util.List;

public class EmployeeController {
    private final EmployeeDAO employeeDAO;


    public EmployeeController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
        try {
            employeeDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddEmployee(String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  Double salary, String hiring_date) {
        employeeDAO.AddEmployee(name, last_name, NIN, title, address, telephone, dateNaissance, salary, hiring_date);
    }

    public void UpdateEmployee(int id, String name, String last_name, String NIN, String title, String address, String telephone, String dateNaissance,  Double salary, String hiring_date) {
        employeeDAO.UpdateEmployee(id, name, last_name, NIN, title, address, telephone, dateNaissance, salary, hiring_date);
    }

    public void DeleteEmployee(int id) {
        employeeDAO.DeleteEmployee(id);
    }

    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    public List<Employee> getEmployees() {
        return employeeDAO.getEmployees();
    }

    public Employee SearchEmployee(String name) {
        return employeeDAO.SearchEmployee(name);
    }

    public Account getAccount(int id) {
        return employeeDAO.getAccount(id);
    }

    public void close() throws Exception {
        employeeDAO.close();
    }

}