package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.exceptions.AccountNotExistingException;
import ma.octo.assignement.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositMapper {
    private static AccountService accountService;

    @Autowired
    public DepositMapper(AccountService accountService) {
        DepositMapper.accountService = accountService;
    }

    public static DepositDto toDto(Deposit deposit) {
        DepositDto depositDto = new DepositDto();
        depositDto.setId(deposit.getId());
        depositDto.setRib(deposit.getRecipientAccount().getRib());
        depositDto.setSenderFullName(deposit.getSenderFullName());
        depositDto.setAmount(deposit.getAmount());
        depositDto.setDate(deposit.getExecutionDate());
        depositDto.setReason(deposit.getReason());

        return depositDto;
    }

    public static Deposit toEntity(DepositDto depositDto) throws AccountNotExistingException {
        Account recipientAccount = accountService.findByRib(depositDto.getRib());

        if (recipientAccount == null) {
            throw new AccountNotExistingException("the given Recipient account does not exist");
        }

        Deposit deposit = new Deposit();
        deposit.setRecipientAccount(recipientAccount);
        deposit.setReason(depositDto.getReason());
        deposit.setAmount(depositDto.getAmount());
        deposit.setSenderFullName(depositDto.getSenderFullName());
        deposit.setExecutionDate(deposit.getExecutionDate());

        return deposit;
    }
}
