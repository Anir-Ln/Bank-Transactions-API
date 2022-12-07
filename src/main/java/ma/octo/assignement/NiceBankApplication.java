package ma.octo.assignement;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Role;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.RoleRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class NiceBankApplication implements CommandLineRunner {
	private final AccountRepository accountRepository;
	private final TransferRepository transferRepository;
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(NiceBankApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... strings) throws Exception {
		Role role_user = new Role();
		role_user.setName("ROLE_USER");
		roleRepository.save(role_user);

		Role role_admin = new Role();
		role_admin.setName("ROLE_ADMIN");
		roleRepository.save(role_admin);

		PasswordEncoder passwordEncoder = passwordEncoder();

		User user1 = new User();
		user1.setUsername("admin");
		user1.setLastname("admin");
		user1.setFirstname("first1");
		user1.setGender("Male");
		user1.setPassword(passwordEncoder.encode("admin"));
		user1.setRoles(List.of(role_admin));
		userRepository.save(user1);


		User user2 = new User();
		user2.setUsername("user");
		user2.setLastname("user");
		user2.setFirstname("first2");
		user2.setGender("Female");
		user2.setPassword(passwordEncoder.encode("user"));
		user2.setRoles(List.of(role_user));

		userRepository.save(user2);

		Account account1 = new Account();
		account1.setAccountNumber("010000A000001000");
		account1.setRib("RIB1");
		account1.setBalance(BigDecimal.valueOf(200000L));
		account1.setUser(user1);

		accountRepository.save(account1);

		Account account2 = new Account();
		account2.setAccountNumber("010000B025001000");
		account2.setRib("RIB2");
		account2.setBalance(BigDecimal.valueOf(140000L));
		account2.setUser(user2);

		accountRepository.save(account2);

		Transfer transfer = new Transfer();
		transfer.setAmount(BigDecimal.TEN);
		transfer.setRecipientAccount(account2);
		transfer.setSenderAccount(account1);
		transfer.setExecutionDate(new Date());
		transfer.setReason("Assignment 2021");

		transferRepository.save(transfer);
	}
}
