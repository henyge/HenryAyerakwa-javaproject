/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ordersystem.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author henyge
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLineDTO implements Serializable {
    
    private long productId;
    
    private long orderId;
    
    private int quantity;
}
