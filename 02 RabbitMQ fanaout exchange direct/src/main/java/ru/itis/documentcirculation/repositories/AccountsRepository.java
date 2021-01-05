package ru.itis.documentcirculation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.documentcirculation.entities.account.Account;

public interface AccountsRepository extends JpaRepository<Account, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Account a SET a.money = (a.money + :money) WHERE a.id=:id")
    void cashbackAccount(@Param("money") Long money, @Param("id") Long id);

}
