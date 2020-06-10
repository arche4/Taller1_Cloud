package com.taller.cloud.Taller1_Cloud.service;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;

import java.util.HashMap;
import java.util.List;

public interface CryptocurrencyService {
    public List<Cryptocurrency> listAllCryptocurrencies();
    public Cryptocurrency getCyroCryptocurrency(Long id);
    public Cryptocurrency createCryptocurrency(Cryptocurrency cryptocurrency);
    public Cryptocurrency updateRank(Long id, Long quoteId);
    public List<Cryptocurrency> findByName(String name);
    public List<Cryptocurrency> findBySymbol(String symbol);

}
