package com.maxi.shoppingcartservice.service.imp;

import com.maxi.shoppingcartservice.dao.IProductsAPI;
import com.maxi.shoppingcartservice.dao.IShoppingCartDAO;
import com.maxi.shoppingcartservice.dto.ProductDTO;
import com.maxi.shoppingcartservice.model.ShoppingCart;
import com.maxi.shoppingcartservice.service.IShoppingCartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService {

    private final IProductsAPI productsAPI;
    private final IShoppingCartDAO shoppingCartDAO;
    public ShoppingCartService(IShoppingCartDAO shoppingCartDAO, IProductsAPI productsAPI) {
        this.shoppingCartDAO = shoppingCartDAO;
        this.productsAPI = productsAPI;
    }


    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setTotal(0.0);
        shoppingCart.setProductIds(null);
        return shoppingCartDAO.save(shoppingCart);
    }

    @Override
    public ShoppingCart findShoppingCartById(Long id) {
        return this.shoppingCartDAO.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name="products-service", fallbackMethod = "fallbackAddProduct")
    @Retry(name="products-service")
    public ShoppingCart addProduct(Long idShoppingCart, Long idProduct) {
        ShoppingCart cart = this.findShoppingCartById(idShoppingCart);
        ProductDTO productDTO = this.productsAPI.getProductById(idProduct);
        Double total = cart.getTotal();
        total += productDTO.getPrice();
        cart.setTotal(total);
        List<Long> products = cart.getProductIds();
       // cart.getProductIds().add(productDTO.getIdProduct());
        products.add(productDTO.getIdProduct());
        //exception
        //pring:
        // this.createException();
        return this.shoppingCartDAO.save(cart);
    }

    public ShoppingCart fallbackAddProduct(Long idShoppingCart, Long idProduct, Throwable throwable) {
        System.err.println("Error al agregar el producto: " + throwable.getMessage());
        return new ShoppingCart(idShoppingCart, 0.0, new ArrayList<>());
    }

    public void createException(){
        throw new RuntimeException("Prueba Circuit breaker y resilience4j");
    }

    @Override
    public void removeProduct(Long idShoppingCart, Long idProduct) {
        ShoppingCart cart = this.findShoppingCartById(idShoppingCart);
        ProductDTO productDTO = this.productsAPI.getProductById(idProduct);
        Double total = cart.getTotal();
        total -= productDTO.getPrice();
        cart.setTotal(total);
        List<Long> products = cart.getProductIds();
        products.remove(productDTO.getIdProduct());
        this.shoppingCartDAO.save(cart);
    }
}
