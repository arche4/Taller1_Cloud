package com.taller.cloud.Taller1_Cloud.controller;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/currency")
public class CryptocurrencyController {

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cryptocurrency> getCryptocurrency(@PathVariable("id") Long id){
        Cryptocurrency Cryptocurrency = cryptocurrencyService.getCyroCryptocurrency(id);
        if(Cryptocurrency == null){
            return ResponseEntity.notFound().build();
        }
            return ResponseEntity.ok(Cryptocurrency);
    }

    @GetMapping
    public List<Cryptocurrency> getAllCryptocurrency(){
        return cryptocurrencyService.listAllCryptocurrencies();
    }

    @PostMapping
    public ResponseEntity<Cryptocurrency> createCryptocurrency(@RequestBody Cryptocurrency cryptocurrency) {
        Cryptocurrency cryptocurrencyCreated = cryptocurrencyService.createCryptocurrency(cryptocurrency);
        return ResponseEntity.status(HttpStatus.CREATED).body(cryptocurrencyCreated);
    }



}
