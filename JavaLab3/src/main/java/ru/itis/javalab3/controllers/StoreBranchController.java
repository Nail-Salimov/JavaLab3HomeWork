package ru.itis.javalab3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.services.StoreBranchService;

@Controller
@RepositoryRestResource
public class StoreBranchController {

    @Autowired
    private StoreBranchService storeBranchService;

    @PostMapping("/close")
    public ResponseEntity<?> close(@RequestParam("store_branch_id") Long branchId){

        storeBranchService.close(branchId);
        return ResponseEntity.ok("ok");
    }
}
