package com.example.ecommerceTTS.controller;

import com.example.ecommerceTTS.model.Product;
import com.example.ecommerceTTS.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class ProductController {
    //Wire  up another folder to this one; injection inside of file,dot syntax
    @Autowired
    ProductService productService;

    @GetMapping(value = "/product/{id}")
    public String show(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute(product);
        return "product";
    }

    @RequestMapping(value = "/product", method = {RequestMethod.POST, RequestMethod.PUT})
    public String creatOrupdate(@Valid Product product) {
        productService.save(product);
        return "redirect:/product/" + product.getId();
    }
}
