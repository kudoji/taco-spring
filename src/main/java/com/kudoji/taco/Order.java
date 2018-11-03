package com.kudoji.taco;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date placedAt;

    @PrePersist
    private void placedAt(){
        this.placedAt = new Date();
    }

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City name is required")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State is only two letters long")
    private String state;

    @NotBlank(message = "ZIP code is required")
    private String zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String creditCardNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/[1-9][0-9]$",
             message = "Use the following MM/YY format")
    private String creditCardExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV code")
    private String creditCardCVV;

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
