package com.banking.exception.advice;

import com.banking.exception.ErrorResponse;
import com.banking.exception.ExistException;
import com.banking.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.BindException;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    /* - Khi một BindException xảy ra trong phạm vi của phương thức được đánh dấu, BindException sẽ bắt và xử lý nó
      - Phương thức này tạo ra một đối tượng ErrorResponse chứa FileErrors (Lỗi xảy ra trên các trường dữ liệu)
      - Sau đó tạo ra một ResponseEntity chứa thông tin lỗi trong đối tượng ErrorResponse trả về cho client với mã lỗi
        là HttpStatus.BAD_REQUEST */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errors,
                request.getDescription(false)
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /* - Phương thức xử lý các RuntimeException trong ứng dụng
       - Khi một RuntimeException xảy ra phương thức sẽ tạo ra 1 ErrorResponse lỗi xảy ra
       - Sau đó tạo ra một ResponseEntity chứa thông tin lỗi trong đối tượng ErrorResponse trả về cho client với mã lỗi
         là HttpStatus.BAD_REQUEST */
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleBindException(
//            RuntimeException ex, HttpHeaders headers,
//            HttpStatusCode statusCode,
//            WebRequest request) {
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                HttpStatus.BAD_REQUEST.value(),
//                new Date(),
//                List.of(ex.getMessage()),
//                request.getDescription(false)
//        );
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(ExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //409
    public ErrorResponse ExistHandler(ExistException ex, WebRequest request){
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse NotFoundHandler(NotFoundException ex, WebRequest request){
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse BadCredentialsHandler(BadCredentialsException ex, WebRequest request){
        return new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                List.of(ex.getMessage()),
                request.getDescription(false)
        );
    }
}

