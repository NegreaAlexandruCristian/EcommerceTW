package com.ecommerce.controllers;

import com.ecommerce.models.Product;
import com.ecommerce.services.specifications.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //ok
    @PostMapping("/insert")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.ACCEPTED);
    }

    //ok
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.FOUND);
    }

    //ok
    @GetMapping("/product/{id}")
    public ResponseEntity<Boolean> checkProductById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.checkIfExists(id), HttpStatus.FOUND);
    }

    //ok
    @GetMapping("/count")
    public int countProducts() {
        return productService.count();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestBody Product product) {
        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
