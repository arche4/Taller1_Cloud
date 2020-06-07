package com.taller.cloud.Taller1_Cloud.controller;

import com.taller.cloud.Taller1_Cloud.acl.Exception;
import com.taller.cloud.Taller1_Cloud.acl.NotFoundException;
import com.taller.cloud.Taller1_Cloud.acl.http.QuoteResponse;
import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.repository.QuoteRepository;
import com.taller.cloud.Taller1_Cloud.service.CryptocurrencyService;
import com.taller.cloud.Taller1_Cloud.service.MapValidationErrorService;
import com.taller.cloud.Taller1_Cloud.service.QuoteService;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private MapValidationErrorService validationErrorService;

    @Autowired
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    private NotFoundException notFoundException;


    @PostMapping("/{id}")
    public ResponseEntity<?> crearQuote(@Valid @PathVariable Long id, @RequestBody Quote quote, BindingResult result){

        ResponseEntity<?> errorMap = validationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;
        Cryptocurrency currency = cryptocurrencyService.getCyroCryptocurrency(id);

        if (currency == null) {
            ResponseEntity<?> exception = notFoundException.Exception("Currency: " + id + " doesn't exist");
            return exception;
        }
        if (quote.getName() == null || quote.getSymbol() == null) {
            ResponseEntity<?> exception = notFoundException.Exception("name y symbol no puede ser vacio");
            return exception;
        }

        if(!quote.getSymbol().equals("USD") && !quote.getSymbol().equals("EUR") && !quote.getSymbol().equals("GBP"))
            throw new Exception("Solo es posible adicionar estas tres divisas\n" +
                "USD-EUR-GBP ");

        quote.setCryptocurrency(currency);
        Quote quoteCreate = quoteService.creaQuote(quote);
        if (quoteCreate==null){
            return ResponseEntity.notFound().build();
        }else{
            Cryptocurrency updateRank = cryptocurrencyService.updateRank(id);
            if (updateRank==null){
                ResponseEntity<?> exception = notFoundException.Exception("Se presento un erro cambiando el rank");
                return exception;
            }
            QuoteResponse response = new QuoteResponse(quoteCreate.getId(), quoteCreate.getName(), quoteCreate.getSymbol(), quoteCreate.getPrice(),quoteCreate.getLastUpdate());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
}
    }
}
