package com.Humain_Ressources_Management.Controllers;

import com.Humain_Ressources_Management.DAO.EmployeeDAO;
import com.Humain_Ressources_Management.Models.Employee;

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

    public void AddEmployee(String name, String Last_name, String Title, String address, String Telephone, String DateNaissance, Double salary, String Hiring_date) {
        employeeDAO.AddEmployee(name, Last_name, Title, address, Telephone, DateNaissance, Hiring_date, salary);
    }

    public void UpdateEmployee(int id, String name, String Last_name, String Title, String address, String Telephone, String DateNaissance, Double salary, String Hiring_date) {
        employeeDAO.UpdateEmployee(id, name, Last_name, Title, address, Telephone, DateNaissance, Hiring_date, salary);
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

    public void close() throws Exception {
        employeeDAO.close();
    }

}
