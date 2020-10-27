package ru.itis.javalab3.configs;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Controller;
import ru.itis.javalab3.controllers.BuyProductController;
import ru.itis.javalab3.controllers.StoreBranchController;
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.entities.StoreBranch;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
public class StoreBranchCloseRepresentationProcessor implements RepresentationModelProcessor<EntityModel<StoreBranch>> {

    @Override
    public EntityModel<StoreBranch> process(EntityModel<StoreBranch> model) {
        StoreBranch storeBranch = model.getContent();

        if(storeBranch != null){
            model.add(linkTo(methodOn(StoreBranchController.class)
                    .close(storeBranch.getId())).withRel("close"));
        }
        return model;
    }
}
