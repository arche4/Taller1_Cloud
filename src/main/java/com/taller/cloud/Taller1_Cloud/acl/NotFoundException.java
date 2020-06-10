package com.taller.cloud.Taller1_Cloud.acl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotFoundException {
    public ResponseEntity<?> Exception(String message){
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
