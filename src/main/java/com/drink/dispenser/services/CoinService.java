package com.drink.dispenser.services;

import com.drink.dispenser.config.Constants;
import com.drink.dispenser.entities.CoinEntity;
import com.drink.dispenser.repositories.CoinRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CoinService {

    Logger logger = LoggerFactory.getLogger(CoinService.class);


    @Autowired
    private CoinRepository coinRepository;


    /**
     * Generate the default Coins of the Machine
     */
    public List<CoinEntity> generateDefaultCoins() {


        List<CoinEntity> listDefaultCoins = new ArrayList<>();

        // 5 cent
        CoinEntity coin5cent = CoinEntity.builder()
                .quantity(100)
                .type(Constants.TYPE_COIN_5CENT)
                .valueCoin(Constants.VALUE_COIN_5CENT)
                .build();

        listDefaultCoins.add(coin5cent);

        // 10 cent
        CoinEntity coin10cent = CoinEntity.builder()
                .quantity(200)
                .type(Constants.TYPE_COIN_10CENT)
                .valueCoin(Constants.VALUE_COIN_10CENT)
                .build();

        listDefaultCoins.add(coin10cent);

        // 20 cent
        CoinEntity coin20cent = CoinEntity.builder()
                .quantity(50)
                .type(Constants.TYPE_COIN_20CENT)
                .valueCoin(Constants.VALUE_COIN_20CENT)
                .build();

        listDefaultCoins.add(coin20cent);

        // 50 cent
        CoinEntity coin50cent = CoinEntity.builder()
                .quantity(150)
                .type(Constants.TYPE_COIN_50CENT)
                .valueCoin(Constants.VALUE_COIN_50CENT)
                .build();

        listDefaultCoins.add(coin50cent);

        // 1 €
        CoinEntity coin1euro = CoinEntity.builder()
                .quantity(5)
                .type(Constants.TYPE_COIN_1EURO)
                .valueCoin(Constants.VALUE_COIN_1EURO)
                .build();

        listDefaultCoins.add(coin1euro);

        // 2 €
        CoinEntity coin2euro = CoinEntity.builder()
                .quantity(5)
                .type(Constants.TYPE_COIN_2EURO)
                .valueCoin(Constants.VALUE_COIN_2EURO)
                .build();

        listDefaultCoins.add(coin2euro);


        return this.coinRepository.saveAll(listDefaultCoins);


    }

    /**
     * Add a Coin to the wallet
     *
     * @param typeCoin
     * @return
     * @throws Exception
     */
    public double addCoin(final String typeCoin) throws Exception {

        CoinEntity coinEntity = this.getCoinValueByType(typeCoin);

        coinEntity.setQuantity(coinEntity.getQuantity() + 1);

        this.coinRepository.save(coinEntity);

        return coinEntity.getValueCoin();
    }

    /**
     * Get the value of a Coin by type
     *
     * @param typeCoin
     * @return
     */
    public CoinEntity getCoinValueByType(final String typeCoin) {

        return this.coinRepository.findByType(typeCoin).orElseThrow();

    }

    /**
     * Returns the coins
     *
     * @param value
     */
    public void returnCoins(double value) {

        while (value > 0) {
            CoinEntity coinEntity = null;
            if (value >= Constants.VALUE_COIN_2EURO) {
                coinEntity = this.getCoinValueByType(Constants.TYPE_COIN_2EURO);
                coinEntity.setQuantity(coinEntity.getQuantity() - 1);

                value = value - coinEntity.getValueCoin();
            }
            else if (value >= Constants.VALUE_COIN_1EURO) {
                coinEntity = this.getCoinValueByType(Constants.TYPE_COIN_1EURO);
                coinEntity.setQuantity(coinEntity.getQuantity() - 1);

                value = value - coinEntity.getValueCoin();
            }
            else if (value >= Constants.VALUE_COIN_50CENT) {
                coinEntity = this.getCoinValueByType(Constants.TYPE_COIN_50CENT);
                coinEntity.setQuantity(coinEntity.getQuantity() - 1);

                value = value - coinEntity.getValueCoin();
            }
            else if (value >= Constants.VALUE_COIN_20CENT) {
                coinEntity = this.getCoinValueByType(Constants.TYPE_COIN_20CENT);
                coinEntity.setQuantity(coinEntity.getQuantity() - 1);

                value = value - coinEntity.getValueCoin();
            }
            else if (value >= Constants.VALUE_COIN_10CENT) {
                coinEntity = this.getCoinValueByType(Constants.TYPE_COIN_10CENT);
                coinEntity.setQuantity(coinEntity.getQuantity() - 1);

                value = value - coinEntity.getValueCoin();
            }
            else if (value >= Constants.VALUE_COIN_5CENT) {
                coinEntity = this.getCoinValueByType(Constants.TYPE_COIN_5CENT);
                coinEntity.setQuantity(coinEntity.getQuantity() - 1);

                value = value - coinEntity.getValueCoin();
            }


            // To avoid problems with the double 0.0000001
            if(Objects.nonNull(coinEntity)){
                coinRepository.save(coinEntity);
            }else{
                value=0;
            }
        }
    }


}
