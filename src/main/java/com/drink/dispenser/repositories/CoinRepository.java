package com.drink.dispenser.repositories;

import com.drink.dispenser.entities.CoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CoinRepository extends JpaRepository<CoinEntity, Long> {
    Optional<CoinEntity> findByType(String typeCoin);
}