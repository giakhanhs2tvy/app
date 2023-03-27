package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.ERole;
import intern.project.parkingmanagerment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findRoleByName(ERole roleName);
}