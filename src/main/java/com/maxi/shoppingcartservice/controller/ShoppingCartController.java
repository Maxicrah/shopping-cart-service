package com.maxi.shoppingcartservice.controller;

import com.maxi.shoppingcartservice.model.ShoppingCart;
import com.maxi.shoppingcartservice.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    private final IShoppingCartService shoppingCartService;


    public ShoppingCartController(IShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCart(@RequestBody ShoppingCart shoppingCart) {
        this.shoppingCartService.saveShoppingCart(shoppingCart);
        return ResponseEntity.ok(shoppingCart);
    }


    @GetMapping("/{idCart}")
    public ResponseEntity<ShoppingCart> findCartById(@PathVariable("idCart") Long idCart) {
        this.shoppingCartService.findShoppingCartById(idCart);
        return ResponseEntity.ok(shoppingCartService.findShoppingCartById(idCart));
    }

//    @PostMapping("/{idCart}/add/product/{idProduct}")
//    public ResponseEntity<ShoppingCart> addProductToCart(@PathVariable(name="idCart")
//                                              Long idCart, @PathVariable(name = "idProduct")
//                                              Long idProduct) {
//        this.shoppingCartService.addProduct(idCart, idProduct);
//        return ResponseEntity.ok(shoppingCartService.findShoppingCartById(idCart));
//    }
@PostMapping("/{idCart}/add/product/{idProduct}")
public ResponseEntity<ShoppingCart> addProductToCart(@PathVariable(name="idCart") Long idCart,
                                                     @PathVariable(name = "idProduct") Long idProduct) {
    ShoppingCart updatedCart = this.shoppingCartService.addProduct(idCart, idProduct);
    return ResponseEntity.ok(updatedCart);
}


    @PostMapping("/{idCart}/remove/product/{idProduct}")
    public ResponseEntity<?> removeProductToCart(@PathVariable(name="idCart") Long idCart,
                                                 @PathVariable(name="idProduct")Long idProduct) {
        this.shoppingCartService.removeProduct(idCart, idProduct);
        return ResponseEntity.ok(shoppingCartService.findShoppingCartById(idCart));
    }


}
