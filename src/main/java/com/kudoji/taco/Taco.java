package com.kudoji.taco;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @Size(min = 5, message = "Name must be at least five characters long")
    private String name;

    private Date createdAt;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<Ingredient> ingredients;

    @PrePersist
    private void createdAt(){
        this.createdAt = new Date();
    }
}