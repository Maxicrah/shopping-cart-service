package com.maxi.shoppingcartservice.dao;

import com.maxi.shoppingcartservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "products-service")
public interface IProductsAPI {

    @GetMapping("product/{idProduct}")
    public ProductDTO getProductById(@PathVariable Long idProduct);


}
