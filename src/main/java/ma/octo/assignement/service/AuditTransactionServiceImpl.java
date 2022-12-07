package ma.octo.assignement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.domain.AuditTransaction;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditTransactionRepository;
import ma.octo.assignement.service.interfaces.AuditTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuditTransactionServiceImpl implements AuditTransactionService {


    private final AuditTransactionRepository auditTransactionRepository;


    public void auditTransfer(String message) {

        log.info("Audit de l'événement {}", EventType.TRANSFER);

        AuditTransaction audit = new AuditTransaction();
        audit.setEventType(EventType.TRANSFER);
        audit.setMessage(message);
        auditTransactionRepository.save(audit);
    }


    public void auditDeposit(String message) {

        log.info("Audit de l'événement {}", EventType.DEPOSIT);

        AuditTransaction audit = new AuditTransaction();
        audit.setEventType(EventType.DEPOSIT);
        audit.setMessage(message);
        auditTransactionRepository.save(audit);
    }

}
