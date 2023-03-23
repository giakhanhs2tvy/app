package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
