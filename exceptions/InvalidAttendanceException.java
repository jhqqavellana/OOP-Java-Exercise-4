package exceptions;

/**
 * Exception thrown when an attendance operation violates the attendance rules.
 */
public class InvalidAttendanceException extends Exception {
  public InvalidAttendanceException(String message) {
    super(message);
  }

}
