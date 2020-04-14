package test;

import model.Department;
import model.Employee;

import java.util.ArrayList;

import helper.DepartmentHelper;

public class DepartmentTest {
    public static void main(String[] args) throws Exception {
        Department testDepartment = new Department(1, "Test Name", "Test Location");
        Employee tmpEmployee = new Employee(1, "Test", "Employee", "E@m.il", "012345", "5.00", 1);

        testDepartment.addEmployee(tmpEmployee);

        Department testDepartment2 = new Department(1, "Test Name", "Test Location");

        ArrayList<Department> departmentList = new ArrayList<>();

        departmentList.add(testDepartment);
        departmentList.add(testDepartment2);

        //DepartmentHelper.modifyEntry(testDepartment, 50);
        //DepartmentHelper.print(testDepartment, 50);

        DepartmentHelper.printMenu(departmentList, 50);
        System.out.println();
        System.out.println(testDepartment.toString());
    }
}
