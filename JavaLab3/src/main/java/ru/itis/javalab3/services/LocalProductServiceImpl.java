package ru.itis.javalab3.services;

import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab3.entities.LocalProduct;
import ru.itis.javalab3.entities.Product;
import ru.itis.javalab3.repositories.LocalProductsRepository;

import java.util.Optional;

@Service
public class LocalProductServiceImpl implements LocalProductService{

    @Autowired
    private LocalProductsRepository localProductsRepository;

    @Override
    public LocalProduct buyLocalProduct(Long id) {
        Optional<LocalProduct> optionalLocalProduct = localProductsRepository.findById(id);
        if(!optionalLocalProduct.isPresent()){
            throw new IllegalArgumentException("LocalProduct with id not exist");
        }

        LocalProduct localProduct = optionalLocalProduct.get();
        if (localProduct.getCount().equals(0L)){
            throw new IllegalArgumentException("LocalProduct is out of stock");
        }
        localProduct.setCount(localProduct.getCount()-1);
        return localProductsRepository.save(localProduct);
    }
}
