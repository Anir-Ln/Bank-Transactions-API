package ma.octo.assignement.service;

import ma.octo.assignement.domain.User;
import ma.octo.assignement.exceptions.ResourceNotFoundException;
import ma.octo.assignement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void whenValidUsername_userShouldBeFound() throws ResourceNotFoundException {
        User user = new User();
        user.setUsername("anir");

        BDDMockito.given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));

        String username = "anir";
        User foundUser = userService.findByUsername(username);

        assert foundUser.getUsername().equals(username);
    }

    @Test
    public void findAllTest() {
        User user1 = new User();
        user1.setUsername("anir");

        List<User> users =  List.of(user1);

        BDDMockito.given(userRepository.findAll()).willReturn(users);

        List<User> foundUsers = userService.findAll();
        assert foundUsers.size() == 1;
        assert foundUsers.get(0).getUsername().equals(user1.getUsername());
    }
}
