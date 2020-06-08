package com.taller.cloud.Taller1_Cloud.service.Impl;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.repository.CryptocurrencyRepository;
import com.taller.cloud.Taller1_Cloud.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Cryptocurrency updateRank(Long id) {
        double precio = 0;
        Cryptocurrency currey = getCyroCryptocurrency(id);
        if (currey != null) {
            List<Quote> quotes = currey.getQuote();
            for (Quote cuota : quotes) {
                if (precio < cuota.getPrice()) {
                    precio = cuota.getPrice();
                    currey.setRank(cuota.getId());
                }
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

}
