package com.maxi.shoppingcartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductDTO {
    private Long idProduct;
    private String productName;
    private Double price;
    private String brand;
    //private Double total;
}
