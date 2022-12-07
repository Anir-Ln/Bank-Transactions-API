package ma.octo.assignement.service.interfaces;

import ma.octo.assignement.domain.Role;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.exceptions.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    public User save(User user);
    public void addRoleByUsername(String username, String roleName) throws ResourceNotFoundException;
    public User findByUsername(String username) throws ResourceNotFoundException;
    public List<User> findAll();
}
