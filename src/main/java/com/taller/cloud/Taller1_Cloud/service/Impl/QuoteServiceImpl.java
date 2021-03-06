package com.taller.cloud.Taller1_Cloud.service.Impl;

import com.taller.cloud.Taller1_Cloud.acl.Exception;
import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.repository.QuoteRepository;
import com.taller.cloud.Taller1_Cloud.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public Quote getQuote(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }

    @Override
    public Quote creaQuote(Quote quote) {
        try {
            Date fecha = new Date();
            quote.setLastUpdate(fecha);
            return quoteRepository.save(quote);
        }catch (Exception ex){
            throw new Exception("Nombre y simbolo ya existen", ex);
        }

    }

    @Override
    public List<Quote> findByNameAndCryptocurrency(String name, Cryptocurrency currency) {
        return quoteRepository.findByNameAndCryptocurrency(name, currency);
    }

    @Override
    public List<Quote> findBySymbolAndCryptocurrency(String symbol, Cryptocurrency currency) {
        return quoteRepository.findBySymbolAndCryptocurrency(symbol, currency);
    }


}
