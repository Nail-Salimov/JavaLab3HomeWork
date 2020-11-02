package ru.itis.javalab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itis.javalab3.entities.Transaction;
import ru.itis.javalab3.jsons.BuyJson;
import ru.itis.javalab3.services.TransactionsService;


@RepositoryRestController
@Controller
public class ReturnPurchasesController {

    @Autowired
    private TransactionsService transactionsService;

    @PutMapping("/transactions/{transaction_id}/return")
    public @ResponseBody
    ResponseEntity<?> returnPurchases(@PathVariable("transaction_id") Long id) {
        return ResponseEntity.ok(transactionsService.returnPurchase(id));
    }

}
