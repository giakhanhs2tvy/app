package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.dto.UserDto;
import intern.project.parkingmanagerment.model.ERole;
import intern.project.parkingmanagerment.model.Role;
import intern.project.parkingmanagerment.model.User;
import intern.project.parkingmanagerment.repositories.RoleRepository;
import intern.project.parkingmanagerment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role roleUser = roleRepo.findRoleByName(ERole.ROLE_USER);
        user.addRole(roleUser);
        return userRepo.save(user);
    }

    @Override
    public User findByUserName(String name) {
        return userRepo.findByUserName(name);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}