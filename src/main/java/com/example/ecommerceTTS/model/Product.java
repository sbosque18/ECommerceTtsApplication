package com.example.ecommerceTTS.model;

//import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    private Integer quantity;
    private Double price;

    private String brand;
    private String category;
    private String description;
    private String size;
    private String name;
    private String image;
}
