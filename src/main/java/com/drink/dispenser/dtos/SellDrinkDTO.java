package com.drink.dispenser.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SellDrinkDTO {

    private String drink;

    private double money;
}
