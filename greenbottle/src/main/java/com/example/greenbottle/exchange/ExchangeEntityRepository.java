package com.example.greenbottle.exchange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeEntityRepository extends JpaRepository<ExchangeEntity,String> {
}
