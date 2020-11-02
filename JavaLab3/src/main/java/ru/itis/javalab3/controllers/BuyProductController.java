package ru.itis.javalab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab3.services.LocalProductService;

@RepositoryRestController
@Controller
public class BuyProductController {

    @Autowired
    private LocalProductService localProductService;

    @PutMapping("/localProducts/{localProduct_id}/buy")
    public ResponseEntity<?> buyProduct(@PathVariable("localProduct_id") Long id){
        return ResponseEntity.ok(localProductService.buyLocalProduct(id));
    }
}
