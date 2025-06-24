package com.Polimeras.Controller;

import com.Polimeras.Entity.Category;
import com.Polimeras.Entity.Products;
import com.Polimeras.Repository.ProductsRepository;
import com.Polimeras.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Optional<Products> product(@PathVariable long id){
        Optional<Products> product = productService.getProductById(id);
        System.out.println(product);
        return product;
    }

    @GetMapping("/products")
    public List<Products> products(){
        List<Products> products = productService.getAllProducts();
        System.out.println(products);
        return products;
    }

    @GetMapping("/products/{category}")
    public List<Products> category(@PathVariable Category category){
        List<Products> byCategory = productService.getCategoryService(category);
        System.out.println(byCategory);
        return byCategory;
    }

//    @PostMapping("/product")
//    public String postProduct(@RequestPart Products product){
//        productsRepository.save(product);
//        System.out.println(product);
//        return "Executed";
//    }

}
