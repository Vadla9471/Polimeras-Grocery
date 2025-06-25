package com.Polimeras.Service;

import com.Polimeras.Entity.Category;
import com.Polimeras.Entity.Products;
import com.Polimeras.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
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

//    For Vegetables
    public List<Products> getCategoryService() {
        return productsRepository.getCategory();
    }


    public Products addProduct(Products product, MultipartFile imageFile) throws IOException {
        product.setActive(product.isActive());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImgType(imageFile.getContentType());
        product.setImgUrl(imageFile.getBytes());
        product.setCreatedAt(LocalDateTime.now());
        return productsRepository.save(product);
    }
}
