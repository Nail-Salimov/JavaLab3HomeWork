package ru.itis.javalab3.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.javalab3.controllers.ReturnPurchasesController;
import ru.itis.javalab3.entities.Transaction;
import ru.itis.javalab3.entities.TransactionState;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class TransactionRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Transaction>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Transaction> process(EntityModel<Transaction> model) {
        Transaction transaction = model.getContent();

        if (transaction != null && transaction.getState().equals(TransactionState.NOT_RETURNED)) {
            model.add(linkTo(methodOn(ReturnPurchasesController.class)
                    .returnPurchases(transaction.getId())).withRel("return"));
        }

        if (transaction != null) {
            model.add(links.linkToItemResource(Transaction.class, transaction.getId())
                    .withRel("returnedTransactions"));
        }
        return model;
    }
}
