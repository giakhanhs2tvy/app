package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.Card;
import intern.project.parkingmanagerment.repositories.CardRepository;
import intern.project.parkingmanagerment.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Override
    public Card findById(Long cardId) {
        return cardRepository.findByCardId(cardId);
    }
}
