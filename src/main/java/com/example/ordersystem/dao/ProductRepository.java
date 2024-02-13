/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ordersystem.dao;

import com.example.ordersystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author henyge
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
//    @Query("SELECT p from Product WHERE p.id IN(SELECT l.productId from ProductLine WHERE l.orderId= :orderId)")
//    List<Product> findByOrderId(long orderId);
}
