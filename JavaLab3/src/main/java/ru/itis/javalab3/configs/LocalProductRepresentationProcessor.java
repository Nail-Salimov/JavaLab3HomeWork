package ru.itis.javalab3.configs;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab3.controllers.BuyProductController;
import ru.itis.javalab3.controllers.ReturnPurchasesController;
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.entities.Transaction;
import ru.itis.javalab3.entities.TransactionState;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LocalProductRepresentationProcessor implements RepresentationModelProcessor<EntityModel<LocalProduct>> {

    @Override
    public EntityModel<LocalProduct> process(EntityModel<LocalProduct> model) {
        LocalProduct localProduct = model.getContent();

        if(localProduct != null){
            model.add(linkTo(methodOn(BuyProductController.class)
                    .buyProduct(localProduct.getId())).withRel("buy"));
        }
        return model;
    }
}
