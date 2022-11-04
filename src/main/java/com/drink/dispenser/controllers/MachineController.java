package com.drink.dispenser.controllers;

import com.drink.dispenser.config.Constants;
import com.drink.dispenser.dtos.SellDrinkDTO;
import com.drink.dispenser.entities.DrinkEntity;
import com.drink.dispenser.entities.MachineEntity;
import com.drink.dispenser.services.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/machine")
@Api(tags = "machine")
public class MachineController {

    @Autowired
    private MachineService machineService;


    @GetMapping("/stop")
    public String stopMachine() throws Exception {

        this.machineService.stopMachine();

        return Constants.RESPONSE_OK;
    }

    @PostMapping("/wallet/coin/{typeCoin}")
    public double addCoinWallet(@ApiParam(name = "typeCoin", value = "The type coin to be add to the wallet")
                                @PathVariable("typeCoin")
                                @Pattern(regexp = Constants.COIN_ALLOWED_TYPES,
                                        flags = Pattern.Flag.CASE_INSENSITIVE,
                                        message = "Coin not valid") final String typeCoin) throws Exception {

        return this.machineService.addCoinWallet(typeCoin.toLowerCase(Locale.ROOT));
    }

    @PostMapping("/wallet/reset")
    public double resetWallet() throws Exception {
        return this.machineService.resetWallet();
    }


    @GetMapping("/wallet")
    public double getWallet() throws Exception {
        return this.machineService.getWallet();
    }

    @PostMapping("/drink/sell/{typeDrink}")
    public SellDrinkDTO buyDrink(@ApiParam(name = "typeDrink", value = "The drink to by")
                                @PathVariable("typeDrink")
                                @Pattern(regexp = Constants.DRINK_ALLOWED_TYPES,
                                        flags = Pattern.Flag.CASE_INSENSITIVE,
                                        message = "Drink not valid") final String typeDrink) throws Exception {

        return this.machineService.buyDrink(typeDrink);

    }

    @GetMapping("/drink/all")
    public List<DrinkEntity> getAllDrinks() throws Exception {

        return this.machineService.getAllDrinks();

    }

    @GetMapping("/")
    public MachineEntity getMachine() throws Exception {

        return this.machineService.getMachine();

    }
}
