package com.drink.dispenser.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity(name = "drink")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "drinkId"})
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long drinkId;

    @Size(max = 255)
    private String name;

    private double price;

    @JsonIgnore
    private Integer stock;
}
