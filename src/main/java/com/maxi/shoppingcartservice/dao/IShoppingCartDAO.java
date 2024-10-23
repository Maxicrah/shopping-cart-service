package com.maxi.shoppingcartservice.dao;

import com.maxi.shoppingcartservice.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartDAO extends JpaRepository<ShoppingCart, Long> {

}
