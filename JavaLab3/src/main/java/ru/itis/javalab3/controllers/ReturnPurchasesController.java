package ru.itis.javalab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab3.entities.Transaction;
import ru.itis.javalab3.jsons.BuyJson;
import ru.itis.javalab3.services.TransactionsService;


@RepositoryRestController
@RequestMapping("/transactions/buy")
public class ReturnPurchasesController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<?> returnPurchases(@RequestParam("transaction_id") Long id){
        transactionsService.returnPurchase(id);
        return ResponseEntity.ok("ok");
    }

}
