package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);
    public boolean existsByEmail(String email);
}
