package com.drink.dispenser.repositories;

import com.drink.dispenser.entities.MachineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MachineRepository extends JpaRepository<MachineEntity, Long> {
    Optional<MachineEntity> findByName(String machineName);
}