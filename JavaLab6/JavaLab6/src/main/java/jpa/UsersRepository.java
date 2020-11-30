package jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends MongoRepository<User, String> {

    @Query(value = "{ confirmed: false })")
    List<User> findAllNotConfirmed();

    @Query(value = " { name: {$exists: true}, $expr: {$gt: [{$strLenCP: $name }, ?0]}}")
    List<User> findAllWithShortName(@Param("len") int length);

    @Query(value = "{auto: {$size: ?0}, city : {$exists: ?1}}")
    List<User>  findByAutoCountAndCity(@Param("countAuto") int countAuto, @Param("bCity") boolean cityIsExist);

    @Query(value = "{auto: {$size: ?0}, city : ?1}")
    List<User>  findByAutoCountAndCity(@Param("countAuto") int countAuto, @Param("city") String city);
}
