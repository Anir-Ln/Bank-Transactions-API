package ma.octo.assignement.service;

import ma.octo.assignement.domain.*;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.DepositRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.interfaces.AuditTransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
public class TransactionServiceTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransferRepository transferRepository;
    @Mock
    DepositRepository depositRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    AuditTransactionService auditTransactionService;

    private List<User> users;
    private Deposit deposit;
    private Account recipientAccount, senderAccount;
    private Transfer transfer;

    @BeforeEach
    void setUp() {
        recipientAccount = new Account();
        recipientAccount.setAccountNumber("1111");
        recipientAccount.setBalance(BigDecimal.TEN);

        senderAccount = new Account();
        senderAccount.setAccountNumber("2222");
        senderAccount.setBalance(BigDecimal.valueOf(70));

        transfer = new Transfer();
        transfer.setSenderAccount(senderAccount);
        transfer.setRecipientAccount(recipientAccount);
    }

    @AfterEach
    void tearDown() {
        recipientAccount = senderAccount = null;
        transfer = null;
        deposit = null;
    }

    @Test
    void shouldFailTransfer_InsuffisantBalance() {
        transfer.setAmount(BigDecimal.valueOf(100));
        assertThrows(SoldeDisponibleInsuffisantException.class, () -> transactionService.doTransfer(transfer));
    }

    @Test
    void shouldFailTransfer_badAccount() {
        transfer.setAmount(BigDecimal.valueOf(50));
        transfer.setRecipientAccount(null);
        assertThrows(TransactionException.class, () -> transactionService.doTransfer(transfer));
    }

    @Test
    void shouldSucceedTransfer() {
        transfer.setAmount(BigDecimal.valueOf(40));
        assertDoesNotThrow(() -> transactionService.doTransfer(transfer));

        assertEquals(recipientAccount.getBalance(), BigDecimal.valueOf(50));
        assertEquals(senderAccount.getBalance(), BigDecimal.valueOf(30));
        verify(accountRepository, times(2)).save(any());
        verify(transferRepository).save(any());
        verify(auditTransactionService).auditTransfer(any());
    }

    @Test
    public void shouldSucceedDeposit() {
        deposit = new Deposit();
        deposit.setAmount(BigDecimal.valueOf(99));
        deposit.setRecipientAccount(recipientAccount);

        transactionService.doDeposit(deposit);
        assertEquals(recipientAccount.getBalance(), BigDecimal.valueOf(109));

        verify(accountRepository).save(recipientAccount);
        verify(depositRepository).save(deposit);
        verify(auditTransactionService).auditDeposit(any());
    }
}
