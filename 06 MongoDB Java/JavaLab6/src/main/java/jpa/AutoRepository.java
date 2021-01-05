package jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AutoRepository extends MongoRepository<Auto, String> {
}
