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
import java.util.NoSuchElementException;
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

//    public Products updateProduct(Products product , MultipartFile imageFile) throws IOException {
//        product.setActive(product.isActive());
//        product.setImageName(imageFile.getOriginalFilename());
//        product.setImgType(imageFile.getContentType());
//        product.setImgUrl(imageFile.getBytes());
//        product.setUpdatedAt(LocalDateTime.now());
//        return productsRepository.save(product);
//    }

    //
    public Products get(long id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Product " + id + " not found"));
    }

    /** Update fields + optional image */
    public Products updateProduct(long id,
                           Products fields,          // bound from multipart
                           MultipartFile imageFile) throws IOException {

        Products p = get(id);                       // current state
        p.setName(fields.getName());
        p.setDescription(fields.getDescription());
        p.setPrice(fields.getPrice());
        p.setCategory(fields.getCategory());
        p.setStockQuantity(fields.getStockQuantity());
        p.setActive(fields.isActive());

        if (imageFile != null && !imageFile.isEmpty()) {
            p.setImageName(imageFile.getOriginalFilename());
            p.setImgType(imageFile.getContentType());
            p.setImgUrl(imageFile.getBytes());
        }
        p.setUpdatedAt(LocalDateTime.now());

        return productsRepository.save(p);
    }

}
