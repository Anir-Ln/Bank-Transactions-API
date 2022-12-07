package ma.octo.assignement.web;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.exceptions.ResourceNotFoundException;
import ma.octo.assignement.service.interfaces.AccountService;
import ma.octo.assignement.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Account> getAll() {
        return accountService.findAll();
    }

    @GetMapping("/{username}")
    @PreAuthorize("authentication.principal.equals(#username)")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getUserAccounts(@PathVariable("username") String username) throws ResourceNotFoundException {
        User user = userService.findByUsername(username);
        return accountService.findByUser(user);
    }
}
