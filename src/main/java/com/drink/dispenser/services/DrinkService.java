package com.drink.dispenser.services;

import com.drink.dispenser.config.Constants;
import com.drink.dispenser.entities.DrinkEntity;
import com.drink.dispenser.repositories.DrinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {

    Logger logger = LoggerFactory.getLogger(DrinkService.class);


    @Autowired
    private DrinkRepository drinkRepository;


    public List<DrinkEntity> generateDefaultDrinks(){

        List<DrinkEntity> defaultDrinks = new ArrayList<>();

        DrinkEntity cocaEntity = DrinkEntity.builder()
                .name(Constants.DRINK_NAME_COCA)
                .price(Constants.VALUE_COCA)
                .stock(10)
                .build();

        defaultDrinks.add(cocaEntity);

        DrinkEntity redBullEntity = DrinkEntity.builder()
                .name(Constants.DRINK_NAME_REDBULL)
                .price(Constants.VALUE_REDBULL)
                .stock(20)
                .build();

        defaultDrinks.add(redBullEntity);

        DrinkEntity waterEntity = DrinkEntity.builder()
                .name(Constants.DRINK_NAME_WATER)
                .price( Constants.VALUE_WATER)
                .stock(50)
                .build();

        defaultDrinks.add(waterEntity);

        DrinkEntity orangeJuiceEntity = DrinkEntity.builder()
                .name(Constants.DRINK_NAME_ORANGE_JUICE)
                .price( Constants.VALUE_ORANGE_JUICE)
                .stock(10)
                .build();

        defaultDrinks.add(orangeJuiceEntity);

        this.drinkRepository.saveAll(defaultDrinks);
        return defaultDrinks;

    }

    /**
     * Get the drink by name
     * @param typeDrink
     * @return
     */
    public DrinkEntity getDrinkByName(final String typeDrink){

        return this.drinkRepository.findByName(typeDrink).orElseThrow();

    }

    /**
     * Buy a Drink
     * @param drinkEntity
     * @return
     */
    public DrinkEntity buyDrink(DrinkEntity drinkEntity){

        drinkEntity.setStock(drinkEntity.getStock()-1);

        return this.drinkRepository.save(drinkEntity);
    }


}
