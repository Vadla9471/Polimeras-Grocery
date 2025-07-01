package com.Polimeras.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    @GetMapping("/dashboard")
    public String display(){
        return "This is vendors dash board";
    }

}
