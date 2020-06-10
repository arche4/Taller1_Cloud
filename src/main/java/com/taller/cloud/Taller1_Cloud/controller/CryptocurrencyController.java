package com.taller.cloud.Taller1_Cloud.controller;

import com.taller.cloud.Taller1_Cloud.acl.NotFoundException;
import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.service.CryptocurrencyService;
import com.taller.cloud.Taller1_Cloud.service.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/currency")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    private MapValidationErrorService validationErrorService;

    @Autowired
    private NotFoundException notFoundException;

    @GetMapping(value = "/{name}")
    public ResponseEntity<List<Cryptocurrency>> getCryptocurrency(@PathVariable("name") String name){
        List<Cryptocurrency> nameCurrency = cryptocurrencyService.findByName(name);
        if(nameCurrency.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nameCurrency);
    }

    @GetMapping
    public List<Cryptocurrency> getAllCryptocurrency(){
        return cryptocurrencyService.listAllCryptocurrencies();
    }

    @PostMapping("")
    public ResponseEntity<?> createCryptocurrency(@Valid @RequestBody Cryptocurrency cryptocurrency, BindingResult result) {
        ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        if (cryptocurrency.getName() == null || cryptocurrency.getSymbol() == null) {
            ResponseEntity<?> exception = notFoundException.Exception("name y symbol no puede ser vacio");
            return exception;
        }
        List<Cryptocurrency> nameCurrency = cryptocurrencyService.findByName(cryptocurrency.getName());
        List<Cryptocurrency> SymbolCurrency = cryptocurrencyService.findBySymbol(cryptocurrency.getSymbol());

        if(!nameCurrency.isEmpty() && !SymbolCurrency.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("name y Symbol ya existe");
            return exception;
        }

        if(!nameCurrency.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("name ya existe");
            return exception;
        }
        if(!SymbolCurrency.isEmpty()){
            ResponseEntity<?> exception = notFoundException.Exception("Symbol ya existe");
            return exception;
        }
        
        Cryptocurrency cryptocurrencyCreated = cryptocurrencyService.createCryptocurrency(cryptocurrency);
        if (cryptocurrencyCreated==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(cryptocurrencyCreated);
        }
    }



}
