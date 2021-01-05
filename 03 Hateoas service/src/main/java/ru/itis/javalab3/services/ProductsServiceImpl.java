package ru.itis.javalab3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab3.repositories.LocalProductsRepository;
import ru.itis.javalab3.repositories.ProductsRepository;

@Service
public class ProductsServiceImpl implements ProductsService{

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private LocalProductsRepository localProductsRepository;

    @Override
    public Long getAllAmount(Long productId) {
        return null;
    }
}
