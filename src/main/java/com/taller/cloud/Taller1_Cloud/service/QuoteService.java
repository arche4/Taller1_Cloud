package com.taller.cloud.Taller1_Cloud.service;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;

import java.util.List;

public interface QuoteService {

    public Quote getQuote(Long id);
    public Quote creaQuote(Quote quote);
    public List<Quote> findByNameAndCryptocurrency(String name, Cryptocurrency currency);
    public List<Quote> findBySymbolAndCryptocurrency(String symbol, Cryptocurrency currency);

}
