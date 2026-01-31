package az.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer employeeId) {
        super("Employee with id " + employeeId + " not found");
    }
}
