package com.maxi.shoppingcartservice.service;

import com.maxi.shoppingcartservice.dto.ProductDTO;
import com.maxi.shoppingcartservice.model.ShoppingCart;
import org.hibernate.type.descriptor.java.ShortPrimitiveArrayJavaType;

import java.util.List;

public interface IShoppingCartService {

    //crear carrito compra
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart);
    //buscar carrito por id
    public ShoppingCart findShoppingCartById(Long id);
    //añadir productos al carro
    public ShoppingCart addProduct(Long idShoppingCart, Long idProduct);
    //sacar producto del carro
    public void removeProduct(Long idShoppingCart, Long idProduct);


}
