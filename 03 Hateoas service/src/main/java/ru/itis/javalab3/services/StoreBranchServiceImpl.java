package ru.itis.javalab3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab3.entities.StoreBranch;
import ru.itis.javalab3.entities.StoreBranchState;
import ru.itis.javalab3.repositories.LocalProductsRepository;
import ru.itis.javalab3.repositories.StoreBranchesRepository;

import java.util.Optional;

@Service
public class StoreBranchServiceImpl implements StoreBranchService{

    @Autowired
    private StoreBranchesRepository storeBranchesRepository;

    @Autowired
    private LocalProductsRepository localProductsRepository;

    @Override
    public StoreBranch close(Long storeBranchId) {
        Optional<StoreBranch> optionalStoreBranch = storeBranchesRepository.findById(storeBranchId);
        if (!optionalStoreBranch.isPresent()){
            throw new IllegalArgumentException("StoreBranch with this id is not exist");
        }

        StoreBranch storeBranch = optionalStoreBranch.get();

        localProductsRepository.deleteAll(storeBranch.getLocalProductSet());
        storeBranch.setState(StoreBranchState.CLOSE);
        storeBranchesRepository.save(storeBranch);
        return storeBranch;
    }
}
