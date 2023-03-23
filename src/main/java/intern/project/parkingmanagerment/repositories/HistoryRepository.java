package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}