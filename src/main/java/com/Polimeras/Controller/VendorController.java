package com.Polimeras.Controller;

import com.Polimeras.Entity.Products;
import com.Polimeras.Entity.Users;
import com.Polimeras.Service.ProductService;
import com.Polimeras.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vendor")
@PreAuthorize("hasRole('VENDOR')")          // every method in this class requires ROLE_VENDOR
public class VendorController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    /** 1. Redirect vendor → static dashboard page */
    @GetMapping("/vendor-dashboard")
    public ResponseEntity<Void> dashboardPage() {
        // /vendor-dashboard.html must live in src/main/resources/static
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/vendor-dashboard.html"))
                .build();
    }

    /** 2. JSON data consumed by the dashboard page */
    @GetMapping("/vendor-dashboard-data")
    public ResponseEntity<Map<String, Object>> dashboardData(@AuthenticationPrincipal UserDetails userDetails) {

        Users vendor = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        List<Products> products = productService.findByVendor(vendor);

        Map<String, Object> body = new HashMap<>();
        body.put("vendor", vendor.getFirstname() + " " + vendor.getLastname());
        body.put("totalProducts", products.size());
        body.put("products", products);

        return ResponseEntity.ok(body);
    }

    @GetMapping("/add-product")
    public ResponseEntity<Void> redirectToStaticPage() {
        return ResponseEntity.status(302)
                .location(URI.create("/add-product.html"))
                .build();
    }

    /** 3. Create new product */
    @PostMapping(value = "/product",   // <- maps POST /api/product
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Products> postProduct(
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute Products product,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Users user = userService.findByUsername(userDetails.getUsername()).orElse(null);
        Products saved = productService.addProduct(user,product, imageFile);
        return ResponseEntity.ok(saved);
    }

    /** 4. Read single product */
    @GetMapping("/product/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable long id) {
        return ResponseEntity
                .ok(productService.get(id));
    }

    /** 5. Update product */
    @PutMapping(value = "/product/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Products> updateProduct(@PathVariable long id,
                                                  @ModelAttribute Products productPart,
                                                  @RequestPart(value = "imageFile", required = false) MultipartFile imageFile)
            throws IOException {
        return ResponseEntity.ok(productService.updateProduct(id, productPart, imageFile));
    }

    /* 6. Delete Product */
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String > deleteProduct (@PathVariable long id){
        productService.deleteProductById(id);
        return ResponseEntity.status(200).body("Product Deleted...");
    }

}
