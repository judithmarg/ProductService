package com.project.product.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table( name = "product")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id")
    private Integer id;


    @Column( name = "name")
    private String name;


    @Column( name = "description")
    private String description;
}

