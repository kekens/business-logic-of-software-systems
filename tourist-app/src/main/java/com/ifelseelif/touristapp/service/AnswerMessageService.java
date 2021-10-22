package com.ifelseelif.touristapp.service;

import com.ifelseelif.touristapp.models.dto.ModeratorAnswer;
import com.ifelseelif.touristapp.service.interfaces.IAnswerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnswerMessageService implements IAnswerMessageService {

    private final KafkaTemplate<Long, ModeratorAnswer> kafkaTemplate;

    @Autowired
    public AnswerMessageService(KafkaTemplate<Long, ModeratorAnswer> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendAnswerMessage(ModeratorAnswer moderatorAnswer) {
        kafkaTemplate.send("tourist-messages", moderatorAnswer);
    }
}
