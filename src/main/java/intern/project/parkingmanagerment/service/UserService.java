package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.dto.UserDto;
import intern.project.parkingmanagerment.model.User;

public interface UserService {
    public User createUser(UserDto user);
    public User findByUserName(String name);
}
