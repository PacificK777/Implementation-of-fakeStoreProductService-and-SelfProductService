package com.example.projectbyumang.Controller;

import com.example.projectbyumang.DTOS.CustomErrorResponseDTO;
import com.example.projectbyumang.Exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException ex) {
        CustomErrorResponseDTO errorResponse = new CustomErrorResponseDTO();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}