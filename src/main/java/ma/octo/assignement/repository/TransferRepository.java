package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
//    @Query("select sum(amount) from Transfer where executionDate like ?1%")
//    Long getSumOfAmountByDate(String date, Account account);

    List<Transfer> getBySenderAccountAndExecutionDateAfter(Date date, Account account);
}
