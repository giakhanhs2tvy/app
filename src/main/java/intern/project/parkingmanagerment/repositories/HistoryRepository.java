package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.Card;
import intern.project.parkingmanagerment.model.History;
import intern.project.parkingmanagerment.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT H FROM History H WHERE H.card.cardId = :cardId and H.timeOut is null ")
    History findHistoriesByCardIdAndTimeOutNull(@Param("cardId") Long cardId);

    List<History> findByOrderByUpdatedAtDesc();

    @Query(value = "SELECT * FROM History H order by updated_at desc LIMIT :page, 6", nativeQuery = true)
    List<History> findByOrderByUpdatedAtDescOffset(@Param("page")Long page);
    @Query("SELECT H FROM History H WHERE H.vehicle.vehicleType.vehicleTypeId = :vehicleTypeId ")
    List<History> findByVehicleType(@Param("vehicleTypeId") Long vehicleTypeId);
    List<History> findByVehicleOrderByUpdatedAtDesc(Vehicle vehicle);
//    List<History> findByOrderByUpdatedAtDesc();
//    List<History> findByVehicleOrderByUpdatedAtDesc(Vehicle vehicle);

}