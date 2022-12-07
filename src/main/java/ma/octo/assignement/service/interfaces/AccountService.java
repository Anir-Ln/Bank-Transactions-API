package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.User;

import java.util.List;

public interface AccountService {
    Account findByAccountNumber(String accountNumber);
    Account findByRib(String rib);

    List<Account> findAll();

    List<Account> findByUser(User user);
}
