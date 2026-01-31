package az;

import az.exception.EmployeeAlreadyExistsException;
import az.exception.EmployeeDoesNotExistException;
import az.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class EmployeeManagementService {

    ArrayList<Employee> employeesArray = new ArrayList<>();
    HashMap<Integer, Employee> employeesMap = new HashMap<>();

    public void addEmployee(Employee employee) {
        if (employeesMap.containsKey(employee.getId())) throw new EmployeeAlreadyExistsException();

        employeesMap.put(employee.getId(), employee);
        employeesArray.add(employee);
    }

    public void removeEmployeeById(Integer employeeId) {
        if (!employeesMap.containsKey(employeeId)) throw new EmployeeDoesNotExistException();

        var removedEmployee = employeesMap.remove(employeeId);
        employeesArray.remove(removedEmployee);
    }

    public void showAllEmployees() {
        employeesArray.forEach(employee -> System.out.println(employee));
    }

    public void sortEmployees() {
        Comparator<Employee> nameComparator = Comparator.comparing(Employee::getName);
        Comparator<Employee> salaryComparator = Comparator.comparing(Employee::getSalary).reversed();
        Comparator<Employee> departmentComparator = Comparator.comparing(Employee::getDepartment);
        Comparator<Employee> departmentThenNameComparator = departmentComparator.thenComparing(nameComparator);

        employeesArray.sort(nameComparator);
        System.out.println("by name (asc): " + employeesArray);

        employeesArray.sort(salaryComparator);
        System.out.println("by salary (desc): " + employeesArray);

        employeesArray.sort(departmentThenNameComparator);
        System.out.println("by department, then by name (asc): " + employeesArray);
    }

    public void searchEmployeeById(Integer employeeId) {
        var employee = employeesMap.get(employeeId);
        if (employee == null) throw new EmployeeNotFoundException(employeeId);
        System.out.println(employee);
    }

    public void searchEmployeeByName(String employeeName) {
        List<Employee> employee = employeesArray.stream()
                .filter(e -> e.getName().toLowerCase().contains(employeeName.toLowerCase()))
                .toList();
        if (employee.isEmpty()) {
            System.out.println("No employees found with name containing: " + employeeName);
        } else {
            System.out.println(employee);
        }
    }

    public void salaryAnalytics() {
        double averageSalary = employeesArray.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        double highestSalary = employeesArray.stream()
                .mapToDouble(Employee::getSalary)
                .max()
                .orElse(0.0);

        //List<Double> top3Salaries =

        System.out.println("average salary: " + averageSalary
                + " highest salary: " + highestSalary
                + " top 3 salaries: " /*+ top3Salaries*/
        );
    }
}