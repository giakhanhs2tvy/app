package intern.project.parkingmanagerment.repositories;


import intern.project.parkingmanagerment.model.TypeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCardRepository extends JpaRepository<TypeCard, Long> {
}