package com.taller.cloud.Taller1_Cloud.service;

import com.taller.cloud.Taller1_Cloud.model.Quote;

import java.util.Optional;

public interface QuoteService {

    Optional<Quote> getFileByQuote(Long id);
    public Quote creaQuote(Quote Quote);

}
