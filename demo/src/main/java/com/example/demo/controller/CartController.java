package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
@RequiredArgsConstructor
@Controller
public class CartController {
   
   @Autowired
   private final CartRepository cartRepository;
   
   @Autowired
   private final ProductRepository productRepository;

   @Autowired
   private final UserRepository userRepository;

   @GetMapping("/auth/cart/list/{username}")
   public ResponseEntity<List<Cart>> cartList(@RequestParam(required = false) Integer cid, @PathVariable("username") String username){
      try {
         
         List<Cart> cart = new ArrayList<Cart>();
         
            cartRepository.findAllByUsername(username).forEach(cart::add);
           if(cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         } 
         return new ResponseEntity<>(cart, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   
   @GetMapping("/auth/cart/{cid}")
   public ResponseEntity<Cart> cartById(@PathVariable("cid") Integer cid){
      try {
      Optional<Cart> cartData = this.cartRepository.findById(cid);
      Cart cart = cartData.get();
      return new ResponseEntity<>(cart, HttpStatus.OK);
      } catch(Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
      
   }

   
   @DeleteMapping("/auth/cart/delete/{cid}")
   public ResponseEntity<HttpStatus> deletecartList(@PathVariable("cid") Integer cid){
      try {
         
         cartRepository.deleteById(cid);
         
         return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   
   @PutMapping("/auth/cart/modify/{cid}")
   public ResponseEntity<Cart> updatecartList(@PathVariable("cid") Integer cid, @RequestBody Cart cart){
      try {
         Optional<Cart> cartData = cartRepository.findById(cid);
         Cart newCart = cartData.get();
         newCart.setPquantity(cart.getPquantity());
         
         
         return new ResponseEntity<>(cartRepository.save(newCart), HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   
   @PostMapping("/auth/cart/add")
   public ResponseEntity<Cart> registerCart(@RequestBody Cart cart){
      try {
         Cart newCart = this.cartRepository.save(new Cart(cart.getProduct(), cart.getPquantity(), cart.getUsername()));
         
         
         return new ResponseEntity<>(newCart, HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
   
   @DeleteMapping("/auth/cart/deleteall/{username}")
   public ResponseEntity<HttpStatus> deletecartListByUsername(@PathVariable("username") String username){
      try {
         List<Cart> cartList = cartRepository.findAllByUsername(username);
         cartRepository.deleteAll(cartList);
         return new ResponseEntity<>(HttpStatus.OK);
      } catch (Exception e) {
         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}