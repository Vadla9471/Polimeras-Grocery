package com.Polimeras.Controller;

import com.Polimeras.Entity.Category;
import com.Polimeras.Entity.Products;
import com.Polimeras.Entity.Users;
import com.Polimeras.Repository.ProductsRepository;
import com.Polimeras.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/{id}/image", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> productImage(@PathVariable long id) {
        return productService.getProductById(id)
                .map(p -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, p.getImgType())
                        .body(p.getImgUrl()))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/products")
    public List<Products> products(){
        List<Products> products = productService.getAllProducts();
        System.out.println(products);
        return products;
    }

    @GetMapping("/products/{category}")
    @PreAuthorize("hasAnyRole('COUSTMER','VENDOR')")
    public List<Products> category(@PathVariable Category category){
        List<Products> byCategory = productService.getCategoryService(category);
        System.out.println(byCategory);
        return byCategory;
    }

    @GetMapping("/products/VEGETABLES")
    @PreAuthorize("hasAnyRole('COUSTMER','VENDOR')")
    public List<Products> category(){
        List<Products> category = productService.getCategoryService();
        System.out.println(category);
        return category;
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('VENDOR')")
    public void postProduct(@ModelAttribute Products product,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Products products = productService.addProduct(product, imageFile);
        System.out.println(products);
    }


    @GetMapping("/product/{id}")
    public Products get(@PathVariable long id) {
        return productService.get(id);
    }

    @PutMapping(
            value = "/product/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Products update(@PathVariable long id,
                           @ModelAttribute Products productPart,
                           @RequestPart(required = false) MultipartFile imageFile) throws IOException {
        return productService.updateProduct(id, productPart, imageFile);
    }

}
