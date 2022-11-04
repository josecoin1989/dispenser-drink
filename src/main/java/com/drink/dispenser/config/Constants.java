package com.drink.dispenser.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Constants to be used for the app
 */
@Component
@Getter
public class Constants {

    // Responses
    public static final String RESPONSE_OK = "success";
    public static final String RESPONSE_ERROR = "error";

    // Machine Status
    public static final String STATUS_STARTED = "STARTED";
    public static final String STATUS_ERROR = "ERROR";
    public static final String STATUS_SELLING = "SELLING";
    public static final String STATUS_STOP = "STOP";


    public static final String MACHINE_NAME = "jadg_machine";

    // Coin types
    public static final String TYPE_COIN_5CENT = "5cent";
    public static final String TYPE_COIN_10CENT = "10cent";
    public static final String TYPE_COIN_20CENT = "20cent";
    public static final String TYPE_COIN_50CENT = "50cent";
    public static final String TYPE_COIN_1EURO = "1euro";
    public static final String TYPE_COIN_2EURO = "2euro";

    public static final String COIN_ALLOWED_TYPES = TYPE_COIN_5CENT
            +"|"+TYPE_COIN_10CENT
            +"|" + TYPE_COIN_20CENT
            +"|" + TYPE_COIN_50CENT
            +"|" + TYPE_COIN_1EURO
            +"|" + TYPE_COIN_2EURO;



    public static final double VALUE_COIN_5CENT = 0.05;
    public static final double VALUE_COIN_10CENT = 0.10;
    public static final double VALUE_COIN_20CENT = 0.20;
    public static final double VALUE_COIN_50CENT = 0.5;
    public static final double VALUE_COIN_1EURO = 1.0;
    public static final double VALUE_COIN_2EURO = 2.0;

    // Default Drinks
    public static final String DRINK_NAME_COCA = "Coca";
    public static final String DRINK_NAME_REDBULL = "Redbull";
    public static final String DRINK_NAME_WATER = "Water";
    public static final String DRINK_NAME_ORANGE_JUICE = "Orange Juice";

    public static final String DRINK_ALLOWED_TYPES = DRINK_NAME_COCA
            +"|"+ DRINK_NAME_REDBULL
            +"|" + DRINK_NAME_WATER
            +"|" + DRINK_NAME_ORANGE_JUICE;


    public static final double VALUE_COCA = 1;
    public static final double VALUE_REDBULL = 1.25;
    public static final double VALUE_WATER = 0.5;
    public static final double VALUE_ORANGE_JUICE = 1.95;








    private Constants() {
    }

}
