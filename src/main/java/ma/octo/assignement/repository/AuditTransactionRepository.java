package ma.octo.assignement.repository;

import ma.octo.assignement.domain.AuditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTransactionRepository extends JpaRepository<AuditTransaction, Long> {
}
