package com.Polimeras.Controller;

import com.Polimeras.Entity.Products;
import com.Polimeras.Entity.Users;
import com.Polimeras.Repository.ProductsRepository;
import com.Polimeras.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;


    @GetMapping("/product/{id}")
    public Optional<Products> display(@PathVariable long id){
        Optional<Products> products = productsRepository.findById(id);
        System.out.println(products);
        return products;
    }
    @GetMapping("/user/{id}")
    public Optional<Users> usersHandle(@PathVariable long id){
        Optional<Users> users = usersRepository.findById(id);
        System.out.println(users);
        return users;
    }

}
