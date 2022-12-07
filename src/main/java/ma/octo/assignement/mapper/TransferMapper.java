package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.AccountNotExistingException;
import ma.octo.assignement.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {

    private static AccountService accountService;

    @Autowired
    public TransferMapper(AccountService accountService) {
        TransferMapper.accountService = accountService;
    }

    public static TransferDto toDto(Transfer transfer) {
        TransferDto transferDto = new TransferDto();

        transferDto.setId(transfer.getId());
        transferDto.setSenderAccountNumber(transfer.getSenderAccount().getAccountNumber());
        transferDto.setRecipientAccountNumber(transfer.getRecipientAccount().getAccountNumber());
        transferDto.setDate(transfer.getExecutionDate());
        transferDto.setReason(transfer.getReason());
        transferDto.setAmount(transfer.getAmount());

        return transferDto;
    }

    public static Transfer toEntity(TransferDto transferDto) throws AccountNotExistingException {
        Account senderAccount = accountService.findByAccountNumber(transferDto.getSenderAccountNumber());
        Account recipientAccount = accountService.findByAccountNumber(transferDto.getRecipientAccountNumber());

        if (senderAccount == null) {
            throw new AccountNotExistingException("The given Sender account does not exist");
        }
        if (recipientAccount == null) {
            throw new AccountNotExistingException("the given Recipient account does not exist");
        }

        Transfer transfer = new Transfer();
        transfer.setExecutionDate(transferDto.getDate());
        transfer.setRecipientAccount(recipientAccount);
        transfer.setSenderAccount(senderAccount);
        transfer.setAmount(transferDto.getAmount());

        return transfer;
    }
}
