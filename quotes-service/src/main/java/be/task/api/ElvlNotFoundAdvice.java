package be.task.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ayrat
 */
@ControllerAdvice
public class ElvlNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(ElvlNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(ElvlNotFoundException ex) {
    return ex.getMessage();
  }
}