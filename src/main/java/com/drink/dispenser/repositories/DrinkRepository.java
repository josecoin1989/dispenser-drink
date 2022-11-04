package com.drink.dispenser.repositories;

import com.drink.dispenser.entities.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {
    Optional<DrinkEntity> findByName(String typeDrink);
}