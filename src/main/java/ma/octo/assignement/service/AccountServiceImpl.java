package ma.octo.assignement.service;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public Account findByRib(String rib) {
        return accountRepository.findByRib(rib);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findByUser(User user) {
        return accountRepository.findByUser(user);
    }
}
