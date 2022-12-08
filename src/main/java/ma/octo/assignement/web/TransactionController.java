package ma.octo.assignement.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.AccountNotExistingException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.DepositMapper;
import ma.octo.assignement.mapper.TransferMapper;
import ma.octo.assignement.service.interfaces.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Slf4j
class TransactionController {

//    public static final int MONTANT_MAXIMAL = 10000;

    private final TransactionService transactionService;

    @PostMapping("/transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public TransferDto createTransfer(@Valid @RequestBody TransferDto transferDto, Authentication authentication)
            throws SoldeDisponibleInsuffisantException, AccountNotExistingException, TransactionException {

        String authUsername = authentication.getPrincipal().toString();
        Transfer transfer = TransferMapper.toEntity(transferDto);

        if (!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
                !authUsername.equals(transfer.getSenderAccount().getUser().getUsername())) {

            throw new AccessDeniedException("Wrong sender account number");
        }

        transactionService.doTransfer(transfer);
        return TransferMapper.toDto(transfer);
    }


    @PostMapping("/deposits")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDeposit(@Valid @RequestBody DepositDto depositDto) throws AccountNotExistingException {
        Deposit deposit = DepositMapper.toEntity(depositDto);
        transactionService.doDeposit(deposit);
    }


    @GetMapping("/transfers")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TransferDto> getAllTransfers() {
        return transactionService.findAllTransfer().stream().map(TransferMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping("/deposits")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DepositDto> getAllDeposits() {
        return transactionService.findAllDeposit().stream().map(DepositMapper::toDto).collect(Collectors.toList());
    }

}
