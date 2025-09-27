package br.com.pep.vet.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.pep.vet.exception.CpfException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> ExceptionHandler(Exception exception){
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Ocorreu um erro. Tente novamente mais tarde: " + exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<String> integrutyViolationHandler(DataIntegrityViolationException data){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(data.getMessage());
    }

    @ExceptionHandler(CpfException.class)
    private ResponseEntity<String> cpfExceptionHandler(CpfException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

}
