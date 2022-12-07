package ma.octo.assignement.web;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.exceptions.AccountNotExistingException;
import ma.octo.assignement.exceptions.ResourceNotFoundException;
import ma.octo.assignement.repository.UserRepository;
import ma.octo.assignement.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final AccountService accountService;

    @Autowired
    public UserController(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @GetMapping("/{username}")
    @PreAuthorize("authentication.principal.equals(#username)")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable("username") String username) throws ResourceNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @GetMapping("/{username}/accounts")
    @PreAuthorize("authentication.principal.equals(#username)")
   @ResponseStatus(HttpStatus.OK)
    public List<Account> getUserAccounts(@PathVariable("username") String username) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return accountService.findByUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
