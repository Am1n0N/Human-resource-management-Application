package com.Humain_Ressources_Management.DAO;


import com.Humain_Ressources_Management.Models.Employee;

import java.util.List;

public interface EmployeeDAO extends DAO {
    int AddEmployee(String name, String Last_name, String Title, String Address, String Telephone, String DateNaissance, String Hiring_date, double Salary);
    Employee getEmployee(int id);
    List<Employee> getEmployees();
    int UpdateEmployee(int id, String name, String Last_name, String Title, String Address, String Telephone, String DateNaissance, String Hiring_date, double Salary);
    int DeleteEmployee(int id);
}
