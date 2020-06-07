package com.taller.cloud.Taller1_Cloud.repository;

import com.taller.cloud.Taller1_Cloud.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository  extends JpaRepository<Quote, Long> {
}
