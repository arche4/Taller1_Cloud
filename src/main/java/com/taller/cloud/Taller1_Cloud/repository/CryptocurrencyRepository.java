package com.taller.cloud.Taller1_Cloud.repository;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {

    public List<Cryptocurrency> findByName(String name);
    public List<Cryptocurrency> findBySymbol(String symbol);

}
