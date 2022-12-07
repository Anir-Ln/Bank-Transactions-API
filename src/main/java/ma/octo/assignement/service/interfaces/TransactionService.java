package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;

import java.util.List;

public interface TransactionService {
    public Transfer saveTransfer(Transfer transfer);
    public Deposit saveDeposit(Deposit deposit);

    public List<Transfer> findAllTransfer();
    public List<Deposit> findAllDeposit();

    public void doTransfer(Transfer transfer) throws SoldeDisponibleInsuffisantException, TransactionException;
    public void doDeposit(Deposit deposit);
}
