package ru.itis.mongohateoas.configs;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.mongohateoas.controller.ChangeSexController;
import ru.itis.mongohateoas.entities.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserRepresentationProcessor implements RepresentationModelProcessor<EntityModel<User>> {


    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        User user = model.getContent();

        if(user != null){
            model.add(linkTo(methodOn(ChangeSexController.class)
            .changeSex(user.get_id())).withRel("changeSex"));
        }
        return model;
    }
}
