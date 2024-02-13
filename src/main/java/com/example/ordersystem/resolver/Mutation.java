/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ordersystem.resolver;

import com.example.ordersystem.dao.OrderRepository;
import com.example.ordersystem.dao.ProductRepository;
import com.example.ordersystem.dao.UserRepository;
import com.example.ordersystem.domain.Order;
import com.example.ordersystem.domain.Product;
import com.example.ordersystem.domain.ProductLine;
import com.example.ordersystem.domain.User;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 *
 * @author henyge
 */
@Component
public class Mutation implements GraphQLMutationResolver{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    /**
     * create user mutation
     * 
     * @param name
     * @param email
     * @param password
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(String name, String email, String password){
        
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        
        userRepository.save(user);
        
        return user;
    }
    
    /**
     * Update User mutation
     * 
     * @param id
     * @param name
     * @param email
     * @param password
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(long id, String name, String email, String password){
        
        Optional<User> optUser = userRepository.findById(id);
        
        if(optUser.isPresent()){
            
            User user = optUser.get();
            
            if(name != null)
                user.setName(name);
            if(email != null)
                user.setEmail(email);
            if(password != null)
                user.setPassword(password);
            
            userRepository.save(user);
            
            return user;
        }
        
        throw new EntityNotFoundException("Not found User to update!");
        
    }
    
    /**
     * Delete User mutation
     * 
     * @param id
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteUser(long id){
        
        userRepository.deleteById(id);
        
        return true;
    }
    
    /**
     * Create Product mutation
     * 
     * @param name
     * @param stock
     * @param price
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    public Product createProduct(String name, int stock, double price){
        
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        product.setPrice(price);
        
        productRepository.save(product);
        
        return product;
    }
    
    /**
     * Update Product mutation
     * 
     * @param id
     * @param name
     * @param stock
     * @param price
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Product updateProduct(long id, String name, int stock, double price){
        
        Optional<Product> optProduct = productRepository.findById(id);
        
        if(optProduct.isPresent()){
            
            Product product = optProduct.get();
            
            if(name != null)
                product.setName(name);
            product.setStock(stock);
            product.setPrice(price);
            
            productRepository.save(product);
            
            return product;
        }
        
        throw new EntityNotFoundException("Not found Product to update!");
    }
    
    /**
     * Delete Product mutation
     * 
     * @param id
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteProduct(long id){
        
        productRepository.deleteById(id);
        
        return true;
    }
    
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Order createOrder(List<ProductLine> products, long userId){
        
        Order order = new Order();
        
        order.setProducts(products);
        order.setUser(userRepository.getReferenceById(userId));
        
        orderRepository.save(order);
        
        return order;
    }
    
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public Order updateOrder(long id, List<ProductLine> products){
        
        Optional<Order> optOrder = orderRepository.findById(id);
        
        if(optOrder.isPresent()){
            
            Order order = optOrder.get();
            
            order.setProducts(products);
            
            orderRepository.save(order);
            
            return order;
        }
        
        throw new EntityNotFoundException("Not found Product to update!");
    }
    
    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    public boolean deleteOrder(long id){
        
        orderRepository.deleteById(id);
        
        return true;
    }
}
