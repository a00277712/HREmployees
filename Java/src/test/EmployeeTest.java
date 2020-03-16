package test;

import model.Department;
import model.Employee;

public class EmployeeTest {
    public static void main(String[] args) throws Exception {
        Department testDepartment = new Department(1, "Test Name", "Test Location");
        Employee testEmployee = new Employee(1, "Test", "Employee", "E@m.il", "012345", "5.00", 1);
        testDepartment.addEmployee(testEmployee);

        //testEmployee.modifyEntry(50);
        //testEmployee.print(50);
        //System.out.print(testEmployee.toString());

        //TestAddEmployeeToFromInput(testDepartment);


        Employee.printMenu(testDepartment.EmployeeList, 50);
    }

    private static void TestAddEmployeeToFromInput(Department testDepartment) {
        Employee.AddNewEmployee(testDepartment);
        System.out.print(testDepartment.EmployeeList.get(0));
    }
}
