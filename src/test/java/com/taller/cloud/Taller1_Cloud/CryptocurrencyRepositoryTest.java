package com.taller.cloud.Taller1_Cloud;

import com.taller.cloud.Taller1_Cloud.model.Cryptocurrency;
import com.taller.cloud.Taller1_Cloud.model.Quote;
import com.taller.cloud.Taller1_Cloud.repository.CryptocurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CryptocurrencyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CryptocurrencyRepository repository;

    @Test
    public void saveCryptoCurrency(){
        List<Quote> quote = new ArrayList<>();
        Cryptocurrency cryptocurrency = new Cryptocurrency("Bintops", "BIC", (long) 0, quote);
        entityManager.persistAndFlush(cryptocurrency);
        assertEquals("Bintops", cryptocurrency.getName());
    }



}
