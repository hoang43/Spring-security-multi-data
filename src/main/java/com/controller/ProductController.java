package com.controller;

import com.entities.Product;
import com.repo2.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  ProductRepository productRepository;

  @PostMapping
  public ResponseEntity<?> createProducts(){
    List<Product> products = new ArrayList<>();
    for (int i =0 ; i < 5; i++){
      Product product = new Product();
      product.setName("product " + i);
      products.add(product);
    }
    productRepository.saveAll(products);
    return ResponseEntity.ok("SUCCESS");
  }

}
