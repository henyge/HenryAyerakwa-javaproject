/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ordersystem.dao;

import com.example.ordersystem.domain.ProductLine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author henyge
 */
@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, Long> {
    
    List<ProductLine> findByOrder_id(long orderId);
}
