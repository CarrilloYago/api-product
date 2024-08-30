/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fullstack.api_product.controller;

import com.fullstack.api_product.entity.Product;
import com.fullstack.api_product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yagoc
 */
@CrossOrigin("*")

@RestController
@RequestMapping("/api")
public class ProductController {
   @Autowired
   private ProductService productService;
   @PostMapping("/products")
   //method to save product
   public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
       System.out.println(product);
       return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
   }

   @GetMapping("/products")
   //method to get all products
   public ResponseEntity<List<Product>> getAllProducts() {
       return ResponseEntity.ok(productService.getAllProducts());
   }

   @GetMapping("/products/{id}")
   //method to get product by id
   public ResponseEntity<Product> getProductById(@PathVariable Long id) {
       return ResponseEntity.ok(productService.getProductById(id));
   }

   @PutMapping("/products/{id}")
   //method to update product
   public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
       System.out.println(id);
       System.out.println(product);
       Product existingProduct = productService.getProductById(id);
       if (existingProduct == null) {
           throw new EntityNotFoundException("Product not found with id: " + id);
       }
       return ResponseEntity.ok(productService.updateProduct(id, product));
   }

   @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    try {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
   }
}


