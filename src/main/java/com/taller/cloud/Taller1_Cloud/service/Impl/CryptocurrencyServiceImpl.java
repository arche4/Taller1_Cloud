package com.taller.cloud.Taller1_Cloud.service.Impl;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.repository.CryptocurrencyRepository;
import com.taller.cloud.Taller1_Cloud.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
        cryptocurrency.setRank(0);
        return cryptocurrencyRepository.save(cryptocurrency);
    }

    @Override
    public Cryptocurrency updateRank(Long id, Long quoteId) {
        double precio = 0;
        double priceUDS = 0;
        Cryptocurrency currey = getCyroCryptocurrency(id);
        if (currey != null) {
            List<Quote> quotes = currey.getQuote();
            if (!quotes.isEmpty()){
                for (Quote cuota : quotes) {
                    String divisa = cuota.getSymbol();
                    if(divisa.equals("EUR")){
                        priceUDS  = cuota.getPrice()*1.25;
                    }else if(divisa.equals("GBP")){
                        priceUDS = cuota.getPrice()*1.5;
                    }else{
                        priceUDS = cuota.getPrice();
                    }
                    if (precio < priceUDS) {
                        precio = priceUDS;
                        currey.setRank(cuota.getId());
                    }
                }
            }else{
                currey.setRank(quoteId);
            }
            return cryptocurrencyRepository.save(currey);
        } else {
            return null;
        }
    }

    @Override
    public List<Cryptocurrency> findByName(String name) {
        return cryptocurrencyRepository.findByName(name);
    }

    @Override
    public List<Cryptocurrency> findBySymbol(String symbol) {
        return cryptocurrencyRepository.findBySymbol(symbol);
    }

}
