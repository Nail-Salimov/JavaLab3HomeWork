package ru.itis.javalab3.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.entities.Product;

@Component
public class ProductRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Product>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Product> process(EntityModel<Product> model) {

        Product product = model.getContent();
        if(product != null){
            model.add(links.linkToItemResource(Product.class, product.getId())
                    .withRel("cheap"));
        }
        return model;
    }
}
