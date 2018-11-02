package com.kudoji.taco;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private long id;

    @NotBlank
    @Size(min = 5, message = "Name must be at least five characters long")
    private String name;

    private Date createdAt;

    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<Ingredient> ingredients;
}