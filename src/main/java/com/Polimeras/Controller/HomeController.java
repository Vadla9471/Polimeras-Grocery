package com.Polimeras.Controller;

import com.Polimeras.Entity.Users;
import com.Polimeras.Repository.ProductsRepository;
import com.Polimeras.Repository.UsersRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class HomeController {

    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    private JavaMailSender mailSender;


//    @GetMapping("/product/{id}")
//    public Optional<Products> display(@PathVariable long id){
//        Optional<Products> products = productsRepository.findById(id);
//        System.out.println(products);
//        return products;
//    }
//    @GetMapping("/user/{id}")
//    public Optional<Users> usersHandle(@PathVariable long id){
//        Optional<Users> users = usersRepository.findById(id);
//        System.out.println(users);
//        return users;
//    }

//    @RequestMapping("/sendMail")
//    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER','VENDOR')")
//    public String handler(String toEmail){
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message,true);
//
//            helper.setFrom("manapolimeras@gmail.com");
////            helper.setFrom(toEmail);
//            helper.setTo("bakkanollaharinathreddy@gmail.com");
//            helper.setSubject("Welcome To Mana Polimera's");
//            helper.setText("Hello");
//            helper.addAttachment("polimeras Logo.png", new File("C:\\Users\\vadla vineeth kumar\\Downloads\\polimeras Logo.png"));
//
//            mailSender.send(message);
//            return "Mail sent";
//
//        }catch (Exception e){
//            return e.getMessage();
//        }
//
//    }

}
