/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fullstack.api_product.service;

import com.fullstack.api_product.entity.Product;
import java.util.List;

/**
 *
 * @author yagoc
 */
public interface ProductService {
    //create
    public Product createProduct(Product product);

    //read
    public List<Product> getAllProducts();
    public Product getProductById(Long productId);

    //update
    public Product updateProduct(Long productId, Product product);

    //delete
    public void deleteProductById(Long productId);

}
