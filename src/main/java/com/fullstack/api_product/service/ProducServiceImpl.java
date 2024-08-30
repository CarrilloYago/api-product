/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fullstack.api_product.service;

import com.fullstack.api_product.entity.Product;
import com.fullstack.api_product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yagoc
 */
@Service
public class ProducServiceImpl implements ProductService {

   @Autowired
   private ProductRepository productRepository;

   @Override
   public Product createProduct(Product product) {
       return productRepository.save(product);
   }

   @Override
   public List<Product> getAllProducts() {
       return productRepository.findAll();
   }

   @Override
   public Product getProductById(Long productId) {
       return productRepository.findById(productId)
               .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
   }

   @Override
   public Product updateProduct(Long productId, Product product) {
       Product existingProduct = productRepository.findById(productId)
               .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
       //update field
       existingProduct.setName(product.getName());
       existingProduct.setPrice(product.getPrice());
       existingProduct.setQuantity(product.getQuantity());
       System.out.println(product);
       return productRepository.save(existingProduct);
   }

 
   @Override
   public void deleteProductById(Long productId) {
       if (!productRepository.existsById(productId)) {
           throw new EntityNotFoundException("Product not found with id " + productId);
       }
       productRepository.deleteById(productId);
   }

}

