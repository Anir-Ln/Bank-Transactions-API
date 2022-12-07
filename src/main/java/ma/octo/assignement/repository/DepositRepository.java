package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
