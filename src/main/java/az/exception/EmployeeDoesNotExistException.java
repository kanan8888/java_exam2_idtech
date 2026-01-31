package az.exception;

public class EmployeeDoesNotExistException extends RuntimeException {
    public EmployeeDoesNotExistException() {
        super("Employee does not exist");
    }
}
