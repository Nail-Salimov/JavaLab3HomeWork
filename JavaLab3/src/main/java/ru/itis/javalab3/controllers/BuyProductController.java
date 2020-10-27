package ru.itis.javalab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab3.services.LocalProductService;

@Controller
@RepositoryRestResource
public class BuyProductController {

    @Autowired
    private LocalProductService localProductService;

    @PostMapping("/buy")
    public ResponseEntity<?> buyProduct(@RequestParam("localProduct_id") Long id){
        localProductService.buyLocalProduct(id);
        return ResponseEntity.ok("ok");
    }
}
