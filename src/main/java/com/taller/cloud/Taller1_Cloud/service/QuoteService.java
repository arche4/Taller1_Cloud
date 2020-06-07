package com.taller.cloud.Taller1_Cloud.service;

import com.taller.cloud.Taller1_Cloud.model.Quote;

public interface QuoteService {

    public Quote getQuote(Long id);
    public Quote creaQuote(Quote quote);

}
