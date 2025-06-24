package com.Polimeras.Service;

import com.Polimeras.Entity.Category;
import com.Polimeras.Entity.Products;
import com.Polimeras.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public Optional<Products> getProductById(long id) {
        return productsRepository.findById(id);
    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    public List<Products> getCategoryService(Category category) {
        return productsRepository.getCategory(category);
    }
}
