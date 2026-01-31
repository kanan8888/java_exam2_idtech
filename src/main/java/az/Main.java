package az;

import az.exception.EmployeeAlreadyExistsException;
import az.exception.EmployeeDoesNotExistException;
import az.exception.EmployeeNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var sc = new Scanner(System.in);
        int choice;


        var service = new EmployeeManagementService();


        do {
            printMenu();
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("enter employee id");
                    Integer id = sc.nextInt();

                    System.out.println("enter employee name");
                    String name = sc.next();

                    System.out.println("enter employee department");
                    String department = sc.next();

                    System.out.println("enter employee salary");
                    Double salary = sc.nextDouble();

                    try {
                        service.addEmployee(new Employee(id, name, department, salary));
                        System.out.println("added employee successfully");
                    }catch (EmployeeAlreadyExistsException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("enter employee id");
                    Integer id = sc.nextInt();

                    try {
                        service.removeEmployeeById(id);
                    }catch (EmployeeDoesNotExistException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                case 3 -> {
                    service.showAllEmployees();
                }
                case 4 -> {
                    service.sortEmployees();
                }
                case 5 -> {
                    System.out.println("enter employee id");
                    Integer id = sc.nextInt();

                    try {
                         service.searchEmployeeById(id);
                    }catch (EmployeeNotFoundException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                case 6 -> {
                    service.salaryAnalytics();
                }
                case 7 -> System.out.println("bye :)");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }

    public static void printMenu(){
        System.out.println("""
                
                ------------------------
                1. Add employee
                2. Remove employee
                3. Show all employees
                4. Sort employees
                5. Search employee
                6. Salary analytics
                7. Exit
                ------------------------
                
                """);
    }
}
