package ru.itis.javalab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.services.StoreBranchService;

@Controller
@RepositoryRestController
public class StoreBranchController {

    @Autowired
    private StoreBranchService storeBranchService;

    @PutMapping("/store_branch/{store_branch_id}/close")
    public ResponseEntity<?> close(@PathVariable("store_branch_id") Long branchId){

        return ResponseEntity.ok(storeBranchService.close(branchId));
    }
}
