package intern.project.parkingmanagerment.repositories;

import intern.project.parkingmanagerment.model.Card;
import intern.project.parkingmanagerment.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT H FROM History H WHERE H.card.cardId = :cardId and H.timeOut is null ")
    History findHistoriesByCardIdAndTimeOutNull(Long cardId);

}