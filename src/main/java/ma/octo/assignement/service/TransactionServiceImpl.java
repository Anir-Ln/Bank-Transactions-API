package ma.octo.assignement.service;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.DepositRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.interfaces.AuditTransactionService;
import ma.octo.assignement.service.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransferRepository transferRepository;
    private final DepositRepository depositRepository;
    private final AccountRepository accountRepository;
//    private final TransactionService transactionService;
    private final AuditTransactionService auditTransactionService;

    @Override
    public Transfer saveTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }
    @Override
    public Deposit saveDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }
    @Override
    public List<Transfer> findAllTransfer() {
        return transferRepository.findAll();
    }
    @Override
    public List<Deposit> findAllDeposit() {
        return depositRepository.findAll();
    }


    @Override
    public void doTransfer(Transfer transfer) throws SoldeDisponibleInsuffisantException, TransactionException {
        Account senderAccount = transfer.getSenderAccount();
        Account recipientAccount = transfer.getRecipientAccount();

        if (senderAccount == null || recipientAccount == null) throw new TransactionException("account is null");

        if (senderAccount.getBalance().intValue() - transfer.getAmount().intValue() < 0) {
            throw new SoldeDisponibleInsuffisantException("solde insuffisant");
        }


        senderAccount.setBalance(senderAccount.getBalance().subtract(transfer.getAmount()));
        accountRepository.save(senderAccount);

        recipientAccount.setBalance(recipientAccount.getBalance().add(transfer.getAmount()));
        accountRepository.save(recipientAccount);

        this.saveTransfer(transfer);
        auditTransactionService.auditTransfer("Transfer depuis " + transfer.getSenderAccount().getAccountNumber() + " vers " + transfer.getRecipientAccount().getAccountNumber()
                         + " d'un montant de " + transfer.getAmount()
                        .toString());
    }

    @Override
    public void doDeposit(Deposit deposit) {
        Account recipientAccount = deposit.getRecipientAccount();
        recipientAccount.setBalance(recipientAccount.getBalance().add(deposit.getAmount()));

        accountRepository.save(recipientAccount);
        this.saveDeposit(deposit);
        auditTransactionService.auditDeposit("Deposit depuis " + deposit.getSenderFullName() + " vers " + deposit.getRecipientAccount().getAccountNumber() + " d'un montant de " + deposit.getAmount()
                        .toString());
    }


}
