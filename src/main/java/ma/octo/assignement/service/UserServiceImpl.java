package ma.octo.assignement.service;

import lombok.RequiredArgsConstructor;
import ma.octo.assignement.domain.Role;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.exceptions.ResourceNotFoundException;
import ma.octo.assignement.repository.RoleRepository;
import ma.octo.assignement.repository.UserRepository;
import ma.octo.assignement.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream().map((role -> new SimpleGrantedAuthority(role.getName()))).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void addRoleByUsername(String username, String roleName) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("username not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new ResourceNotFoundException("role not found"));
        user.getRoles().add(role);
    }


    @Override
    public User findByUsername(String username) throws ResourceNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("username not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
