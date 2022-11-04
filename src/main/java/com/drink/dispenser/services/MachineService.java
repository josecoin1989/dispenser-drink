package com.drink.dispenser.services;

import com.drink.dispenser.config.Constants;
import com.drink.dispenser.dtos.SellDrinkDTO;
import com.drink.dispenser.entities.DrinkEntity;
import com.drink.dispenser.entities.MachineEntity;
import com.drink.dispenser.repositories.MachineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {

    Logger logger = LoggerFactory.getLogger(MachineService.class);


    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private CoinService coinService;

    @Autowired
    private DrinkService drinkService;

    /**
     * Start the machine
     */
    public void startMachine() throws Exception {
        MachineEntity machineEntity;

        if (this.getMachineByName(Constants.MACHINE_NAME).isPresent()){
            machineEntity = this.getMachineByName(Constants.MACHINE_NAME).get();
        }
        else{
            machineEntity = this.createMachine();
        }

        machineEntity.setStatus(Constants.STATUS_STARTED);
        this.machineRepository.save(machineEntity);
    }

    /**
     * Start the machine
     */
    public void stopMachine() throws Exception {

        MachineEntity machineEntity = this.getMachine();
        machineEntity.setStatus(Constants.STATUS_STOP);

        this.machineRepository.save(machineEntity);

    }

    /**
     * Initialize the machine
     * @return
     */
    private MachineEntity createMachine() throws Exception {

        MachineEntity machineEntity = MachineEntity.builder()
                .name(Constants.MACHINE_NAME)
                .status(Constants.STATUS_STARTED)
                .listCoinEntity(this.coinService.generateDefaultCoins())
                .listDrinkEntity(this.drinkService.generateDefaultDrinks())
                .build();

        machineEntity = this.machineRepository.save(machineEntity);

        this.updateTotalAmount();

        return machineEntity;
    }

    /**
     * Update the total Amount of the Machine
     */
    private void updateTotalAmount() throws Exception {

        MachineEntity machineEntity = this.getMachine();

        final double totalAmount = machineEntity.getListCoinEntity()
                .stream().map(coinEntity -> coinEntity.getQuantity()* coinEntity.getValueCoin())
                .mapToDouble(value -> value).sum();

        machineEntity.setTotalAmount(totalAmount);

       this.machineRepository.save(machineEntity);
    }

    /**
     * Update the amount of the wallet
     * @param value
     * @return
     * @throws Exception
     */
    public double updateWallet(final double value) throws Exception {
        MachineEntity machineEntity = this.getMachine();

        machineEntity.setWallet(machineEntity.getWallet()+value);

        this.machineRepository.save(machineEntity);

        this.updateTotalAmount();

        return machineEntity.getWallet();
    }


    /**
     * Get the machine
     * @param name
     * @return
     */
    public Optional<MachineEntity> getMachineByName(final String name){

        return this.machineRepository.findByName(name);
    }

    /**
     * Get the machine
     */
    public MachineEntity getMachine() throws Exception {
        return this.getMachineByName(Constants.MACHINE_NAME).orElseThrow(() ->new Exception("Machine not found"));
    }


    /**
     * Get the wallet of the machine
     * @return
     * @throws Exception
     */
    public double getWallet() throws Exception {
        MachineEntity machineEntity = this.getMachine();

        return machineEntity.getWallet();

    }

    /**
     * Reset the Wallet of the machine
     * @return
     * @throws Exception
     */
    public double resetWallet() throws Exception {
        MachineEntity machineEntity = this.getMachine();

        this.coinService.returnCoins(machineEntity.getWallet());

        machineEntity.setWallet(0.0);

        this.machineRepository.save(machineEntity);

        return this.getWallet();

    }

    /**
     * Add a Coin to the wallet
     *
     * @param typeCoin
     * @return
     * @throws Exception
     */
    public double addCoinWallet(final String typeCoin) throws Exception {

        final double valueCoin = this.coinService.addCoin(typeCoin);

        return this.updateWallet(valueCoin);
    }


    /**
     * Buy a Drink
     *
     * @param typeDrink
     * @return
     * @throws Exception
     */
    public SellDrinkDTO buyDrink(final String typeDrink) throws Exception {

        DrinkEntity drinkEntity = this.drinkService.getDrinkByName(typeDrink);

        MachineEntity machineEntity = this.getMachine();

        if(machineEntity.getWallet()>drinkEntity.getPrice()){
            this.drinkService.buyDrink(drinkEntity);

            final double moneyReturn = machineEntity.getWallet()-drinkEntity.getPrice();

            machineEntity.setWallet(machineEntity.getWallet()-drinkEntity.getPrice());

            this.machineRepository.save(machineEntity);

            this.resetWallet();

            return SellDrinkDTO.builder()
                    .drink(drinkEntity.getName())
                    .money(moneyReturn)
                    .build();
        }else{
            throw new Exception("Money not enough");
        }


    }

    /**
     * Get all drinks availabe of teh machine
     * @return
     */
    public List<DrinkEntity> getAllDrinks() throws Exception {


        return this.getMachine().getListDrinkEntity();
    }

}
