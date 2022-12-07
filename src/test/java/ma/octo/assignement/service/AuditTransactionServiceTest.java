package ma.octo.assignement.service;

import ma.octo.assignement.domain.AuditTransaction;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditTransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class AuditTransactionServiceTest {
    @Mock
    AuditTransactionRepository auditTransactionRepository;

    @InjectMocks
    AuditTransactionServiceImpl auditTransactionService;

    @Test
    public void testAudit() {
        String message = "transfer is done";
        auditTransactionService.auditTransfer(message);
        ArgumentCaptor<AuditTransaction> argumentCaptor = ArgumentCaptor.forClass(AuditTransaction.class);
        verify(auditTransactionRepository).save(argumentCaptor.capture());
        assertEquals(message, argumentCaptor.getValue().getMessage());
        assertEquals(EventType.TRANSFER, argumentCaptor.getValue().getEventType());
    }
}
