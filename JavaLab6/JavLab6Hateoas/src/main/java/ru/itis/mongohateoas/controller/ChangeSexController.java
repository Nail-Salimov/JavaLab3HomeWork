package ru.itis.mongohateoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.mongohateoas.sevices.UsersService;

@RepositoryRestController
@Controller
public class ChangeSexController {


    @Autowired
    private UsersService usersService;

    @PutMapping("/users/{user_id}/change_sex")
    public @ResponseBody
    ResponseEntity<?> changeSex(@PathVariable("user_id") String id){
        return ResponseEntity.ok(usersService.changeSex(id));
    }
}
