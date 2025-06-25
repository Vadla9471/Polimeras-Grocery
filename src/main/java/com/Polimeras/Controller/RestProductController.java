package com.Polimeras.Controller;

import com.Polimeras.Entity.Products;
import com.Polimeras.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class RestProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Products());
        return "add_product";
    }

    @PostMapping("/product")
    public String postProduct(@ModelAttribute Products product,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model) throws IOException {
        Products products = productService.addProduct(product, imageFile);
        model.addAttribute("product", products);
        return "add_product";
    }


}
