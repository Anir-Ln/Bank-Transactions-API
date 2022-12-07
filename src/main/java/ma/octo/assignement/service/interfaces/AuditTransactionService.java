package ma.octo.assignement.service.interfaces;

public interface AuditTransactionService {
    public void auditTransfer(String message);
    public void auditDeposit(String message);
}
