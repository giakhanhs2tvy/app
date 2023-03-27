package intern.project.parkingmanagerment.service;

import intern.project.parkingmanagerment.model.Card;

public interface CardService {
    Card findById(Long cardId);
}
