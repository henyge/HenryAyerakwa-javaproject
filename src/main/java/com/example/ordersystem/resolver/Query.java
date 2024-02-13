/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ordersystem.resolver;

import com.example.ordersystem.dao.OrderRepository;
import com.example.ordersystem.dao.ProductLineRepository;
import com.example.ordersystem.dao.ProductRepository;
import com.example.ordersystem.dao.UserRepository;
import com.example.ordersystem.domain.Order;
import com.example.ordersystem.domain.Product;
import com.example.ordersystem.domain.ProductLine;
import com.example.ordersystem.domain.User;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 *
 * @author henyge
 */
@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductLineRepository productLineRepository;

    GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long")
            .aliasedScalar(ExtendedScalars.GraphQLLong)
            .build();

    /**
     * Find all users query
     * 
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<User> findAllUsers() {
        
        return userRepository.findAll();
    }
    
    /**
     * find user by ID query
     * 
     * @param id
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Optional<User> findUserById(long id) {
        
        return userRepository.findById(id);
    }
    
    /**
     * Find all products query
     * 
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Iterable<Product> findAllProducts() {
        
        return productRepository.findAll();
    }
    
    /**
     * Find product by ID query
     * 
     * @param id
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Optional<Product> findProductById(long id) {
        
        return productRepository.findById(id);
    }
    
    
    /**
     * Find all orders
     * 
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Iterable<Order> findAllOrders() {
        
        return orderRepository.findAll();
    }
    
    /**
     * Find an order by ID
     * 
     * @param id
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Optional<Order> findOrderById(long id) {
        
        return orderRepository.findById(id);
    }
    
    /**
     * Find products by Order ID
     * 
     * @param orderId
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Iterable<ProductLine> findOrderedProducts(long orderId) {
        
        return productLineRepository.findByOrder_id(orderId);
    }
}
