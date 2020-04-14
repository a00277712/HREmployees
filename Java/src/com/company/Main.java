package com.company;

import io.CsvIO;
import io.MenuPrinter;
import model.Department;
import model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import helper.DepartmentHelper;
import helper.EmployeeHelper;

public class Main {
    // Keep length > 9
    private static final int Length = 50;
    // The list of departments
    private static ArrayList<Department> DepartmentList = new ArrayList();
    // The csv separator
    private static final String SEPARATOR = ",";
    // .csv file for departments
    private static final CsvIO DepartmentIO = new CsvIO(new File("data/Departments.csv"), SEPARATOR);
    // .csv file for Employees
    private static final CsvIO EmployeeIO = new CsvIO(new File("data/Employees.csv"), SEPARATOR);

    /**
     * Populates the Departments list from data/Departments.csv and data/Employees.csv
     * @param args
     */
    public static void main(String[] args) {
        getDepartments();
        getEmployees();

        if (Length < 9) {
            System.out.println("Length too short");
            return;
        }
        ManageDepartments();
        save();
    }

    /**
     * Gets Departments from DepartmentIO and adds them to the DepartmentList
     */
    private static void getDepartments() {
        for (ArrayList<String> value : DepartmentIO.getValues()) {
            DepartmentList.add(new Department(value));
        }
    }

    /**
     * Gets Employees from EmployeeIO and adds them to the Departments in DepartmentList
     */
    private static void getEmployees() {
        for (ArrayList<String> value : EmployeeIO.getValues()) {
            Employee tmpEmployee = new Employee(value);
            for (int i = 0; i < DepartmentList.size(); i++) {
                Department department = DepartmentList.get(i);
                if (department.Id == tmpEmployee.DepartmentId) {
                    department.addEmployee(tmpEmployee);
                    break;
                }
            }
        }
    }

    /**
     * Prints all departments
     */
    private static void ManageDepartments() {
        Scanner input = new Scanner(System.in);
        try {
            DepartmentHelper.printMenu(DepartmentList, Length);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        while (input.hasNextInt()) {
            int index = input.nextInt();

            if(index <= 0){
                return;
            }else
            if (DepartmentList.size() >= index) {
                // Enters 1 based index of item
                selectDepartment(DepartmentList.get(index - 1));
            } else if(DepartmentList.size() + 1 == index){
                // If entered next index add new department
                DepartmentHelper.addNewDepartment(DepartmentList);
            } else{
                // invalid index so take as 0
                return;
            }

            try {
                DepartmentHelper.printMenu(DepartmentList, Length);
            } catch (Exception e) {
                System.out.print(e.getMessage());
                return;
            }
        }
    }

    /**
     * Prints a selected department and options
     *
     * @param department The department to be operated on
     */
    private static void selectDepartment(Department department) {
        Scanner input = new Scanner(System.in);
        if (PrintDepartmentOptions(department)) {
            return;
        }

        while (input.hasNextInt()) {
            switch (input.nextInt()) {
                case 1:
                    ManageEmployees(department);
                    break;
                /*case 2:
                    Employee.addNewEmployee(department);
                    break;*/
                case 2:
                    DepartmentHelper.modify(department, Length);
                    break;
                case 3:
                    DepartmentList.remove(department);
                default:
                    return;
            }

            if (PrintDepartmentOptions(department)) {
                return;
            }
        }
    }

    private static boolean PrintDepartmentOptions(Department department) {
        try {
            DepartmentHelper.print(department, Length);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return true;
        }

        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Employees");
        menuItems.add("Edit");
        menuItems.add("Delete");

        try {
            MenuPrinter.print(menuItems, Length);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return true;
        }

        System.out.print("Enter option number:");
        return false;
    }

    /**
     * Displays all Employees of given Department
     * @param department The Department to show the employees for
     */
    private static void ManageEmployees(Department department) {
        Scanner input = new Scanner(System.in);

        try {
        	EmployeeHelper.printMenu(department.EmployeeList, Length);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return;
        }

        while (input.hasNextInt()) {
            int index = input.nextInt();

            if(index <= 0){
                return;
            }else
            if (department.EmployeeList.size() >= index) {
                // Enters 1 based index of item
                selectEmployee(department, department.EmployeeList.get(index - 1));
            } else if(department.EmployeeList.size() + 1 == index){
                // If entered next index add new department
                EmployeeHelper.AddNewEmployee(department);
            } else{
                // invalid index so take as 0
                return;
            }

            try {
            	EmployeeHelper.printMenu(department.EmployeeList, Length);
            } catch (Exception e) {
                System.out.print(e.getMessage());
                return;
            }
        }
    }

    /**
     * Prints a selected employee and options
     * @param employee The employee to be operated on
     */
    private static void selectEmployee(Department department, Employee employee) {
        Scanner input = new Scanner(System.in);
        if (PrintEmployeeOptions(employee)) {
            return;
        }

        while (input.hasNextInt()) {
            switch (input.nextInt()) {
                case 1:
                	EmployeeHelper.modify(employee, Length);
                    break;
                case 2:
                    department.EmployeeList.remove(employee);
                default:
                    return;
            }
            if (PrintEmployeeOptions(employee)) return;
        }
    }

    private static boolean PrintEmployeeOptions(Employee employee) {
    	EmployeeHelper.print(employee, Length);

        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Edit");
        menuItems.add("Delete");

        try {
            MenuPrinter.print(menuItems, Length);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return true;
        }

        System.out.print("Enter option number:");
        return false;
    }

    /**
     * Save the Departments List and their Employees to the DepartmentIO
     */
    private static void save() {
        DepartmentIO.write(Department.toArrayStrings(DepartmentList));
        saveEmployees();
    }

    /**
     * Save all Employees of Departments in DepartmentList to the EmployeeIO
     */
    private static void saveEmployees() {
        ArrayList<ArrayList<String>> employeeStrings = new ArrayList<>();

        for (Department department : DepartmentList) {
            employeeStrings.addAll(Employee.toArrayStrings(department.EmployeeList));
        }

        EmployeeIO.write(employeeStrings);
    }
}
