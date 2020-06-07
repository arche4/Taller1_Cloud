package com.taller.cloud.Taller1_Cloud.service.Impl;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.repository.CryptocurrencyRepository;
import com.taller.cloud.Taller1_Cloud.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Autowired
    private CryptocurrencyRepository cryptocurrencyRepository;

    @Override
    public List<Cryptocurrency> listAllCryptocurrencies() {
        return cryptocurrencyRepository.findAll();
    }

    @Override
    public Cryptocurrency getCyroCryptocurrency(Long id) {
        return cryptocurrencyRepository.findById(id).orElse(null);
    }

    @Override
    public Cryptocurrency createCryptocurrency(Cryptocurrency cryptocurrency) {
        return cryptocurrencyRepository.save(cryptocurrency);
    }

    @Override
    public Cryptocurrency updateRank(Long id) {
        Cryptocurrency currey = getCyroCryptocurrency(id);
        List<Quote> quotes = currey.getQuote();
        for(int x =0; x< quotes.size(); x++){
              Quote price = quotes.get(x);

        }
        return null;
    }

}
