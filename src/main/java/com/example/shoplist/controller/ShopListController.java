package com.example.shoplist.controller;

import com.example.shoplist.model.Product;
import com.example.shoplist.service.ShopListService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopList")
public class ShopListController {

    @Autowired
    ShopListService shopList;

    @GetMapping("/")
    public List<Product> show(){
        return shopList.getShopList();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Long id, HttpServletResponse response){
        if(shopList.getProduct(id) == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return shopList.getProduct(id);
    }

    @PostMapping("/add")
    public Product create(@RequestBody Product product) {
        return shopList.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable(value = "id") Long id) {
        return shopList.updateProduct(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        shopList.deleteProduct(id);
    }
}
