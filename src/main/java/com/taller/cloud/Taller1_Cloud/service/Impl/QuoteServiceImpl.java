package com.taller.cloud.Taller1_Cloud.service.Impl;

import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.service.QuoteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Override
    public Optional<Quote> getFileByQuote(Long id) {
        return Optional.empty();
    }

    @Override
    public Quote creaQuote(Quote Quote) {
        return null;
    }
}
