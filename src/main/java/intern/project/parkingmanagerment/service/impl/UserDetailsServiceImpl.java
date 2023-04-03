package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.User;
import intern.project.parkingmanagerment.repositories.UserRepository;
import intern.project.parkingmanagerment.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user!=null){
            return new CustomUserDetails(user);
        }
        else throw new UsernameNotFoundException("User not found");
    }
}
