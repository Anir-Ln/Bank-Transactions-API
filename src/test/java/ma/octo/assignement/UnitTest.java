package ma.octo.assignement;

import ma.octo.assignement.domain.User;
import ma.octo.assignement.exceptions.ResourceNotFoundException;
import ma.octo.assignement.repository.*;
import ma.octo.assignement.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UnitTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;


    @Test
    public void whenValidUsername_thenUserShouldBeFound() throws ResourceNotFoundException {
        User user1 = new User();
        user1.setUsername("anir");

        given(userRepository.findByUsername(user1.getUsername())).willReturn(Optional.of(user1));

        String username = "anir";
        User user = userService.findByUsername(username);
        assert user.getUsername().equals(username);
    }

    @Test
    public void test() {
        assert 1 == 1;
    }

}
