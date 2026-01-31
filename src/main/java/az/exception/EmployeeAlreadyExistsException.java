package az.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException() {
        super("Employee already exists");
    }
}
